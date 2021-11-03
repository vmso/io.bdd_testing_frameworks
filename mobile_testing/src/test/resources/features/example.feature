Feature:
  test

  Scenario: lunch App
    Given Get 'iPhone_12_Pro_Max.capabilities' capabilities from resource with JSON 'devices/device_capabilities' file and lunch 'iOS' platform
    And Check if 'app.meditopia' exists, remove it if it does and then reinstall from '/Users/testinium/Desktop/meditopia.app'
