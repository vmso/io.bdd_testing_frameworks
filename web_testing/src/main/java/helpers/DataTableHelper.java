package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper class for extracting and converting web table data into structured formats.
 * Provides methods to convert HTML tables into List<Map<String, String>> format
 * for easy data manipulation and validation in tests.
 */
public class DataTableHelper extends GetElementHelper {
    
    private static final Logger log = LogManager.getLogger(DataTableHelper.class);
    
    /**
     * Converts a web table to a list of maps using JSON keys for both cells and headers.
     * 
     * @param jsonCellKey JSON key for table cells
     * @param jsonHeaderKey JSON key for table headers
     * @return List of maps where each map represents a row with header-value pairs
     */
    public List<Map<String, String>> getTableAsMapList(String jsonCellKey, String jsonHeaderKey) {
        validateJsonKeys(jsonCellKey, jsonHeaderKey);
        
        By cellBy = getByValue(jsonCellKey);
        By headerBy = getByValue(jsonHeaderKey);
        return getTableAsMapList(cellBy, headerBy);
    }
    
    /**
     * Converts a web table to a list of maps using By locators for both cells and headers.
     * 
     * @param cellBy locator for table cells
     * @param headerBy locator for table headers
     * @return List of maps where each map represents a row with header-value pairs
     */
    public List<Map<String, String>> getTableAsMapList(By cellBy, By headerBy) {
        validateLocators(cellBy, headerBy);
        
        List<WebElement> cells = getElementsWithWait(cellBy);
        List<WebElement> headerElements = getElementsWithWait(headerBy);
        
        List<String> headers = extractHeaderTexts(headerElements);
        int rowCount = calculateRowCount(cells.size(), headers.size());
        
        return convertToMapList(cells, rowCount, headers);
    }
    
    /**
     * Converts a web table to a list of maps using a JSON key for cells and provided headers.
     * 
     * @param jsonKey JSON key for table cells
     * @param headers array of header names
     * @return List of maps where each map represents a row with header-value pairs
     */
    public List<Map<String, String>> getTableAsMapList(String jsonKey, String... headers) {
        validateJsonKey(jsonKey);
        validateHeaders(headers);
        
        By by = getByValue(jsonKey);
        return getTableAsMapList(by, headers);
    }
    
    /**
     * Converts a web table to a list of maps using a By locator for cells and provided headers.
     * 
     * @param by locator for table cells
     * @param headers array of header names
     * @return List of maps where each map represents a row with header-value pairs
     */
    public List<Map<String, String>> getTableAsMapList(By by, String... headers) {
        validateLocator(by);
        validateHeaders(headers);
        
        List<WebElement> cells = getElementsWithWait(by);
        int rowCount = calculateRowCount(cells.size(), headers.length);
        
        return convertToMapList(cells, rowCount, Arrays.asList(headers));
    }
    
    /**
     * Core method that converts web elements to a structured map list.
     * 
     * @param cells list of web elements representing table cells
     * @param rowCount number of rows in the table
     * @param headers list of header names
     * @return List of maps where each map represents a row
     */
    private List<Map<String, String>> convertToMapList(List<WebElement> cells, int rowCount, List<String> headers) {
        validateInputParameters(cells, rowCount, headers);
        
        AtomicInteger cellIndex = new AtomicInteger(0);
        List<Map<String, String>> tableData = new ArrayList<>();
        
        IntStream.range(0, rowCount)
                .forEach(rowIndex -> {
                    Map<String, String> row = createRowData(cells, headers, cellIndex);
                    tableData.add(row);
                });
        
        log.info("Successfully converted web table to map list with {} rows and {} columns", rowCount, headers.size());
        return tableData;
    }
    
    /**
     * Creates a single row of data from the table cells.
     * 
     * @param cells list of all table cells
     * @param headers list of header names
     * @param cellIndex atomic integer to track current cell position
     * @return Map representing a single row
     */
    private Map<String, String> createRowData(List<WebElement> cells, List<String> headers, AtomicInteger cellIndex) {
        Map<String, String> row = new HashMap<>();
        
        headers.forEach(header -> {
            String cleanHeader = Utils.strip(header);
            String cellText = extractCellText(cells, cellIndex);
            row.putIfAbsent(cleanHeader, cellText);
            cellIndex.getAndIncrement();
        });
        
        return row;
    }
    
