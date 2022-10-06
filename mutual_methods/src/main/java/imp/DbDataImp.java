package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.database.DbDataHelper;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class DbDataImp extends DbDataHelper {
    private final Logger log = LogManager.getLogger(DbDataImp.class);
    private static final String LOG_INFO = "Query result stored on scenario store key: {}, value: {}";

    @Step({"Get column <columnName> data from query <queryName> result and save in scenario store",
            "Query <queryName> sorgusu sonucundan <columnName> sütun verisini scenario deposunda sakla"})
    public void GetQueryResultAndSaveValueWithColumnName(String columnName, String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(columnName))
                .forEach(entry -> {
                    ScenarioDataStore.put(entry.getKey(), entry.getValue());
                    log.info(LOG_INFO, entry.getKey(), entry.getValue());
                });
    }

    @Step({"Get column <columnName> data from query <queryName> result and save in spec store",
            "Query <queryName> sorgusu sonucundan <columnName> sütun verisini spec deposunda sakla"})
    public void GetQueryResultAndSaveValueWithColumnNameSpec(String columnName, String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(columnName))
                .forEach(entry -> {
                    SpecDataStore.put(entry.getKey(), entry.getValue());
                    log.info(LOG_INFO, entry.getKey(), entry.getValue());
                });
    }

    @Step({"Get column <columnName> data from query <queryName> result and save in suit store",
            "Query <queryName> sorgusu sonucundan <columnName> sütun verisini suit deposunda sakla"})
    public void GetQueryResultAndSaveValueWithColumnNameSuit(String columnName, String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(columnName))
                .forEach(entry -> {
                    SuiteDataStore.put(entry.getKey(), entry.getValue());
                    log.info(LOG_INFO, entry.getKey(), entry.getValue());
                });
    }

    @Step({"Get column data from query <queryName> result and save all column data in scenario store with column name",
            "Query <queryName> sorgusu sonucundan dönen tüm datayı kolon isimleriyle birlikte senaryo deposunda sakla"})
    public void GetQueryResultAndSaveValueForAllColumnAndStoreScenarioStore(String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.forEach(ScenarioDataStore::put);
        queryResults.forEach((key, value) -> log.info(LOG_INFO, key, value));
    }

    @Step({"Get column data from query <queryName> result and save all column data in spec store with column name",
            "Query <queryName> sorgusu sonucundan dönen tüm datayı kolon isimleriyle birlikte spec deposunda sakla"})
    public void GetQueryResultAndSaveValueForAllColumnAndStoreSpecStore(String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.forEach(SpecDataStore::put);
        queryResults.forEach((key, value) -> log.info(LOG_INFO, key, value));
    }

    @Step({"Get column data from query <queryName> result and save all column data in suit store with column name",
            "Query <queryName> sorgusu sonucundan dönen tüm datayı kolon isimleriyle birlikte spec deposunda sakla"})
    public void GetQueryResultAndSaveValueForAllColumnAndStoreSuitStore(String queryName) throws SQLException, ClassNotFoundException, IOException {
        Map<String, Object> queryResults = getQueResult(queryName);
        queryResults.forEach(SuiteDataStore::put);
        queryResults.forEach((key, value) -> log.info(LOG_INFO, key, value));
    }
}
