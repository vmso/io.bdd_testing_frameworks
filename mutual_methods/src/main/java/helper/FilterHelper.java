package helper;

import filter.RestAssuredFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterHelper {

    private final Logger log = LogManager.getLogger(FilterHelper.class);


    protected void addCustomLogFilter(Integer... statusCode) {
        ApiHelper.getInstance().getRequestSpecification().filter(new RestAssuredFilter(statusCode));
        log.info("Status added to log filter {}", Arrays.stream(statusCode)
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    protected void addFilter(RestAssuredFilter filter) {
        ApiHelper.getInstance().getRequestSpecification().filter(filter);
    }
}
