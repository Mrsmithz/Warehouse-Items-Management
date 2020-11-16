package myutilities;

import java.util.*;

public class MapComparator implements Comparator<Map<String, Object>> {
    private final String key;
    public MapComparator(String key){
        this.key = key;
    }
    public int compare(Map<String, Object> first, Map<String, Object> second){
        if (key.equals("ID") || key.equals("Quantity")){
            Integer id_1 = (Integer)first.get(key);
            Integer id_2 = (Integer)second.get(key);
            return id_1.compareTo(id_2);
        }
        else if (key.equals("Name") || key.equals("Type")){
            String name_1 = (String)first.get(key);
            String name_2 = (String)second.get(key);
            return name_1.compareTo(name_2);
        }
        else if (key.equals("Price") || key.equals("Weight")){
            Double db_1 = (Double)first.get(key);
            Double db_2 = (Double)second.get(key);
            return db_1.compareTo(db_2);
        }
        else{
            return 0;
        }
    }
}
