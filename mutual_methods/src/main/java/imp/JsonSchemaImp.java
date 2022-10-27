package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import exceptions.NullResponse;
import helper.JsonSchemaHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class JsonSchemaImp extends JsonSchemaHelper {

    private final Logger log = LogManager.getLogger(JsonSchemaImp.class);
    private static final String ERROR_OCCURRED = "An error occurred; {}";
    private static final String VALIDATE = "{} json validate with {} schema";
    private static final String VALIDATE_2 = "response json validate with {} schema";



    @Step({"Validate response json with schema <Schema Name>",
            "Validate response json with schema <Schema Name> which is in from class path",
            "Responsu resource da tanımlanan şema <schema name> ile dorğulayın"})
    public void validateJsonSchema(String schemaName) throws NullResponse {
        schemaName = String.valueOf(Utils.getFromStoreData(schemaName));
        try {
            jsonSchemaValidator(String.format("schemas/%s",schemaName));
            log.info("response validate with {} json schema", schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate stored json <json store key> response with stored schema <schema store key> from scenario data store")
    public void validateJsonSchema(String jsonStoreKey, String schemaStoreKey) {
        try {
            String responseJsonString = String.valueOf(ScenarioDataStore.get(jsonStoreKey));
            String jsonSchema = String.valueOf(ScenarioDataStore.get(schemaStoreKey));
            jsonSchemaValidator(responseJsonString, jsonSchema);
            log.info(VALIDATE, jsonStoreKey, schemaStoreKey);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate json <store key> from the scenario stored data with schema <schema store key> in classpath")
    public void validateJsonSchemaInClasspathWithScenarioData(String jsonStoreKey, String schemaName) {
        try {
            String responseJsonString = String.valueOf(ScenarioDataStore.get(jsonStoreKey));
            jsonSchemaValidatiorInClasspath(responseJsonString, schemaName);
            log.info(VALIDATE, jsonStoreKey, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate json <store key> from the spec stored data with schema <schema store key> in classpath")
    public void validateJsonSchemaInClasspathWithSpecData(String jsonStoreKey, String schemaName) {
        try {
            String responseJsonString = String.valueOf(SpecDataStore.get(jsonStoreKey));
            jsonSchemaValidatiorInClasspath(responseJsonString, schemaName);
            log.info(VALIDATE, jsonStoreKey, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate json <store key> from the suit stored data with schema <schema store key> in classpath")
    public void validateJsonSchemaInClasspathWithSuitData(String jsonStoreKey, String schemaName) {
        try {
            String responseJsonString = String.valueOf(SuiteDataStore.get(jsonStoreKey));
            jsonSchemaValidatiorInClasspath(responseJsonString, schemaName);
            log.info(VALIDATE, jsonStoreKey, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate response with  schema <schema store key> in classpath according draft4")
    public void validateJsonSchemaInClasspathAccordingDraft4(String schemaName) throws NullResponse {
        try {
            jsonSchemaValidatiorWithDraft4(schemaName);
            log.info(VALIDATE_2, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate response with  schema <schema store key> in classpath according draft3")
    public void validateJsonSchemaInClasspathAccordingDraft3(String schemaName) throws NullResponse {
        try {
            jsonSchemaValidatiorWithDraft3(schemaName);
            log.info(VALIDATE_2, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }

    @Step("Validate response with  schema <schema store key> in classpath according hyper schema")
    public void validateJsonSchemaInClasspathAccordingHyperSchema(String schemaName) throws NullResponse {
        try {
            jsonSchemaValidatiorWithHyperSchema(schemaName);
            log.info(VALIDATE_2, schemaName);
        } catch (Exception e) {
            log.error(ERROR_OCCURRED, e.getMessage());
            throw e;
        }
    }
}
