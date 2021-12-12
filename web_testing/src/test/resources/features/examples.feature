Feature:
  Test

  Scenario Outline:
    Given Open '<Browsers>' and get 'https://courses.letskodeit.com/practice'
    And Get "table" table list map according to "headers" and store it in scenario data store with "dataTable" key
    Examples:
      | Browsers |
      | Chrome   |
      | Firefox  |
      | Opera    |
      | Safari   |