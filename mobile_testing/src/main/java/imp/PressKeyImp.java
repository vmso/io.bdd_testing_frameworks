package imp;

import com.thoughtworks.gauge.Step;
import helper.PressKeysHelper;

public class PressKeyImp extends PressKeysHelper {

     @Step("This <key> is pressed.")
    public void pressKeyImp(String key){
         presKeys("key");
     }

}
