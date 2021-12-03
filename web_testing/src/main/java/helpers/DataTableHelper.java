package helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataTableHelper extends GetElementHelper {
    private final Logger log = LogManager.getLogger(DataTableHelper.class);
    public List<HashMap<String, String>> getTableAsMapList(String jsonKey, String... headers) {
        By by = getByValue(jsonKey);
        return getTableAsMapList(by, headers);
    }

    public List<HashMap<String, String>> getTableAsMapList(By by, String... headers) {
        var cells = getElementsWithWait(by);
        int rowCount = (cells.size() / headers.length);
        return getTableAsMapList(cells, rowCount, Arrays.asList(headers));
    }

    public List<HashMap<String, String>> getTableAsMapList(String jsonCellKey, String jsonHeaderKey) {
        By cellBy = getByValue(jsonCellKey);
        By headerBy = getByValue(jsonHeaderKey);
        return getTableAsMapList(cellBy, headerBy);
    }

    public List<HashMap<String, String>> getTableAsMapList(By cellBy, By headerBy) {
        var cells = getElementsWithWait(cellBy);
        var headersElm = getElementsWithWait(headerBy);
        var headers = headersElm.stream().map(WebElement::getText).collect(Collectors.toList());
        int rowCount = (cells.size() / headers.size());
        return getTableAsMapList(cells, rowCount, headers);
    }

    private List<HashMap<String, String>> getTableAsMapList(List<WebElement> cells, int rowCount, List<String> headers) {
        AtomicInteger cellIndex = new AtomicInteger();
        var table = new ArrayList<HashMap<String, String>>();
        IntStream.range(0, rowCount)
                .forEach(i -> {
                    var row = new HashMap<String, String>();
                    headers.forEach(header -> {
                        var headersText = Utils.strip(header);
                        var cellText = Utils.strip(cells.get(cellIndex.get()).getText());
                        row.putIfAbsent(headersText, cellText);
                        cellIndex.getAndIncrement();
                    });
                    table.add(row);
                });
        log.info("Web data table converted to map list: {}",table);
        return table;
    }

}
