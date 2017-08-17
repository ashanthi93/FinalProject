package settings;

import java.util.HashMap;
import java.util.Map;

public class Temp2 {

    public static void main(String args[]){

        Map<String,String[]> mappingHashMap = new HashMap<String, String[]>();

        String[] array = {"C2","C3","C4","C8","C9","C10"};
        mappingHashMap.put("A1",array);

        array = new String[]{"C5","C8","C9","C10"};
        mappingHashMap.put("A2",array);

        array = new String[]{"C3", "C4", "C8", "C9", "C10"};
        mappingHashMap.put("A3",array);

        array = new String[]{"C6", "C8", "C9", "C10"};
        mappingHashMap.put("A4",array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A5",array);

        array = new String[]{"C7", "C8", "C9", "C10"};
        mappingHashMap.put("A6",array);

        array = new String[]{"C6", "C8", "C9", "C10"};
        mappingHashMap.put("A7",array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A8",array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A9",array);

        array = new String[]{"C4", "C8", "C9", "C10"};
        mappingHashMap.put("A10",array);

        //OWASPMappingConfig owaspMappingConfig = new OWASPMappingConfig();
        //owaspMappingConfig.createConfigFile(mappingHashMap);
    }

}
