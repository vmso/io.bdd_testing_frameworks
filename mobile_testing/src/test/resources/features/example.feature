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

    Scenario: android api app
      Given Get "Pixel_2_installed_ApiDemos" capabilities from resource with JSON "devices/device_capabilities" file and lunch app
      And Scroll to "Views" element
      And Click the "views" element if exists
      And Swipe up from  the "custom" until reach the "tabs"
      And Swipe down from  the "tabs" until reach the "custom"
      And Swipe up from  the "custom" until reach the "tabs"
      And Click the "tabs" element if exists
      And Click the "scrollables" element if exists
      And Swipe left from  the "tab3" until reach the "tab11"
      And Swipe right from  the "tab11" until reach the "tab3"
      And Get "tab_content" element text and store it Scenario Store with "content" key