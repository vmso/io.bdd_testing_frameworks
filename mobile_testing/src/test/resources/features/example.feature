Feature:
  test

  Scenario: Click App
    Given Get "iPhone_12_Pro_Max_2" capabilities from resource with JSON "devices/device_capabilities" file and lunch app
    And Click the 'sleep_better' element


  Scenario Outline: Close and click sleep
    Given Get <device> capabilities from resource with JSON "devices/device_capabilities" file and lunch app
    And Click the "close_login_button" element if exists
    And Click the 'sleep_better' element
    Examples:
      | device      |
      | "Pixel_2"   |
      | "iPhone_13" |



