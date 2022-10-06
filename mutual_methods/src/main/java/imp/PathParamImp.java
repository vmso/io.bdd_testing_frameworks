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
    public void addPathParamsToReq(String key, String value)  {
        value = String.valueOf(Utils.getFromStoreData(value));
        addPathParam(key, value);
    }

    @Step({"Add path parameter <key> = <object value>", "Path parametresi ekle <key> = <object value>"})
    public void addPathParamsToReq(String key, Object value)  {
        addPathParam(key, value);
    }

    @Step({"Add path parameters <table>", "Path parametresi ekle <table>"})
    public void addPathParamsToReq(Table table)  {
        Utils utils = new Utils();
        Map<String, Object> params = utils.gaugeDataTableToMap(table);
        addPathParams(params);
    }

    public void addPathParamsToReq(Map<String, Object> pathParams)  {
        addPathParams(pathParams);
    }

    @Step({"Add path parameter from store with <key>",
            "<key> anahtarı ile saklanan datalardan değeri al path parametresi olarak ekle."})
    public void addPathParaFromStore(String key)  {
        Object params = Utils.getFromStoreData(key);
        addPathParams(key, params);
    }
}
