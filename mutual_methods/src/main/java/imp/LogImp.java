package imp;


import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import exceptions.RequestNotDefined;
import helper.FilterHelper;
import helper.ParseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class LogImp extends FilterHelper {


    private final Logger log = LogManager.getLogger(LogImp.class);

    @Step({"Add log filter with errorStatus <table>", "Bu statü kodları için log filtresi ekle <table>"})
    public void addFilter(Table table) {
        ParseHelper parseHelper = new ParseHelper();
        List<TableRow> rows = table.getTableRows();
        var statusCodes = rows
                .stream()
                .map(row -> parseHelper.parsStringToInt(row.getCellValues().get(0))).toList();
        Integer[] status = new Integer[statusCodes.size()];
        statusCodes.toArray(status);
        addCustomLogFilter(status);
    }

    public void addFilter(List<Integer> statusCodes) {
        ParseHelper parseHelper = new ParseHelper();
        Integer[] status = new Integer[statusCodes.size()];
        statusCodes.toArray(status);
        addCustomLogFilter(status);
    }

    @Step({"Log <log>", "Logla <log>"})
    public void startLog(String log) {
        this.log.info(log);
    }

    @Step({"Log this param <key>", "Kayıtlı parametreyi logla <key>"})
    public void logThisParam(String key) {
        this.log.info(ScenarioDataStore.get(key));
    }
}
