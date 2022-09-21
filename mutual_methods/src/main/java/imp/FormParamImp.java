package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import helper.FormParamsHelper;
import io.cucumber.java.en.Given;
import utils.Utils;

import java.util.Map;

public class FormParamImp extends FormParamsHelper {


    @Step({"Add form parameter <key> = <value>", "Form parametresi ekle <key> = <value>"})
    @Given("Form parameter {string} = {string}")
    public void addFormParamToReq(String key, String value) {
        addFormParam(key, value);
    }

    @Step({"Add form parameter <key> = <value>", "Form parametresi ekle <key> = <value>"})
    @Given("Form parameter {string} = {}")
    public void addFormParamToReqWithObjValue(String key, Object value) {
        addFormParam(key, value);
    }

    @Step({"Add form parameters <key> = <object type value>", "Form parametrelerini ekleyin <key> = <object type value>"})
    @Given("Form parameters {string} = {}")
    public void addFormParamsToReq(String key, Object value) {
        addFormParams(key, value);
    }

    @Step({"Add form parameters <table>", "Form parametrelerini ekle <table>"})
    public void addFormParamToReq(Table table) {
        Utils utils = new Utils();
        Map<String, Object> parameters = utils.gaugeDataTableToMap(table);
        addFormParams(parameters);
    }

    @Given("Form parameters from table")
    public void addFormParametersFromTable(Map<String, Object> map) {
        addFormParams(map);
    }

}
