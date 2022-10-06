package imp;

import com.thoughtworks.gauge.Step;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SleepHelper {
    private final Logger log = LogManager.getLogger(SleepHelper.class);

    @Step({"Sleep for <second> second",
            "Testi <second> saniyeliğine durdur"})

    public void sleepForSecond(int second) throws InterruptedException {
        Thread.sleep(second * 1000L);
        log.info("Test paused {} seconds", second);
    }

    @Step({"Sleep for <millis> milliSecond",
            "Testi <ms> milli saniyeliğine durdur"})

    public void sleepForMillisecond(long millisecond) throws InterruptedException {
        Thread.sleep(millisecond);
        log.info("Test paused {} millisecond", millisecond);

    }
}
