package imp;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import helper.ApiHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class CommonLogImp {
    private final Logger log = LogManager.getLogger(CommonLogImp.class);

    @Step({"Log <log>"})
    public void startLog(String log) {
        this.log.info(log);
    }

    @Step({"Log this param <key>"})
    public void logThisParam(String key) {
        var logString = String.valueOf(Utils.getFromStoreData(key));
        this.log.info(logString);
        Gauge.writeMessage(logString);
    }


    @Step("Print last response")
    public void printResponse() {
        this.log.info( ApiHelper.getInstance().getResponse().asPrettyString());
    }
}
