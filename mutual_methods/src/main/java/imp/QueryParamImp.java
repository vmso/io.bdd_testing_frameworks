package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import exceptions.RequestNotDefined;
import helper.QueryParamsHelper;
import io.cucumber.java.en.Given;
import utils.Utils;

import java.util.Map;

public class QueryParamImp extends QueryParamsHelper {


    @Step({"Add query parameter <key> = <value>.", "Query parametresi ekle <key> = <value>."})
    @Given("Add query parameter {string} = {string}")
    public void addQueryParamsToReq(String key, String value) throws RequestNotDefined {
        addQueryParam(key, value);
    }

    @Step({"Add query parameter <key> = <object value>", "Query parametresi ekle <key> = <object value>"})
    @Given("Add query parameter {string} = {}")
    public void addQueryParamsToReq(String key, Object value) throws RequestNotDefined {
        addQueryParam(key, value);
    }

    @Step({"Add query parameter <table>", "Query parametresi ekle <table>"})
    public void addQueryParamsToReq(Table table) throws RequestNotDefined {
        Utils utils = new Utils();
        Map<String, Object> params = utils.gaugeDataTableToMap(table);
        addQueryParams(params);
    }

    @Given("Add query parameter")
    public void addQueryParamsToReq(Map<String, Object> params) throws RequestNotDefined {
        addQueryParams(params);
    }

    @Step({"Add query parameter from store with <key>",
            "<key> anahtarı ile saklanan datalardan değeri al query parametresi olarak ekle."})
    @Given("Add query parameter from store with {string}")
    public void addQueryParamsfromStore(String key) throws RequestNotDefined {
        Object params = Utils.getFromStoreData(key);
        addQueryParams(key, params);
    }
}
