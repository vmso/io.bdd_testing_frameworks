Feature:
  test

  Scenario Outline: Install App
    Given Get <device> capabilities from resource with JSON "devices/device_capabilities" file and lunch app
    Examples:
      | device                |
      | "iPhone_13_toInstall" |
      | "Pixel_2_toInstall"   |


  Scenario Outline:
    Given Get <device> capabilities from resource with JSON "devices/device_capabilities" file and lunch app
    And Click the "allow" element if exists
    And Click the "close_login_button" element
    And Click the "sleep_better" element
    Examples:
      | device                |
      | "Pixel_2_installed"   |
      | "iPhone_13_installed" |
