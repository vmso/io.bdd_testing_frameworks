package helper;

import enums.RequestInfo;
import exceptions.NullResponse;
import exceptions.NullValue;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

import java.lang.reflect.Type;
import java.util.List;

import static enums.DocumentType.JSON;
import static enums.DocumentType.XML;
import static enums.RequestInfo.RESPONSE;

public class ResponseBodyHelper {

    private final Logger log = LogManager.getLogger(ResponseBodyHelper.class);

    /**
     * Gets response as String object
     *
     * @return is response as String
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected String getResponseAsString() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.info);
            return response.then().extract().asString();
        } catch (Exception e) {
            log.warn("Response could not get as String \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets response as JsonPath object
     *
     * @return is response as JsonPath
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    private JsonPath getResponseAsJsonPath() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.info);
            return response.then().extract().jsonPath();
        } catch (Exception e) {
            log.warn("Response could not get as JsonPath \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets response as XmlPath object
     *
     * @return is response as XmlPath
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    private XmlPath getResponseAsXmlPath() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.info);
            return response.then().extract().xmlPath();
        } catch (Exception e) {
            log.warn("Response could not get as XmlPath \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Return an json element from json response by selector
     *
     * @param selector Search json element with this selector
     * @return is json element as Object
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected Object getJsonPathValue(String selector) throws NullResponse {
        try {
            return getResponseAsJsonPath().get(selector);
        } catch (Exception e) {
            log.warn("Json element could not find by {} \n Exception detail:\n {}", selector, e.getMessage());
            throw e;
        }
    }

    /**
     * Return an xml element from xml response by selector
     *
     * @param selector Search xml element with this selector
     * @return is xml element as String
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected String getXmlPathValue(String selector) throws NullResponse {
        try {
            return getResponseAsXmlPath().get(selector);
        } catch (Exception e) {
            log.warn("Xml element could not find by {} \n Exception detail:\n {}", selector, e.getMessage());
            throw e;
        }

    }

    public Object getResponseElement(String selector) throws NullResponse, NullValue {
        String body = getResponseAsString();
        DocumentHelper documentHelper = new DocumentHelper();
        if (documentHelper.isJsonOrXml(body) == JSON) {
            Object value = getJsonPathValue(selector);
            if (value == null)
                throw new NullValue(selector);
            return getJsonPathValue(selector);
        } else if (documentHelper.isJsonOrXml(body) == XML) {
            Object value = getJsonPathValue(selector);
            if (value == null)
                throw new NullValue(selector);
            return getXmlPathValue(selector);
        } else {
            return null;
        }
    }

    protected Object getResponseElementEvenNull(String selector) throws NullResponse {
        String body = getResponseAsString();
        DocumentHelper documentHelper = new DocumentHelper();
        if (documentHelper.isJsonOrXml(body) == JSON)
            return getJsonPathValue(selector);
        else if (documentHelper.isJsonOrXml(body) == XML)
            return getXmlPathValue(selector);
        else
            return null;
    }

    /**
     * response Check if response is null.
     *
     * @throws NullResponse if response is null this exception will throw
     */
    protected void checkIfResponseNull() throws NullResponse {
        if (StoreApiInfo.get(RESPONSE.info) == null) {
            log.error("Response yok.");
            throw new NullResponse();
        }
    }

    protected <T> List<T> getListFromResponse(String jsonkey) {
        var response = (Response) StoreApiInfo.get(RESPONSE.info);
        return response.getBody().jsonPath().getList(jsonkey);
    }

}
