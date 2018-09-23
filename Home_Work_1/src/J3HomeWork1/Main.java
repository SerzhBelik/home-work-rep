package J3HomeWork1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    String[] arr1 = {"Ivan" , "Petr", "Anton"};
    Integer[] arr2 = {1, 2 , 3};

	changeIndexes(arr1, 0, 2);
	changeIndexes(arr2, 0, 2);
    }

    public  static <Arr> void changeIndexes( Arr[] arr, int index1, int index2){
        Arr tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
