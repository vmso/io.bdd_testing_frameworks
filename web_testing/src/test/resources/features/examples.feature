Feature:
  Test

  Scenario Outline:
    Given Open '<Browsers>' and get 'https://courses.letskodeit.com/practice'
    And Get "table" table list map according to "headers" and store it in scenario data store with "dataTable" key
    And Get "table" table list map according to below headers and store it in suit data store with "dataTable" key
      | Author  |
      | Course |
      | Price  |
    Examples:
      | Browsers |
      | Chrome   |
      | Firefox  |
      | Opera    |
      | Safari   |