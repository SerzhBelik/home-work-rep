package HomeWork3;

import java.util.Arrays;
import java.util.HashSet;


public class Main {

    public static void main(String[] args) {
	String[] names = {"Andreev", "Ivanov", "Anikeev", "Volodin", "Maximov", "Smirnov", "Antonov", "Andreev", "Anikeev", "Petrov",
                      "Sergeev", "Alexandrov", "Volodin", "Maximov", "Alexandrov", "Ivanov", "Petrov", "Andreev", "Ivanov", "Smirnov"};

	String[] phoneNumbers = {"8-901-111-11-11", "8-901-111-11-22", "8-901-111-11-33", "8-901-111-11-44", "8-901-111-11-55",
                             "8-901-111-11-66", "8-901-111-11-77", "8-901-111-11-88", "8-901-111-11-99", "8-901-111-11-00",
                             "8-901-111-22-11", "8-901-111-33-11", "8-901-111-44-11", "8-901-111-55-11", "8-901-111-66-11",
                             "8-901-111-77-11", "8-901-111-88-11", "8-901-111-99-11", "8-901-111-00-11", "8-901-222-11-11"};


	HashSet<String> ln = new HashSet<>(Arrays.asList(names));

	getUniqueLastname(ln);

	getReiteration(ln, names);

	Phonebook phonebook = new Phonebook();

	for (int i = 0; i<names.length; i++){
	    phonebook.add(names[i], phoneNumbers[i]);
    }

    phonebook.get("Ivanov");

    }





    private static void getReiteration(HashSet<String> ln, String[] names) {

        for (String lastname:ln) {
            int reiterations= 0;
            for (String name:names){
                if (lastname.equals(name)) reiterations++;
            }
            System.out.println(lastname+" встречается " + reiterations + "раз(а)");
        }
        System.out.println();
    }



    private static void getUniqueLastname(HashSet<String> ln) {

        System.out.println(ln + "\n");
    }


}
