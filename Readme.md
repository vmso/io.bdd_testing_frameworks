# Test Automation Framework

This framework is an open-source tool to combine API test-automation, UI test-automation, Mobile test-automation into a single, unified framework. It is based on the many steps (key-words), so you can create test cases using these steps even you don't have codding experiences.

### Table of Contents
1- [Api Test Framework](#api)
- 1.1 [Request Steps](#requestSteps)    
    - [Defining api request](#step1)
    - [Adding base url](#step2)
    - [Adding base path](#step3)
    - [Adding path parameter](#step4)
    - [Adding Headers](#step5)
    - [Adding Query Parameter](#step6)
    - [Adding Form Parameter](#step7)
    - [Adding Multi form parameter](#step8)
    - [Adding file as multi-part param](#step9)
    - [Adding log filter with error status](#step10)
    - [Adding proxy to the request ](#step11)
    - [Adding relaxed HTTPS validation](#step12)
    - [Adding the Payload/Body](#step13)
    - [Uploading the Payload/Body](#step14)
    - [Adding Authentication method](#step15)
    - [Storing json file from class](#step16)
    - [Sending the GET http request](#step17)
    - [Sending the POST http request](#step18)
    - [Sending the PUT http request](#step19)
    - [Sending the DELETE http request](#step20)
    - [Sending the PATCH http request](#step21)
    - [Sending the OPTIONS http request](#step22)
    - [Sending the HEAD http request](#step23)

  
- 1.2 [Response Steps](#responseSteps)
    - [Checking Response Status Code](#responseStep1)
    - [Getting response time and comparing with provided value](#responseStep2)
    - [Validating Response Json Schema](#responseStep3)
    - [Storing response body as String](#responseStep4)
    - [Storing response body as json](#responseStep5)
    - [Getting the specified selector from response and store it](#responseStep6)
    - [Getting the specified selector from response and then check if is not null](#responseStep7)
    - [Getting the specified selector from response and then convert it to list and store it](#responseStep8)
    - [Getting the specified Json selector value from json response which have filter and filter value then store it](#responseStep9)
    - [Storing key value from the json response as variable](#responseStep10)
    - [Storing key value table from the json response as variable](#responseStep11)
    - [Getting the specified selector value from response then compare it with provided value if they equals or not](#responseStep12)
    - [Getting the specified selector value from response then in terms of containing compare it with the provided value ](#responseStep13)

2- [Mobile Project](#api)

... preparing

3- [Web Project](#api)

... preparing

<a name='api'></a>
### Api Test Framework

<a name='requestSteps'></a>
### Request Steps

<a name='step1'></a>
##### 1- Defining api request
To define a new api request you can use following step

```* Define new request```
For a check on the implementation of defining a new request step, please visit [RequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/RequestImp.java#L1) class.

<a name='step2'></a>
##### 2- Add base url
The following step can be used to add the base url to the request.
P.S You should pass the base url as a parameter.

```* Add base url <url>```
For a check on the implementation of adding base url step, please visit [UrlImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/UrlImp.java#L1) class.


<a name='step3'></a>
##### 3- Adding base path (endpoint)
The following step can be used to add the base path to the request.

```* Add base path <url>```
to check implementation of adding base path(endpoint) step, please check [UrlImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/UrlImp.java#L1) class.


<a name='step4'></a>
##### 4- Adding path parameter
The following step can be used to add the path parameter to the request.

```* Add base path "/v2/pet/{patId}"```

```* Add path parameter "patId" = "1234".```

You can check more options to add path parameters and review how the steps are implemented by clicking here [PathParamImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/PathParamImp.java#L1) class.

<a name='step5'></a>
##### 5- Adding Headers
The following step can be used to add headers to the request.
```
* Add Headers
    |key            |value              |
    |---------------|-------------------|
    |accept         |application/json   |
    |Content-Type   |application/json   |
    |Cache-Control  |max-age=0          |
```
You can check more options to add headers and review how the steps are implemented by clicking here [HeaderImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/HeaderImp.java#L1) class.

<a name='step6'></a>
##### 6- Query Parameter
The following step can be used to add the query parameters to the request.

```
* Add query parameter
  |key            |value              |
  |---------------|-------------------|
  |patId          |1234               |
  |category       |dog                |
```
You can check more options to add query parameters and review how the steps are implemented by clicking here [QueryParamImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/QueryParamImp.java#L1) class.

<a name='step7'></a>
##### 7- Form Parameter
The following step can be used to add the form parameters to the request.

```
* Add query parameter
  |key            |value              |
  |---------------|-------------------|
  |patId          |1234               |
  |category       |dog                |
```
You can check more options to add form data parameter and review how the steps are implemented by clicking here  [FormParamImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/FormParamImp.java#L1) class.

<a name='step8'></a>
##### 8- Multi form parameter (endpoint)
The following step can be used to add multi form parameter to the request.

```* Add to request "key"="value" as multi-part form data```

You can check more options to add multi form parameter and review how the steps are implemented by clicking here [MultiPartFormDataImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/MultiPartFormDataImp.java#L1) class.

<a name='step9'></a>
##### 9- Adding file as multi part form param

To adding a file to request, first, you should add the file under the [file](https://github.com/vmso/io.bdd_testing_frameworks/tree/master/api_testing/src/test/resources/files) directory in test resource.
Then you can pass the file name as parameter to add to request as multi form parameter with following step.

```* Get "<fileName>" file and add to request as multi-part form data```

You can check more options to add file as multi-part form data and review how the steps are implemented by clicking here [MultiPartFormDataImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/MultiPartFormDataImp.java#L1) class.

<a name='step9'></a>
##### 9- Adding file as multi part form param

To adding a file to request, first, you should add the file under the [file](https://github.com/vmso/io.bdd_testing_frameworks/tree/master/api_testing/src/test/resources/files) directory in test resource.
Then you can pass the file name as parameter to add to request as multi form parameter with following step.

```* Get "<fileName>" file and add to request as multi-part form data```

You can check more options to add file as multi-part form data and review how the steps are implemented by clicking here [MultiPartFormDataImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/MultiPartFormDataImp.java#L1) class.

<a name='step10'></a>
##### 10- Adding log filter with error status table

The following step can be used to add log filter with error status table.

```
* Add log filter with errorStatus
  |Status |
  |500    |
  |400    |
  |405    |
```
You can check more options to add log filter with error status table and review how the steps are implemented by clicking here [LogImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/LogImp.java#L1) class.

<a name='step11'></a>
##### 11- Adding proxy to the request with URL

The following step can be used to add proxy to the request with URL.

``` * Add proxy to request with "<url key>" ```

You can check more options to add proxy to the request with URL and review how the steps are implemented by clicking here [ProxyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ProxyImp.java#L1) class.

<a name='step11'></a>
##### 11- Adding proxy to the request with string and int key

The following step can be used to add proxy to the request with string and int key.

``` * Add proxy to request with "<string key>" and "<int key>" ```

You can check more options to add proxy to the request with string and int key and review how the steps are implemented by clicking here [ProxyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ProxyImp.java#L1) class.

<a name='step12'></a>
##### 12- Adding relaxed https validation

The following step can be used to add default https certification and relaxed https validation

``` * Add relaxed HTTPS validation ```

to check implementation of adding base url step, please visit [HttpsImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/bf4efcca584de9cfeeea06e5cd22863bdc6b3afa/mutual_methods/src/main/java/imp/HttpsImp.java#L1) class.

<a name='step13'></a>
##### 13- Adding payload/Body as String from file resource 

The following step can be used to add the payload/Body as a String from the file under the payloads directory ın test the resource
``` * Add payload as String from resource "<file name>" ```

You can check more options to add payload/Body as String from file resource and review how the steps are implemented by clicking here [RequestBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/RequestBodyImp.java#L1) class.

<a name='step13'></a>
##### 13- Adding payload/Body as File from file resource

The following step can be used to add payload/Body as File from file resource

``` * Add payload as file from resource "<file name>" ```

You can check more options to add payload/Body as File from file resource and review how the steps are implemented by clicking here [RequestBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/RequestBodyImp.java#L1) class.

<a name='step13'></a>
##### 13- Adding payload/Body as Map 

The following step can be used to add payload/Body as Map

``` * Add payload as map ```

You can check more options to add payload/Body as Map and review how the steps are implemented by clicking here [RequestBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/RequestBodyImp.java#L1) class.

<a name='step13'></a>
##### 13- Adding payload/Body from Scenario Store with key

The following step can be used to add payload/Body from Scenario Store with key

``` * Add payload from scenario store with "<key>" ```

You can check more options to add payload/Body from Scenario/Suit/Spec Store with key and review how the steps are implemented by clicking here [RequestBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/RequestBodyImp.java#L1) class.

<a name='step14'></a>
##### 14- Updating the body from stored scenario with key in selector and value shape

The following step can be used to update the body from stored scenario with key in selector and value shape

``` * Update "<selector>"="<value>" json from stored scenario with key "<key>" ```

You can check more options to update the body from stored scenario with key and review how the steps are implemented by clicking here [RequestBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/RequestBodyImp.java#L1) class.

<a name='step15'></a>
##### 15- Adding basic authentication method with username and password

The following step can be used to add basic authentication method with username and password

``` * basic auth with "<username>" and "<password>" ```

You can check more options to add basic authentication method with username and password and review how the steps are implemented by clicking here [AuthImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/AuthImp.java#L1) class.

<a name='step15'></a>
##### 15- Adding bearer token for bearer authentication

The following step can be used to add bearer authentication with bearer token

``` * Add Bearer token "<token>" ```

You can check more options to add bearer authentication method with bearer token and review how the steps are implemented by clicking here [AuthImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/AuthImp.java#L1) class.

<a name='step15'></a>
##### 15- Adding bearer token from scenario store with key

The following step can be used to add bearer authentication with bearer token from scenario store with key

``` * Add Bearer token from scenario store "<key>" ```

You can check more options to add bearer authentication method with bearer token from scenario/suit/spec store with key and review how the steps are implemented by clicking here [AuthImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/AuthImp.java#L1) class.

<a name='step16'></a>
##### 15- Storing the value of json file from classpath with key during scenario

The following step can be used to store the value of json file from classpath with key during scenario

``` * Store json "<file name>"'s value from classpath with "<key>" during scenario ```

You can check more options to store the value of json file from classpath with key during scenario/suit/spec and review how the steps are implemented by clicking here [VariableImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/VariableImp.java#L1) class.

<a name='step17'></a>
##### 17- Sending the GET http request

The following step can be used to send the get http request

``` * Get request```

You can check more options to send the get http request and review how the steps are implemented by clicking here [GetRequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/GetRequestImp.java#L1) class.


<a name='step18'></a> 
##### 18- Sending the POST http request

The following step can be used to send the post http request

``` * Post request```

You can check more options to send the post http request and review how the steps are implemented by clicking here [PostRequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/PostRequestImp.java#L1) class.

<a name='step19'></a>
##### 19- Sending the PUT http request

The following step can be used to send the put http request

``` * Put request```

You can check more options to send the put http request and review how the steps are implemented by clicking here [PutRequestsImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/PutRequestsImp.java#L1) class.

<a name='step20'></a>
##### 20- Sending the DELETE http request

The following step can be used to send the delete http request

``` * Delete request```

You can check more options to send the delete http request and review how the steps are implemented by clicking here [DeleteRequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/DeleteRequestImp.java#L1) class.

<a name='step21'></a>
##### 21- Sending the PATCH http request

The following step can be used to send the patch http request

``` * Patch request```

You can check more options to send the patch http request and review how the steps are implemented by clicking here [PatchRequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/PatchRequestImp.java#L1) class.

<a name='step22'></a>
##### 22- Sending the OPTIONS http request

The following step can be used to send the options http request

``` * Options request```

You can check more options to send the options http request and review how the steps are implemented by clicking here [OptionsRequestsImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/OptionsRequestsImp.java#L1) class.

<a name='step23'></a>
##### 23- Sending the HEAD http request

The following step can be used to send the head http request

``` * Head request```

You can check more options to send the head http request and review how the steps are implemented by clicking here [HeadRequestImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/methods/HeadRequestImp.java#L1) class.



<a name='responseSteps'></a>
### Response Steps

<a name='responseStep1'></a>
##### 1- Checking if the status code comes as expected

The following step can be used to check if the status code comes as expected

``` * Check if status code is "<code>" ```

You can review how the steps are implemented by clicking here [StatusCodeImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/StatusCodeImp.java#L1) class.

<a name='responseStep2'></a>
##### 2- Get the response time as seconds and compare with the provided value 

The following step can be used to get the response time as seconds and compare if its less than the value

``` * Get response time as seconds and compare it, is it less then "<seconds>"? ```

You can check more options to get the response time to compare with value and review how the steps are implemented by clicking here [ResponseTimeImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseTimeImp.java#L1) class.

<a name='responseStep3'></a>
##### 3- Validate the response json with provided String schema

The following step can be used to validate the response json with provided String schema so that we can see if the service is responding with true json schema model.

``` * Validate response json with schema "<Schema Name>" ```

You can check more options to validate the response json with provided String schema and review how the steps are implemented by clicking here [JsonSchemaImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/JsonSchemaImp.java#L1) class.

<a name='responseStep3'></a>
##### 4- Validate the stored response json key with stored schema key from scenario data store

The following step can be used to validate the stored response json key with stored schema key from scenario data store.Basically, wen are calling the stored response json and schema key from scenario data store which is prone to gauge framework.

``` * Validate stored json "<json store key>" response with stored schema "<schema store key>" from scenario data store ```

You can check more options to validate the stored response json with stored schema key from scenario/suit/spec store and review how the steps are implemented by clicking here [JsonSchemaImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/JsonSchemaImp.java#L1) class.

<a name='responseStep4'></a>
##### 5- Storing response body as string with key during scenario

The following step can be used to store response body as string with key during scenario

``` * Store response as string with "<key>" during scenario ```

You can check more options to store response body as string with key during scenario/suit/spec and review how the steps are implemented by clicking here [ResponseBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseBodyImp.java#L1) class.

<a name='responseStep5'></a>
##### 6- Storing response body as Json with key during scenario

The following step can be used to store response body as json with key during scenario

``` * Store response as json with <key> during scenario ```

You can check more options to store response body as json with key during scenario/suit/spec and review how the steps are implemented by clicking here [ResponseBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseBodyImp.java#L1) class.

<a name='responseStep6'></a>
##### 7- Getting specified selector from response and store it with key during scenario

The following step can be used to get the specified selector from response and store it with <key> during scenario

``` * Get "<selector>" from response and store it with "<key>" during scenario ```

You can check more options to get the specified selector from response and store it with <key> during scenario/suit/spec and review how the steps are implemented by clicking here [ResponseBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseBodyImp.java#L1) class.

<a name='responseStep7'></a>
##### 8- Getting specified selector from response and then check if is not null?

The following step can be used to get the specified selector from response and then check if is not null

``` * Get "<selector>" from response and then check if is not null? ```

You can check more options to get the specified selector from response and then check if its null or not null and review how the steps are implemented by clicking here [ResponseBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseBodyImp.java#L1) class.

<a name='responseStep8'></a>
##### 9- Getting the specified selector from response and then convert it to list and store it?

The following step can be used to get the specified selector from response and then convert it to list and store it

``` * Get "<selector>" from the body then convert it to list and store it with "<key>" during the scenario ```

You can check more options to get the specified selector from response and then convert it to list and store it during scenario/suit/spec and review how the steps are implemented by clicking here [ResponseBodyImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/ResponseBodyImp.java#L1) class.


<a name='responseStep9'></a>
##### 10- Getting the specified Json selector value from json response which have filter and filter value then store it

The following step can be used to get the specified Json selector value from json response which have filter and filter value then store it during scenario

``` * Get "<json select>" from "<json array>" json list which one equals "<filter>=<filterValue>", and store it during Scenario with "<key>"```

You can check more options to get the specified Json selector value from json response which have filter and filter value then store it during scenario/suit/spec and review how the steps are implemented by clicking here [JsonFilterImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/JsonFilterImp.java#L1) class.

<a name='responseStep10'></a>
##### 11- Storing key value from the json response as variable

The following step can be used to store key value from the json response as variable during scenario

``` * Store variable <key> = <value> during scenario"```

You can check more options to store key value from the json response as variable during scenario/suit/spec and review how the steps are implemented by clicking here [VariableImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/VariableImp.java#L1) class.

<a name='responseStep11'></a>
##### 11- Storing key value table from the json response as variable

The following step can be used to store key value table from the json response as variable

``` * Store table as map during scenario with "<key>" "<table>"```

You can check more options to store key value table from the json response as variable during scenario/suit/spec and review how the steps are implemented by clicking here [VariableImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/VariableImp.java#L1) class.

<a name='responseStep12'></a>
##### 12- Getting the specified selector value from response then compare it with value if they are equals or not

The following step can be used to get the specified <selector> value from response then compare it with <value> if they equals or not

``` * Get "<selector>" from response and then compare with "<value>", Are they not equals?```

You can check more options to get the specified selector value from response then compare it with value if they are equals or not and review how the steps are implemented by clicking here [CompareImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/CompareImp.java#L1) class.

<a name='responseStep13`'></a>
##### 13- Getting the specified selector value from response then in terms of containing compare it with the provided value 

The following step can be used to get specified <selector> value from response then in terms of containing compare it with the provided <value>

``` * Get "<selector>" from response and then compare with "<value>", is it contains the value?```

You can check more options to get the specified selector value from response then in terms of containing compare it with the provided value and review how the steps are implemented by clicking here [CompareImp](https://github.com/vmso/io.bdd_testing_frameworks/blob/fa184ebc892b8d1f45e2acef0ba2ef7d2db6c70f/mutual_methods/src/main/java/imp/CompareImp.java#L1) class.





