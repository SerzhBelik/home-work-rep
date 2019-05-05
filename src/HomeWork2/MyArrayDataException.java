package HomeWork2;

public class MyArrayDataException extends Exception {
    MyArrayDataException(int y, int x) {
        System.out.println("Не верное значение в яйчейке [" + y + "][" + x + "]");
    }
}