    /**
     * Extracts text from a cell element with proper error handling.
     * 
     * @param cells list of all table cells
     * @param cellIndex current cell index
     * @return cleaned cell text
     */
    private String extractCellText(List<WebElement> cells, AtomicInteger cellIndex) {
        try {
            WebElement cell = cells.get(cellIndex.get());
            return Utils.strip(cell.getText());
        } catch (IndexOutOfBoundsException e) {
            log.warn("Cell index {} is out of bounds. Total cells: {}", cellIndex.get(), cells.size());
            return "";
        }
    }
    
    /**
     * Extracts header texts from web elements.
     * 
     * @param headerElements list of header web elements
     * @return list of header texts
     */
    private List<String> extractHeaderTexts(List<WebElement> headerElements) {
        return headerElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    /**
     * Calculates the number of rows based on total cells and columns.
     * 
     * @param totalCells total number of cells
     * @param columnCount number of columns (headers)
     * @return number of rows
     */
    private int calculateRowCount(int totalCells, int columnCount) {
        if (columnCount == 0) {
            log.warn("Column count is 0, returning 0 rows");
            return 0;
        }
        
        int rowCount = totalCells / columnCount;
        log.debug("Calculated {} rows from {} cells and {} columns", rowCount, totalCells, columnCount);
        return rowCount;
    }
    
    /**
     * Validates input parameters for the conversion process.
     * 
     * @param cells list of web elements
     * @param rowCount number of rows
     * @param headers list of headers
     */
    private void validateInputParameters(List<WebElement> cells, int rowCount, List<String> headers) {
        if (cells == null || cells.isEmpty()) {
            throw new IllegalArgumentException("Cells list cannot be null or empty");
        }
        
        if (rowCount < 0) {
            throw new IllegalArgumentException("Row count cannot be negative");
        }
        
        if (headers == null || headers.isEmpty()) {
            throw new IllegalArgumentException("Headers list cannot be null or empty");
        }
    }
    
    /**
     * Validates JSON keys.
     * 
     * @param jsonCellKey JSON key for cells
     * @param jsonHeaderKey JSON key for headers
     */
    private void validateJsonKeys(String jsonCellKey, String jsonHeaderKey) {
        if (jsonCellKey == null || jsonCellKey.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON cell key cannot be null or empty");
        }
        
        if (jsonHeaderKey == null || jsonHeaderKey.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON header key cannot be null or empty");
        }
    }
    
    /**
     * Validates a single JSON key.
     * 
     * @param jsonKey JSON key to validate
     */
    private void validateJsonKey(String jsonKey) {
        if (jsonKey == null || jsonKey.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON key cannot be null or empty");
        }
    }
    
    /**
     * Validates headers array.
     * 
     * @param headers headers to validate
     */
    private void validateHeaders(String... headers) {
        if (headers == null || headers.length == 0) {
            throw new IllegalArgumentException("Headers cannot be null or empty");
        }
        
        for (String header : headers) {
            if (header == null || header.trim().isEmpty()) {
                throw new IllegalArgumentException("Header cannot be null or empty");
            }
        }
    }
    
    /**
     * Validates By locators.
     * 
     * @param cellBy cell locator
     * @param headerBy header locator
     */
    private void validateLocators(By cellBy, By headerBy) {
        if (cellBy == null) {
            throw new IllegalArgumentException("Cell locator cannot be null");
        }
        
        if (headerBy == null) {
            throw new IllegalArgumentException("Header locator cannot be null");
        }
    }
    
    /**
     * Validates a single By locator.
     * 
     * @param by locator to validate
     */
    private void validateLocator(By by) {
        if (by == null) {
            throw new IllegalArgumentException("Locator cannot be null");
        }
    }
}
