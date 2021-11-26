# Scenario


|device_to_install  |installed_device     |
|-------------------|---------------------|
|iPhone_13_toInstall| iPhone_13_installed |
|Pixel_2_toInstall  | Pixel_2_installed   |

## Install Appx
* Get <device_to_install> capabilities from resource with JSON "devices/device_capabilities" file and lunch app



## Installed App
* Get <installed_device> capabilities from resource with JSON "devices/device_capabilities" file and lunch app
* Click the "allow" element if exists
* Click the "close_login_button" element
* Click the "sleep_better" element


## android api app
* Get "Pixel_2_installed_ApiDemos" capabilities from resource with JSON "devices/device_capabilities" file and lunch app
* Scroll to "Views" element
* Click the "views" element if exists
* Swipe up from  the "custom" until reach the "tabs"
* Swipe down from  the "tabs" until reach the "custom"
* Swipe up from  the "custom" until reach the "tabs"
* Click the "tabs" element if exists
* Click the "scrollables" element if exists
* Swipe left from  the "tab3" until reach the "tab11"
* Swipe right from  the "tab11" until reach the "tab3"
* Get "tab_content" element text and store it Scenario Store with "content" key

## try sendkeys
* Get <device_to_install> capabilities from resource with JSON "devices/device_capabilities" file and lunch app
* Click the "ok" element if exists
* Click the "allow" element if exists
* Click the "continue_with_mail" element if exists
* Click the "mail" element if exists
* Send "serhat.ozdursun@gmail.com" text to the "mail" element

