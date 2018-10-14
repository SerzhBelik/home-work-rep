package HomeWork5;

public class ClassTester {

    public static void main(String[] args) {
	start("Account");
	Account account = Account.initAcc("A", "B");

	account.testAmountIncrease(5);
	account.testInterestIncrease(10);
	account.testEnd();
    }



    private static void start(String className) {
    }
}
