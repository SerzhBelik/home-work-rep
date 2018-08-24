package HomeWork2;



public class Main {

    public static void main(String[] args) {

        String[][] arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr1 = {{"1", "2", "3", "f"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr2 = {{"1", "2", "3", "f"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"} };

            System.out.println("Сумма элементов массива: "+converStringArrayToIntAndSum(arr));
            System.out.println("Сумма элементов массива: "+converStringArrayToIntAndSum(arr1));
            System.out.println("Сумма элементов массива: "+converStringArrayToIntAndSum(arr2));





    }

    public static int converStringArrayToIntAndSum (String[][] arr) {
        int sum =0;
        int dataExcY;
        int dataExcX;
        try {
            if(arr.length!=4||arr[0].length!=4) throw new MyArraySizeException("Размер массива задан не верно!");

            for (int i = 0; i<arr.length; i++){
                for(int j = 0; j<arr[0].length; j++){

                        if(!isNumeric((arr[i][j]))) {
                            dataExcY = i;
                            dataExcX = j;
                            throw new MyArrayDataException(dataExcY, dataExcX);
                        }
                        sum += Integer.parseInt(arr[i][j]);

                }
            }

        } catch (MyArraySizeException e){

        }catch (MyArrayDataException e){
           sum =0;
        }


        return sum;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
