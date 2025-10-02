package helper.selfheal.ai;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.StructuredChatCompletion;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;

import java.io.File;
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
    public Optional<AiLocatorResult> suggest(
            String originalLocator,
            String stepText,
            String domSnippet,
            String url,
            File screenshotBase64
    ) {
        if (!enabled) {
            // skip AI entirely if no API key
            return emptyResult();
        }

        var sys = """
        You are a locator-fixing assistant for Selenium/Appium automated tests.
        
        Inputs you will receive:
        1. The original failing locator (may be incomplete, truncated, or slightly wrong).
        2. Optional human-readable step text describing the element.
        3. A DOM snippet (may be partial or truncated).
        4. (Optional) A screenshot of the page for additional context.
        
        Your tasks:
        - Analyze the failing locator and compare it against all available DOM attributes (id, name, class, text, aria-*, data-*).
        - If the locator is incomplete (e.g., "hide" instead of "hide-textbox"), detect likely matches by:
          • Expanding substrings  
          • Checking case-insensitive and trimmed matches  
          • Matching step text to nearby labels or innerText  
          • Considering common attribute variations (id, name, class, text, aria-label, title, etc.)
        - Propose corrected locators across **all supported strategies**, not just the original type. Supported strategies:
          • ID → By.id(value)  
          • NAME → By.name(value)  
          • CLASS_NAME → By.className(value)  
          • CSS_SELECTOR → By.cssSelector(value)  
          • XPATH → By.xpath(value)  
          • LINK_TEXT → By.linkText(value)  
          • PARTIAL_LINK_TEXT → By.partialLinkText(value)  
          • TAG_NAME → By.tagName(value)  
          • TEXT → By.text(value)  (custom framework extension)
        
        Output requirements:
        - Return a list of corrected texts (if any typos/variants were found).
        - Return a list of candidate locators (XPath, CSS, ID, etc.), deduplicated and minimal.
        - Use robust, text-safe XPath patterns when necessary:
            //tag[normalize-space(.)="..."]
            //*[normalize-space(.)="..."]
          Only use contains(...) as a fallback.
        - Prefer simple strategies (id, name, text) over complex XPath if available.
        - Do NOT include any explanation or reasoning in the fields — only the raw values.
        """;

        var user = String.format("""
        Page context:
        URL: %s
        Original locator: %s
        Step text: %s
        
        DOM snippet (truncated if large):
        %s
        
        Screenshot: (optional, base64 or reference) %s
        """,
                        Objects.toString(url, ""),
                        Objects.toString(originalLocator, ""),
                        Objects.toString(stepText, ""),
                        Objects.toString(domSnippet, ""),
                        screenshotBase64
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
