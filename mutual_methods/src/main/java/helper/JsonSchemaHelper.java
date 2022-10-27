package helper;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReuseStoreData;

import static com.github.fge.jsonschema.SchemaVersion.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonSchemaHelper extends ResponseBodyHelper {

    private final Logger log = LogManager.getLogger(JsonSchemaHelper.class);

    /**
     * This method validate response and schema as string,
     * you should give all response string and schema string
     *
     * @param json       response json
     * @param jsonSchema schema json
     */
    protected void jsonSchemaValidator(String json, String jsonSchema) {
        assertThat(json, matchesJsonSchema(jsonSchema));
    }

    /**
     * This method validate response and schema as string,
     * You should give all response string and schema filename only.
     *
     * @param json       response json
     * @param jsonSchema schema json file name
     */
    protected void jsonSchemaValidatiorInClasspath(String json, String jsonSchema) {
        assertThat(json, matchesJsonSchemaInClasspath(jsonSchema));
    }


    protected void jsonSchemaValidatiorWithSetting(String jsonSchema, SchemaVersion schemaVersion) throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
            JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                    .setValidationConfiguration(ValidationConfiguration
                            .newBuilder()
                            .setDefaultVersion(schemaVersion)
                            .freeze())
                    .freeze();
            response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchema).using(jsonSchemaFactory));
        } catch (Exception e) {
            log.warn("An error occurred message:{}", e.getMessage());
            throw e;
        }
    }

    protected void jsonSchemaValidatiorWithDraft4(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV4);
    }

    protected void jsonSchemaValidatiorWithDraft3(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV3);
    }

    protected void jsonSchemaValidatiorWithHyperSchema(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV4_HYPERSCHEMA);
    }

    protected void jsonSchemaValidator(String jsonSchemaName) throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
            response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchemaName));
        } catch (Exception e) {
            log.warn("An error occurred message:{}", e.getMessage());
            throw e;
        }
    }
}
