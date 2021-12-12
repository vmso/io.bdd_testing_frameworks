package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helpers.DataTableHelper;
import io.cucumber.java.en.And;
import utils.StoreApiInfo;
import utils.Utils;

import java.util.List;
import java.util.Map;

public class DataTableImp extends DataTableHelper {

    @Step("Get <elm> table list map according to below headers and store it in scenario data store with <key> key <table>")
    public void dataTable(String jsonKey, String key,Table table) {
        StoreApiInfo.put(key, getDataTable(jsonKey, table));
    }

    @Step("Get <elm> table list map according to below headers and store it in spec data store with <key> key <table>")
    public void dataTableSpec(String jsonKey,String key, Table table) {
        SpecDataStore.put(key, getDataTable(jsonKey, table));
    }

    @Step("Get <elm> table list map according to below headers and store it in suit data store with <key> key <table>")
    public void dataTableStore(String jsonKey, String key,Table table) {
        SuiteDataStore.put(key, getDataTable(jsonKey, table));
    }

    private List<Map<String, String>> getDataTable(String jsonKey, Table table) {
        var headers = new Utils().gaugeDataTableToArray(table);
        return getTableAsMapList(jsonKey, headers);
    }

    @Step("Get <elm> table list map according to <headers> and store it in scenario data store with <key> key")
    @And("Get {string} table list map according to {string} and store it in scenario data store with {string} key")
    public void dataTable(String jsonKey, String jsonKeyOfHeader, String key) {
        StoreApiInfo.put(key, getTableAsMapList(jsonKey, jsonKeyOfHeader));
    }

    @Step("Get <elm> table list map according to <headers> and store it in spec data store with <key> key")
    @And("Get {string} table list map according to {string} and store it in spec data store with {string} key")
    public void dataTableSpec(String jsonKey, String jsonKeyOfHeader, String key) {
        SpecDataStore.put(key, getTableAsMapList(jsonKey, jsonKeyOfHeader));
    }

    @Step("Get <elm> table list map according to <headers> and store it in suit data store with <key> key")
    @And("Get {string} table list map according to {string} and store it in suit data store with {string} key")
    public void dataTableStore(String jsonKey, String jsonKeyOfHeader, String key) {
        SuiteDataStore.put(key, getTableAsMapList(jsonKey, jsonKeyOfHeader));
    }

}
