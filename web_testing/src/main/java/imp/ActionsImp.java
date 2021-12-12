package imp;

import com.thoughtworks.gauge.Step;
import helpers.ActionsHelper;
import io.cucumber.java.en.And;

public class ActionsImp extends ActionsHelper {

    @And("Type {string} in the {string} field with keyboard")
    @Step("Type <text> in the <element> field with keyboard")
    public void sendKeysWithActions(String text, String jsonKey) {
        sendKeys(jsonKey, text);
    }

    @And("Click at {string} field with keyboard")
    @Step("Click at <element> field with keyboard")
    public void clickWithActions(String jsonKey) {
        click(jsonKey);
    }

    @And("Click at the offset x={int},y={int}")
    @Step("Click at the offset x=<x>,y=<y>")
    public void clickWithActions(int x, int y) {
        clickPoint(x, y);
    }

    @And("Clicked at the current mouse location.")
    @Step("Clicked at the current mouse location.")
    public void clickWithActions() {
        click();
    }

    @And("Clicked and hold at the current mouse location.")
    @Step("Clicked and hold  at the current mouse location.")
    public void clickAndHoldWithActions() {
        clickAndHold();
    }

    @And("Click and hold at {string} field with keyboard")
    @Step("Click and hold at <element> field with keyboard")
    public void clickAndHoldWithActions(String jsonKey) {
        clickAndHold(jsonKey);
    }

    @And("Release the depressed left mouse button, in the middle of the '{string}'")
    @Step("Release the depressed left mouse button, in the middle of the '<jsonKey>'")
    public void release(String jsonKey) {
        releaseMouse(jsonKey);
    }

    @And("Release the depressed left mouse button")
    @Step("Release the depressed left mouse button")
    public void release() {
        releaseMouse();
    }

    @And("Double clicked at the current mouse location.")
    @Step("Double clicked at the current mouse location.")
    public void doubleClickWithActions() {
        doubleClick();
    }

    @And("Double click at {string} field with keyboard")
    @Step("Double click at <element> field with keyboard")
    public void doubleClickWithActions(String jsonKey) {
        doubleClick(jsonKey);
    }

    @And("Move the mouse to the middle of the {string}")
    @Step("Move the mouse to the middle of the <element>")
    public void moveToElm(String jsonKey) {
        moveToElement(jsonKey);
    }

    @And("Move the mouse from its current position  to x= {int}, y= {int} offset")
    @Step("Move the mouse from its current position  to x=<x>, y=<y> offset")
    public void moveByOffsetWithAction(int x, int y) {
        moveByOffset(x, y);
    }

    @And("Move the mouse to an offset x= {int},y= {int} from the {string}'s in-view center point")
    @Step("Move the mouse to an offset x= <int>,y= <int> from the <element>'s in-view center point")
    public void moveByOffsetWithAction(String jsonKey, int x, int y) {
        moveToElement(jsonKey, x, y);
    }

    @And("Perform a context-click at middle of the {string}")
    @Step("Perform a context-click at middle of the <string>")
    public void rightClickWithAction(String jsonKey) {
        rightClick(jsonKey);
    }

    @And("Perform a context-click at mouse current location")
    @Step("Perform a context-click at mouse current location")
    public void rightClickWithAction() {
        rightClick();
    }

    @And("Click and hold at {string} and move to the location of the {string}")
    @Step("Click and hold at <element> and move to the location of the <element>")
    public void drugAndDropWithAction(String sourceElmJsonKey, String targetElmJsonKey) {
        dragAndDrop(sourceElmJsonKey, targetElmJsonKey);
    }

    @And("Click and hold at {string} and moves by a offset x={int}, y={int}")
    @Step("Click and hold at <element> and moves by a offset x=<x>, y=<y>")
    public void drugAndDropWithAction(String sourceElmJsonKey, int x, int y) {
        dragAndDropBy(sourceElmJsonKey, x, y);
    }
}
