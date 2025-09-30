package helper.selfheal;

import configuration.Configuration;

/**
 * Placeholder stub for OCR/visual alignment. Behind a config flag to avoid deps for now.
 */
public class OcrAndVisualHelper {

    public static boolean isEnabled() {
        String v = Configuration.getInstance().getStringValueOfProp("selfheal.ocr.enabled");
        return v != null && Boolean.parseBoolean(v);
    }

    /**
     * Returns a small boost factor based on hypothetical visual alignment. For MVP, returns 0.
     */
    public static double visualBoost(String candidateText) {
        if (!isEnabled()) return 0.0;
        return 0.0;
    }
}


