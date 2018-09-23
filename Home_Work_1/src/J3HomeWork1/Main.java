package J3HomeWork1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    String[] arr1 = {"Ivan" , "Petr", "Anton"};
    Integer[] arr2 = {1, 2 , 3};

// задание №1
	changeIndexes(arr1, 0, 2); // {"Anton", "Petr", "Ivan" }
	changeIndexes(arr2, 0, 2); // {3, 2 , 1}
// задание №2
	ArrayList list1 = convertArrayToList(arr1);
	ArrayList list2 = convertArrayToList(arr2);
// задание №3
	Box<Apple> appleBox1 = new Box<>();
	appleBox1.add(new Apple((float) 0.2));
	appleBox1.add(new Apple((float) 0.1));
	appleBox1.add(new Apple((float) 0.3));

	Box<Orange> orangeBox1 = new Box<>();
	orangeBox1.add(new Orange((float) 0.3));
	orangeBox1.add(new Orange((float) 0.3));

	Box<Apple> appleBox2 = new Box<>();
	appleBox2.add(new Apple());
	appleBox2.add(new Apple());

	System.out.println(appleBox1.compare(orangeBox1)); // true
    System.out.println(appleBox1.compare(appleBox2)); // false

    appleBox1.printContentOfBox();
//        Apple weight: 0.2
//        Apple weight: 0.1
//        Apple weight: 0.3
    appleBox2.printContentOfBox();
//        Apple weight: 1.0994171
//        Apple weight: 0.71046984
    appleBox1.moveFromAnotherBox(appleBox2);
    appleBox1.printContentOfBox();
//        Apple weight: 0.2
//        Apple weight: 0.1
//        Apple weight: 0.3
//        Apple weight: 1.0994171
//        Apple weight: 0.71046984



//    orangeBox1.moveFromAnotherBox(appleBox1);
//    Error, expected parameter is Box<Orange>
    }

    public  static <Arr> void changeIndexes( Arr[] arr, int index1, int index2){
        Arr tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static <Arr> ArrayList convertArrayToList(Arr[] arr){
        ArrayList<Arr> list = new ArrayList<>();
        for (Arr a : arr) {
            list.add(a);
        }
        return list;


    }
}
