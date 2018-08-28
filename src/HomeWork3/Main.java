package HomeWork3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	String[] names = {"Andreev", "Ivanov", "Anikeev", "Volodin", "Maximov", "Smirnov", "Antonov", "Andreev", "Anikeev", "Petrov",
                      "Sergeev", "Alexandrov", "Volodin", "Maximov", "Alexandrov", "Ivanov", "Petrov", "Andreev", "Ivanov", "Smirnov"};

	List<String> lastnames = Arrays.asList(names);
	HashSet<String> ln = new HashSet<>(lastnames);

	getUniqueLastname(ln);

	getReiteration(ln, names);
    }



    private static void getReiteration(HashSet<String> ln, String[] names) {

        for (String lastname:ln) {
            int reiterations= 0;
            for (String name:names){
                if (lastname.equals(name)) reiterations++;
            }
            System.out.println(lastname+" встречается " + reiterations + "раз(а)");
        }
    }


    
    private static void getUniqueLastname(HashSet<String> ln) {

        System.out.println(ln);
    }


}
