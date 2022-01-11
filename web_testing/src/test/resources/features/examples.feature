Feature:
  Test

  Scenario Outline:
    Given Open '<Browsers>' and get 'https://courses.letskodeit.com/practice'
    And Get "table" table list map according to "headers" and store it in scenario data store with "dataTable" key
    And Get "table" table list map according to below headers and store it in suit data store with "dataTable" key
      | Author |
      | Course |
      | Price  |
    And Multiple select below indexes of "multiple_select" option
      | 0       |
      | 1       |
    Examples:
      | Browsers |
      | Chrome   |
      | Firefox  |
    
    Scenario: default url
      Given Open "chrome" and get base url

  Scenario: default url and browser
    Given Open browser and get base url