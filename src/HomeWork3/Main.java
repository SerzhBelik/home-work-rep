package HomeWork3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	String[] names = {"Andreev", "Ivanov", "Anikeev", "Volodin", "Maximov", "Smirnov", "Antonov", "Andreev", "Anikeev", "Petrov",
                      "Sergeev", "Alexandrov", "Volodin", "Maximov", "Alexandrov", "Ivanov", "Petrov", "Andreev", "Ivanov", "Smirnov"};

	List<String> lastnames = Arrays.asList(names);

	getUniqueLastname(lastnames);

	
    }

    private static void getUniqueLastname(List<String> lastames) {
        HashSet<String> ln = new HashSet<>(lastames);
        System.out.println(ln);
    }


}
