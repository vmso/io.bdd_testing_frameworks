package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import helpers.SelectHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import utils.Utils;

public class SelectImp extends SelectHelper {

    @Step("Select <index> of <selectElm> option")
    @And("Select {int} index of {string} option")
    public void selectByIndexImp(int i, String jsonKey) {
        selectByIndex(jsonKey, i);
    }

    @Step("Wait <second> presence of <selectElm> then select <index> of its option")
    @And("Wait {long} second presence of {string} element then select {int} index of its option")
    public void selectByIndexImp(long timeout, String jsonKey, int i) {
        selectByIndex(jsonKey, i, timeout);
    }

    @Step("Wait <second> with poling every <millis> presence of <selectElm> then select <index> of its option")
    @And("Select {long} second with poling every {long} presence of {string} then select {string} of its option")
    public void selectByIndexImp(long timeout, long millis, String jsonKey, int i) {
        selectByIndex(jsonKey, i, timeout, millis);
    }

    @Step("Select <value> of <selectElm> option")
    @And("Select {string} value of {string} option")
    public void selectByValueImp(String value, String jsonKey) {
        selectByValue(jsonKey, value);
    }

    @Step("Wait <second> presence of <selectElm> then select <value> of its option")
    @And("Wait {long} second presence of {string} element then select {string} value of its option")
    public void selectByValueImp(long timeout, String jsonKey, String value) {
        selectByValue(jsonKey, value, timeout);
    }

    @Step("Wait <second> with poling every <millis> presence of <selectElm> then select <value> of its option")
    @And("Select {long} second with poling every {long} presence of {string} then select {string} value of its option")
    public void selectByValueImp(long timeout, long millis, String jsonKey, String value) {
        selectByValue(jsonKey, value, timeout, millis);

    }

    @Step("Select <visibleText> of <selectElm> option")
    @And("Select {string} visible text of {string} option")
    public void selectByVisibleTextImp(String value, String jsonKey) {
        selectByVisibleText(jsonKey, value);
    }

    @Step("Wait <second> presence of <selectElm> then select <visibleText> of its option")
    @And("Wait {long} second presence of {string} element then select {string} visible text of its option")
    public void selectByVisibleTextImp(long timeout, String jsonKey, String value) {
        selectByVisibleText(jsonKey, value, timeout);
    }

    @Step("Wait <second> with poling every <millis> presence of <selectElm> then select <visibleText> of its option")
    @And("Select {long} second with poling every {long} presence of <selectElm> then select {string} visible text of its option")
    public void selectByVisibleTextImp(long timeout, long millis, String jsonKey, String value) {
        selectByValue(jsonKey, value, timeout, millis);
    }


    @Step("Multiple select below indexes of <selectElm> option <table>")
    public void multipleSelectByIndexImp(String jsonKey, Table table) {
        var indexes = new Utils().gaugeDataTableToIntArray(table);
        multipleSelectByIndexes(jsonKey, indexes);
    }

    @Step("Multiple select below visible texes of <selectElm> option <table>")
    public void multipleSelectByVisableTexesImp(String jsonKey, Table table) {
        var visibleTexes = new Utils().gaugeDataTableToStringArray(table);
        multipleSelectByVisibleText(jsonKey, visibleTexes);
    }

    @And("Multiple select below indexes of {string} option")
    public void multipleSelectByIndexImp(String jsonKey, DataTable table) {
        Integer[] indexes = table.asList().stream().map(Integer::parseInt).toArray(Integer[]::new);
        multipleSelectByIndexes(jsonKey, indexes);
    }


}
