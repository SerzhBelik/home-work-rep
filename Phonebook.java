package HomeWork3;

import java.util.*;

public class Phonebook {
    Map<String, List<String>> phoneMap = new HashMap<>();


    void add(String k, String v){
        if(this.phoneMap.containsKey(k)) {
            phoneMap.get(k).add(v);
            return;
        }
        List<String> tempList = new ArrayList<>();
        tempList.add(v);
        phoneMap.put(k, tempList);
    }

    void get(String k){
        System.out.println(k);
        for (String s : phoneMap.get(k)){
            System.out.println(s);
        }
    }



}