package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import exceptions.RequestNotDefined;
import helper.AuthHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class AuthImp extends AuthHelper {

    @Step({"Basic auth with <username> and <password>",
            "Kullanıcı adı: <username>, Şifre <password> ile temel yetkilendirme yap"})
    @Given("Basic auth with {string} and {string}")
    public void basic(String user, String password) throws RequestNotDefined {
        basicAuth(user, password);
    }

    @Step({"Basic auth with <username> and <password> as preemptive",
            "Kullanıcı adı: <username>, Şifre <password> ile preemptive temel yetkilendirme yap"})
    @Given("Basic auth with {string} and {string} as preemptive")
    public void basicAuthWithPreemptive(String user, String password) throws RequestNotDefined {
        preBasicAuth(user, password);
    }

    @Step({"Add Bearer token <token>",
            "Bearer token ekle <token>"})
    @Given("Bearer token {string}")
    public void bearerAuth(String token) throws RequestNotDefined {
        addBearerToken(token);
    }

    @Step({"Add Bearer token from scenario store <key>",
            "Senaryo store'dan Bearer token ekle <token>"})
    @And("Bearer token from scenario store {string}")
    public void bearerAuthFromScenarioStore(String key) throws RequestNotDefined {
        String token = String.valueOf(ScenarioDataStore.get(key));
        addBearerToken(token);
    }

    @Step({"Add Bearer token from suit store <key>",
            "Suit store'dan Bearer token ekle <token>"})
    @And("Bearer token from suit store {string}")
    public void bearerAuthFromSuitStore(String key) throws RequestNotDefined {
        String token = String.valueOf(SuiteDataStore.get(key));
        addBearerToken(token);
    }

    @Step({"Add Bearer token from spec store <key>",
            "Spec store'dan Bearer token ekle <token>"})
    @And("Bearer token from spec store {string}")
    public void bearerAuthFromSpecStore(String key) throws RequestNotDefined {
        String token = String.valueOf(SpecDataStore.get(key));
        addBearerToken(token);
    }
}
