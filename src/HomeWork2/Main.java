package HomeWork2;

import static java.lang.Float.isNaN;

public class Main {

    public static void main(String[] args) {

        String[][] arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}}; /*new String[4][4];
        arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}};*/

            System.out.println(converStringArrayToIntAndSum(arr));





    }

    public static int converStringArrayToIntAndSum (String[][] arr) {
        int sum =0;
        try {
            if(arr.length!=4||arr[0].length!=4) throw new MyArraySizeException();

            for (int i = 0; i<arr.length; i++){
                for(int j = 0; j<arr[0].length; j++){

                        if(Float.isNaN((float)Integer.parseInt(arr[i][j]))) throw new MyArrayDataException();
                        sum += Integer.parseInt(arr[i][j]);

                }
            }


        } catch (MyArraySizeException e){
            System.out.println("Размер массива задан не верно!");
        }catch (MyArrayDataException e){
            System.out.println("Элементы массива заданы не верно!");
        }


        return sum;
    }
}
