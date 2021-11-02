package helper;

import io.restassured.builder.MultiPartSpecBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class MultiPartFormDataParametersHelper {

    private final Logger log = LogManager.getLogger(MultiPartFormDataParametersHelper.class);
    private static final String LOG_INFO = "{} = {} add to request as multi-part form data";

    protected void addMultiPartFormData(File file) {
        ApiHelper.getInstance().getRequestSpecification().multiPart(file);
        log.info("{} add to request as multi-part form data", file.getName());
    }

    protected void addMultiPartFormData(String key, File file) {
        String mimeType = new FileHelper().getFileMimeType(file);
        ApiHelper.getInstance().getRequestSpecification().multiPart(new MultiPartSpecBuilder(file)
                .fileName(file.getName())
                .controlName(key)
                .mimeType(mimeType)
                .build());
        log.info(LOG_INFO, key, file.getName());
    }

    protected void addMultiPartFormData(String key, String value)  {
        ApiHelper.getInstance().getRequestSpecification().multiPart(key, value);
        log.info(LOG_INFO, key, value);
    }

    protected void addMultiPartFormData(String key, Object object)  {
        ApiHelper.getInstance().getRequestSpecification().multiPart(key, object);
        log.info(LOG_INFO, key, object.getClass().getName());
    }
}
