package HomeWork5;

import java.util.Arrays;

public class ArrChanger {

    int[] newArr = null;

    public int[] cutOff (int[] oldArr, int value){
        if (oldArr[oldArr.length-1] == value) return null;
        for(int i = oldArr.length-1; i >= 0; i--){
            if(oldArr[i] == value){
                return newArr = Arrays.copyOfRange(oldArr, i+1, oldArr.length);
            }
        }
        throw new RuntimeException("value not found");
    }

    public boolean findValue(int[] arr, int ... values){
        for(int i = 0; i < arr.length; i++){
            for (int j : values){
                if (arr[i] == j) return true;
            }
        }
        return false;
    }
}
