package HomeWork2;

public class Main {

    public static void main(String[] args) {

        String[][] arr = new String[4][4];
        try{
            converStringArrayToInt(arr);
        } catch (MyArraySizeException e){
            System.out.println("Размер массива задан не верно!");
        }




    }

    public static int converStringArrayToInt (String[][] arr) throws MyArraySizeException {

        if(arr.length!=4||arr[0].length!=4) throw new MyArraySizeException();
        return 1;//FIXME
    }
}
