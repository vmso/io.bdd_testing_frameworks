package imp;

import com.thoughtworks.gauge.Step;
import helper.MultiPartFormDataParametersHelper;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Objects;

public class MultiPartFormDataImp extends MultiPartFormDataParametersHelper {

    private final Logger log = LogManager.getLogger(MultiPartFormDataImp.class);

    @Step({"Get \"<fileName>\" file and add to request as multi-part form data",
            "File \"<fileName>\" dosyayı recourcedan getir ve multi-part form data olarak ekle"})
    @Given("Get \"{string}\" file and add to request as multi-part form data")
    public void addMultiPartFormDataToRequest(String filePath) {
        try {
            filePath = Objects.requireNonNull(
                    getClass().getClassLoader().getResource(filePath)
            ).getFile();
            addMultiPartFormData(new File(filePath));
        } catch (Exception e) {
            log.fatal("An error occurred file path {} and error message is {}", filePath, e.getMessage());
        }
    }

    @Step({"Get <file> file and add to request with key <key> as multi-part form data",
            "File \"<fileName>\" dosyayı recourcedan getir ve key <key> ile multi-part form data olarak ekle"})
    @Given("Get {string} file and add to request with key {string} as multi-part form data")
    public void addMultiPartFormDataToRequest(String filePath, String key) {
        filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getFile();
        File file = new File(filePath);
        try {
            addMultiPartFormData(key, file);
        } catch (Exception e) {
            log.fatal("An error occurred when trying to add {} and {}, error message is {}",
                    key, file.getPath(), e.getMessage());
        }
    }


    @Step({"Add to request <key>=<value> as multi-part form data",
            "Multi-part parametre olarak ekle <key>=<value>"})
    @Given("Add to request {string}={string} as multi-part form data")
    public void addMultiPartFormDataToRequestAsString(String key, String value) {
        try {
            addMultiPartFormData(key, value);
        } catch (Exception e) {
            log.fatal("An error occurred when trying to add {} and {}, error message is {}",
                    key, value, e.getMessage());
        }
    }
}
