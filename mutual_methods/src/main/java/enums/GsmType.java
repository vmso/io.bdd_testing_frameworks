package enums;

import java.util.Arrays;
import java.util.List;

public enum GsmType {
    TURK_CELL(Arrays.asList("530", "531", "532", "533", "534", "535", "536", "537", "538", "539")),
    VODAFONE(Arrays.asList("541", "542", "543", "544", "545", "546", "547", "548", "549")),
    TURK_TELEKOM(Arrays.asList("501", "505", "507", "551", "552", "553", "554", "555", "559"));

    private List<String> areCodeList;

    GsmType(List<String> areCodeList) {
        this.areCodeList = areCodeList;
    }

    public List<String> getAreCodeList(){ return areCodeList;}
}
