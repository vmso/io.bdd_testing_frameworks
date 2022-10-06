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
    public void basic(String user, String password){
        basicAuth(user, password);
    }

    @Step({"Basic auth with <username> and <password> as preemptive",
            "Kullanıcı adı: <username>, Şifre <password> ile preemptive temel yetkilendirme yap"})
    public void basicAuthWithPreemptive(String user, String password)  {
        preBasicAuth(user, password);
    }

    @Step({"Add Bearer token <token>",
            "Bearer token ekle <token>"})
    public void bearerAuth(String token) {
        addBearerToken(token);
    }

    @Step({"Add Bearer token from scenario store <key>",
            "Senaryo store'dan Bearer token ekle <token>"})
    public void bearerAuthFromScenarioStore(String key)  {
        String token = String.valueOf(ScenarioDataStore.get(key));
        addBearerToken(token);
    }

    @Step({"Add Bearer token from suit store <key>",
            "Suit store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSuitStore(String key) {
        String token = String.valueOf(SuiteDataStore.get(key));
        addBearerToken(token);
    }

    @Step({"Add Bearer token from spec store <key>",
            "Spec store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSpecStore(String key)  {
        String token = String.valueOf(SpecDataStore.get(key));
        addBearerToken(token);
    }
}
