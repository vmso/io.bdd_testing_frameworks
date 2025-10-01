package helper.selfheal.ai;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.StructuredChatCompletion;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Thin wrapper around OpenAI Java SDK to suggest corrected texts / locators.
 * Uses Structured Outputs so the model MUST return the schema we define.
 */
public class AiLocatorClient {

    public static class AiLocatorResult {
        /** Likely-corrected visible texts (typo-fixes, spacing, accents, case) */
        public List<String> correctedTexts;
        /** Concrete XPath suggestions (absolute or relative; text-safe) */
        public List<String> xpaths;
        /** Optional CSS selector suggestions */
        public List<String> cssSelectors;
    }

    private final OpenAIClient client;
    private final ChatModel model;
    private final boolean enabled;

    public AiLocatorClient(ChatModel model) {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            this.enabled = false;
            this.client = null;
        } else {
            this.enabled = true;
            this.client = OpenAIOkHttpClient.fromEnv();
        }
        this.model = model;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Ask the model for corrected texts and best-guess locators.
     */
    public Optional<AiLocatorResult> suggest(String originalLocator, String stepText, String domSnippet, String url) {
        if (!enabled) {
            // skip AI entirely if no API key
            return emptyResult();
        }

        var sys = """
        You are a locator-fixing assistant for Selenium/Appium tests.
        Given: (1) a failing locator, (2) optional human step text, (3) a DOM snippet.
        Tasks:
        - Detect likely typos in the target text and propose corrected variants (keep punctuation/case realistic).
        - Output robust XPaths and optional CSS selectors that would match the corrected text, preferring text-safe forms:
            //tag[normalize-space(.)="..."]   and   //*[normalize-space(.)="..."]
          Use contains(...) only as a fallback.
        - Keep suggestions short, valid, and de-duplicated.
        - Do NOT include any explanation in fields; only the values themselves.
        """;

        var user = String.format("""
        URL: %s
        Original locator: %s
        Step text (may be noisy): %s

        DOM snippet (may be truncated):
        %s
        """,
                Objects.toString(url, ""),
                Objects.toString(originalLocator, ""),
                Objects.toString(stepText, ""),
                Objects.toString(domSnippet, "")
        );

        StructuredChatCompletionCreateParams<AiLocatorResult> params =
                StructuredChatCompletionCreateParams.<AiLocatorResult>builder()
                        .model(model)
                        .addSystemMessage(sys)
                        .addUserMessage(user)
                        .responseFormat(AiLocatorResult.class)
                        .maxCompletionTokens(300)
                        .build();

        // ✅ Call returns StructuredChatCompletion<AiLocatorResult>
        StructuredChatCompletion<AiLocatorResult> completion =
                client.chat().completions().create(params);

        Optional<AiLocatorResult> result = completion.choices().stream()
                .map(c -> c.message().content())   // returns Optional<AiLocatorResult>
                .flatMap(Optional::stream)         // unwraps to Stream<AiLocatorResult>
                .findFirst();                      // now returns Optional<AiLocatorResult>

        // If nothing found, create empty object
        if (result.isEmpty()) {
            AiLocatorResult empty = new AiLocatorResult();
            empty.correctedTexts = new ArrayList<>();
            empty.xpaths = new ArrayList<>();
            empty.cssSelectors = new ArrayList<>();
            return Optional.of(empty);
        }

        return result;
    }

    private Optional<AiLocatorResult> emptyResult() {
        AiLocatorResult empty = new AiLocatorResult();
        empty.correctedTexts = new ArrayList<>();
        empty.xpaths = new ArrayList<>();
        empty.cssSelectors = new ArrayList<>();
        return Optional.of(empty);
    }
}
