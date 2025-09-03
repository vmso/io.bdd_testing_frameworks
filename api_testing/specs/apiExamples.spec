#Specification Heading

##pet post
* Define new request
* Add base url "https://petstore.swagger.io"
* Add payload as file from resource "payloads/petPost.json"
* Add relaxed HTTPS validation
* Add endpoint "v2/pet"
* Add Headers
    |key            |value              |
    |---------------|-------------------|
    |accept         |application/json   |
    |Content-Type   |application/json   |
    |Cache-Control  |max-age=0          |
* Post request
* Check if status code is "200"
* Validate response json with schema "petSchema.json"
* Get "photoUrls[0]" from response and then compare with "https://www.karabas.com/karabas.jpg", Are they equals?
* Get "tags[1].id" from response and then compare with "3", Are they equals?
* Print last response

##pet post 2

* Add payload as file from resource "payloads/petPost.json"
* Add base url "https://petstore.swagger.io"
* Add relaxed HTTPS validation
* Add endpoint "/v2/pet"
* Add Headers
    |key            |value              |
    |---------------|-------------------|
    |accept         |application/json   |
    |Content-Type   |application/json   |
    |Cache-Control  |max-age=0          |
* Add log filter with errorStatus
    |Status |
    |500    |
    |400    |
    |405    |
* Post request
* Get "tags" from response and store it with "tags" during scenario
* Get "123" and "123" from scenario store and then compare, Are they equals?
* Get "id" from "tags" json list which one equals "name"="kuçukuçu", and store it during Scenario with "tagId"
* Get response time as milliseconds and compare it, is it less then "50000"?
* Sleep for "120" milliSecond
* Check if status code is "200"

## String helper test
* Store variable "key1" = "Tast\n\bRegex" during scenario
* Store variable "FirstTest" = "First Test First Repalce" during suite
* Store variable "Un RegEx" = "//[] test" during spec
* Replace "[\\nb]" old chars to new " " chars on "key1" text and store it on Scenario store with "key2" key
* Replace "a" old chars to new "e" chars on "Tast" text and store it on Scenario store with "key1" key
* Replace "First " old chars to new "" chars on "FirstTest" text but only first and store it on Scenario store with "key 3" key
* Replace "//[] " old chars to new "on iki" chars on "Un RegEx" text and store it on Scenario store with "key4" key
* Substring text "FirstTest" from "6" index to "24" index then store it in Scenario store with "key" key
* Substring text "FirstTest" from "6" index to the end then store it in Scenario store with "key" key
* Substring text "FirstTest" from "6" index to the end then store it in Spec store with "key" key
* Substring text "FirstTest" from "6" index to the end then store it in Suit store with "key" key



