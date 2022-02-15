package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import exceptions.RequestNotDefined;
import helper.PathParameterHelper;
import io.cucumber.java.en.Given;
import utils.Utils;

import java.util.Map;

public class PathParamImp extends PathParameterHelper {


    @Step({"Add path parameter <key> = <value>.", "Path parametresi ekle <key> = <value>."})
    @Given("Path parameter {string} = {string}.")
    public void addPathParamsToReq(String key, String value) throws RequestNotDefined {
        value = String.valueOf(Utils.getFromStoreData(value));
        addPathParam(key, value);
    }

    @Step({"Add path parameter <key> = <object value>", "Path parametresi ekle <key> = <object value>"})
    @Given("Path parameter {string} = {}, value as object")
    public void addPathParamsToReq(String key, Object value) throws RequestNotDefined {
        addPathParam(key, value);
    }

    @Step({"Add path parameters <table>", "Path parametresi ekle <table>"})
    public void addPathParamsToReq(Table table) throws RequestNotDefined {
        Utils utils = new Utils();
        Map<String, Object> params = utils.gaugeDataTableToMap(table);
        addPathParams(params);
    }

    @Given("Path parameters")
    public void addPathParamsToReq(Map<String, Object> pathParams) throws RequestNotDefined {
        addPathParams(pathParams);
    }

    @Step({"Add path parameter from store with <key>",
            "<key> anahtarı ile saklanan datalardan değeri al path parametresi olarak ekle."})
    @Given("Path parameter from store with {string}")
    public void addPathParaFromStore(String key) throws RequestNotDefined {
        Object params = Utils.getFromStoreData(key);
        addPathParams(key, params);
    }
}
