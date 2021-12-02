package helpers;


import elements.GetElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ScrollHelper extends GetElement {
    private JavascriptHelper javascriptHelper;
    private final Logger log = LogManager.getLogger(ScrollHelper.class);

    public ScrollHelper() {
        javascriptHelper = new JavascriptHelper();
    }

    public void pageUp() {
        javascriptHelper.executeJavascript("window.scrollTo(0, -(document.body.scrollHeight))");
    }

    public void pageDown() {
        javascriptHelper.executeJavascript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToElement(String jsonKey) {
        WebElement webElement = getElement(jsonKey);
        scrollToElement(webElement);
    }

    public void scrollToElement(By by) {
        WebElement webElement = getElement(by);
        scrollToElement(webElement);
    }

    public void scrollToElement(WebElement webElement) {
        String script = "arguments[0].scrollIntoViewIfNeeded();";
        try {
            javascriptHelper.executeJavascript(script, webElement);
        } catch (Exception e) {
            log.warn("Page or browser doesn't support '{}' that js command", script);
        }

    }

    public void scrollToPoint(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        javascriptHelper.executeJavascript(jsStmt, true);
    }
}
