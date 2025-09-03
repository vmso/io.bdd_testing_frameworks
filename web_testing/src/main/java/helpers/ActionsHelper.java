package helpers;

import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper extends GetElementHelper {
    private final Logger log = LogManager.getLogger(ActionsHelper.class);

    private Actions getActions() {
            return new Actions(DriverManager.getInstance().getDriver());
    }

    protected void sendKeys(String jsonKey, CharSequence value) {
        var webElm = getClickableElement(jsonKey);
        getActions().sendKeys(webElm, value).build().perform();
        log.info("Typed '{}' in the '{}'", value,jsonKey);
    }

    protected void sendKeys(By by, CharSequence value) {
        var webElm = getClickableElement(by);
        getActions().sendKeys(webElm, value).build().perform();
        log.info("Typed '{}' in the '{}'", value, by);
    }

    /**
     * Clicks in the middle of the given element. Equivalent to: Actions.moveToElement(onElement).click()
     *
     * @param jsonKey json key of the target element
     */
    protected void click(String jsonKey) {
        var webElm = getClickableElement(jsonKey);
        getActions().click(webElm).build().perform();
        log.info("Move to '{}' element and clicked.", jsonKey);
    }

    protected void click() {
        getActions().click().build().perform();
        log.info("Clicked at the current mouse location.");
    }

    /**
     * Moves the mouse to the middle of the element. The element is scrolled into
     * view and its location is calculated using getClientRects.
     * Clicks (without releasing) at the current mouse location.
     *
     * @param jsonKey json key of the target element
     */
    protected void clickAndHold(String jsonKey) {
        var webElm = getClickableElement(jsonKey);
        getActions().moveToElement(webElm).clickAndHold().build().perform();
        log.info("Move to '{}' and clicked (without releasing)", jsonKey);
    }

    /**
     * Clicks (without releasing) at the current mouse location.
     */
    protected void clickAndHold() {
            getActions().clickAndHold().build().perform();
        log.info("Clicks (without releasing) at the current mouse location.");
    }

    /**
     * Releases the depressed left mouse button, in the middle of the given element
     *
     * @param jsonKey json key of the target element
     */
    protected void releaseMouse(String jsonKey) {
        var webElm = getClickableElement(jsonKey);
        getActions().release(webElm).build().perform();
        log.info("Released the depressed left mouse button, in the middle of the '{}' element", jsonKey);
    }

    /**
     * Releases the depressed left mouse button at the current mouse location.
     */
    protected void releaseMouse() {
            getActions().release().build().perform();
        log.info("Releases the depressed left mouse button");
    }


    /**
     * Performs a double click at the middle of the given element.
     *
     * @param jsonKey json key of the target element
     */
    protected void doubleClick(String jsonKey) {
        var webElm = getClickableElement(jsonKey);
        getActions().moveToElement(webElm).doubleClick().build().perform();
        log.info("Performed a double-click at the middle of '{}' element.", jsonKey);
    }

    /**
     * Performs a double click at the current mouse location.
     */
    protected void doubleClick() {
            getActions().doubleClick().build().perform();
        log.info("Performed a double-click at the current mouse location");
    }

    /**
     * Moves the mouse to the middle of the element.
     * The element is scrolled into view and its location is calculated using getClientRects.
     *
     * @param jsonKey json key of the target element
     */
    protected void moveToElement(String jsonKey) {
        var webElement = getClickableElement(jsonKey);
            getActions().moveToElement(webElement).build().perform();
        log.info("Moved the mouse to the middle of the '{}' element", jsonKey);
    }

    /**
     * Moves the mouse from its current position (or 0,0) by the given offset. If the coordinates provided
     * are outside the viewport (the mouse will end up outside the browser window) then the viewport
     * is scrolled to match.
     *
     * @param x offset x
     * @param y offset y
     */
    protected void moveByOffset(int x, int y) {
            getActions().moveByOffset(x, y).build().perform();
        log.info("Moved the mouse to {},{} offset.", x, y);
    }

    /**
     * Moves the mouse to an offset from the element's in-view center point.
     *
     * @param jsonKey json key of the target element
     * @param x       x offset
     * @param y       y offset
     */
    protected void moveToElement(String jsonKey, int x, int y) {
        var webElement = getClickableElement(jsonKey);
            getActions().moveToElement(webElement, x, y).build().perform();
        log.info("Moves the mouse to an offset (x={}.,y={}.) from the '{}' element's in-view center point."
                , x, y, jsonKey);
    }

    /**
     * Performs a context-click at middle of the given element.
     * First performs a mouseMove to the location of the element.
     *
     * @param jsonKey json key of the target element
     */
    protected void rightClick(String jsonKey) {
        var webElement = getClickableElement(jsonKey);
            getActions().moveToElement(webElement).contextClick().build().perform();
        log.info("Performed a context-click at middle of the '{}' element", jsonKey);
    }


    /**
     * Performs a context-click at the current mouse location.
     */
    protected void rightClick() {
            getActions().contextClick().build().perform();
        log.info("Performs a context-click at the current mouse location.");
    }

    /**
     * A convenience method that performs click-and-hold at the location of the source element,
     * moves to the location of the target element, then releases the mouse.
     *
     * @param sourceElementKey json key of the element to emulate button down at.
     * @param targetElementKey json key of the element to move to and release the mouse at.
     */
    protected void dragAndDrop(String sourceElementKey, String targetElementKey) {
        var sourceElement = getClickableElement(sourceElementKey);
        getActions().moveToElement(sourceElement).build().perform();
        var targetElement = getClickableElement(targetElementKey);
            getActions().dragAndDrop(sourceElement, targetElement).build().perform();
        log.info("A convenience method that performed click-and-hold at the location of the '{}' element " +
                        "moves to the location of the target '{}' element",
                sourceElement, targetElement);
    }

    /**
     * A convenience method that performs click-and-hold at
     * the location of the source element, moves by a given offset, then releases the mouse.
     *
     * @param sourceElementKey json key of the element to emulate button down at.
     * @param x                horizontal move offset.
     * @param y                vertical move offset.
     */
    protected void dragAndDropBy(String sourceElementKey, int x, int y) {
        var sourceElement = getClickableElement(sourceElementKey);
            getActions().dragAndDropBy(sourceElement, x, y).build().perform();
        log.info("Performed click-and-hold at '{}' element to (x={}, y={}) offset, then releases the mouse.",
                sourceElement, x, y);
    }

    /**
     * Moves the mouse from its current position, by the given offset (x,y)
     *
     * @param x offset x
     * @param y offset y
     */
    protected void clickPoint(int x, int y) {
        getActions().moveByOffset(x, y).click().build().perform();
        log.info("Moves the mouse from its current position, by the given offset (x={},y={}) and clicked", x, y);
    }


}