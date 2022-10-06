package imp;

import com.thoughtworks.gauge.Step;
import helper.HttpsHelper;
import io.cucumber.java.en.And;

public class HttpsImp extends HttpsHelper {

    @Step({"Add relaxed HTTPS validation",
            "Varsayılan https sertifkası ekleyin"})
    public void addRelaxedHTTPSValidationToRequest() {
        addRelaxedHTTPSValidation();
    }
}
