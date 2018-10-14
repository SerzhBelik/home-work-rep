package HomeWork5;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private static long idCount = 0;
    private long id;
    private String firstName;
    private String lastName;
    private double amount = 0;
    private static int mapKey;
    Map<Integer, Boolean> testStatusMap = new HashMap<>();

    private Account (String firstName, String lastName){
        this.id = idCount++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @BeforeSuite
    public static Account initAcc(String firstName, String lastName){
        mapKey = 0;
        return new Account(firstName, lastName);
    }

    @Test(priority = 5)
    public void testAmountIncrease (int incr){
        double exp = getAmount() + incr;
        double act = amountIncrease(incr);
        test(exp, act);
    }

    @Test(priority = 1)
    public void testInterestIncrease(int interest){
        double exp = getAmount()*(((double)interest/100)+1);
        double act = interestIncrease(interest);
        test(exp, act);
    }

    private double interestIncrease(double interest) {
        return this.amount*(((double)interest/100)+1);
    }

    public double amountIncrease(int incr){
        return this.amount += incr;
    }

    @AfterSuite
    public void testEnd(){
        for (int i = 0; i < testStatusMap.size(); i++){
            System.out.println("test " + (i + 1) + " " + testStatusMap.get(i));
        }
        testStatusMap.clear();
    }


    public double getAmount() {
        return amount;
    }

    private void test (double exp, double act){
        testStatusMap.put(mapKey, act == exp);
        System.out.println("test status " + testStatusMap.get(mapKey));
        System.out.println("expected: " + exp);
        System.out.println("actual: " + act);
        mapKey++;
    }

}
