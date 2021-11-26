package helper;


import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Attachment;
import com.slack.api.model.Conversation;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.ExecutionContext;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import configuration.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SlackHelper {

    private static Integer passCount = 0;
    private static Integer failCount = 0;
    private static Integer executed = 0;
    private static final List<String> failedScenarios = new ArrayList<>();
    private static String startDate;

    private final Logger log = LogManager.getLogger(SlackHelper.class);

    @BeforeSuite
    public void beforeSpec() {
        setStartDate();
    }

    @AfterSuite
    public void sendSlackMessage() {
        var slackMessage = Boolean.parseBoolean(
                Configuration.getInstance().getStringValueOfProp("slack_message")
        );
        boolean webHook = SpecDataStore.get("webHook") != null && (boolean) SpecDataStore.get("webHook");
        String slackToken = Configuration.getInstance().getSlackToken();
        String channelId = String.valueOf(SpecDataStore.get("channelId"));
        if (slackMessage && webHook)
            sendSlackMessageWithWebHook(Configuration.getInstance().webhook());
        else if (slackMessage && channelId != null && !channelId.equals("") && !channelId.equals("null"))
            sendSlackMessageWithToken(slackToken, channelId);

    }

    @AfterScenario
    public void updateStatus(ExecutionContext context) {
        countPassFailAndExecuted(context);
    }

    private void sendMessageToSlackWebHook(Object body) {
        try {
            Response response = RestAssured
                    .given()
                    .header("Content-type", "application/json")
                    .baseUri(Configuration.getInstance().webhook())
                    .body(new String(String.valueOf(body).getBytes(StandardCharsets.UTF_8)))
                    .post();
            if (response.statusCode() != 200) {
                log.warn("Slack message couldn't send, please check your webhook is active?");
            }
        } catch (Exception e) {
            log.warn("""
                    Unexpected error occurred when trying send the slack message.
                    Error message is:
                    {}
                    """, e.getMessage());
        }

    }

    private static synchronized void setStartDate() {
        startDate = getDate();
    }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static synchronized void countPassFailAndExecuted(ExecutionContext context) {
        executed++;
        boolean isFailed = context.getCurrentScenario().getIsFailing();
        if (isFailed) {
            failCount++;
            failedScenarios.add(":heavy_multiplication_x: " + context.getCurrentScenario().getName() + "\n");
        } else
            passCount++;
    }

    private void sendSlackMessageWithWebHook(String webHook) {
        if (webHook != null) {
            JSONObject body = new JSONObject();
            JSONArray attachments = new JSONArray();
            String message = createText();
            if (!failedScenarios.isEmpty()) {
                for (String name : failedScenarios) {
                    attachments.put(new JSONObject().put("text", name));
                }
            }

            body.put("text", message);
            body.put("attachments", attachments);

            sendMessageToSlackWebHook(body);

        }
    }

    private void sendSlackMessageWithToken(String token, String channelId) {
        var client = Slack.getInstance().methods();
        var id = findConversation(channelId, token);
        var text = createText();
        if (id != null) {
            try {
                var result = client.chatPostMessage(r -> r
                        .token(token)
                        .channel(id)
                        .attachments(createAttachments())
                        .text(text));
                if (!result.isOk()) {
                    log.warn("Slack message couldn't send, the message: {} ", result.getMessage());
                }

            } catch (IOException | SlackApiException e) {
                log.error("error: {}", e.getMessage(), e);
            }
        }
    }

    private String findConversation(String name, String token) {
        var client = Slack.getInstance().methods();
        String conversationId = null;
        try {
            var result = client.conversationsList(r -> r
                    .token(token)
            );
            if (result.getError() != null) {
                log.warn("Slack message couldn't send error message: {} ", result.getError());
            } else {
                for (Conversation channel : result.getChannels()) {
                    if (channel.getName().equals(name)) {
                        conversationId = channel.getId();
                        log.info("Found conversation ID: {}", conversationId);
                        break;
                    }
                }
            }
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }
        return conversationId;
    }

    private String createText() {
        var endDate = getDate();
        double duration = 0.0;
        try {
            duration = calculateDuration(startDate, endDate);
        } catch (ParseException e) {
            log.warn("parse error in execution time calculation");
        }
        String message = String
                .format("*Test Start:* %s%n*Test End:* %s%n*Test execution time in seconds*: %.2f%n" +
                                "*%d* executed,*%d* passed,*%d* failed",
                        startDate, endDate, duration, executed, passCount, failCount);

        if (!failedScenarios.isEmpty())
            message += "\n *Failed scenarios is below:* \n";

        return message;
    }

    private double calculateDuration(String sd, String ed) throws ParseException {
        var startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(sd).getTime();
        var endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(ed).getTime();
        var duration = (endDate - startDate);
        return duration / 1000.0;
    }

    private List<Attachment> createAttachments() {
        List<Attachment> attachments = new ArrayList<>();

        if (!failedScenarios.isEmpty()) {
            for (String name : failedScenarios) {
                Attachment attachment = new Attachment();
                attachment.setText(name);
                attachments.add(attachment);
            }
            return attachments;
        }
        return attachments;
    }
}
