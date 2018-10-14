package HomeWork5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClassTester {

    private static Account account = null;
    private static List<Method> methodsList = new ArrayList<>();
    private static Logger log = Logger.getLogger(ClassTester.class.getName());

    public static void main(String[] args) {
        Class cl = null;
        try {
            cl = Class.forName("HomeWork5.Account");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        start(cl);

    }

    private static void start(Class cl) {
        Method[] methods = cl.getMethods();
        checkBeforeAndAfterSuit(methods);
        beforeSuite(cl, methods);
        test(account, methods);
        afterSuite(account, methods);

    }

    private static void checkBeforeAndAfterSuit(Method[] methods) {
        boolean beforeSuiteFlag = false;
        boolean afterSuiteFlag = false;

        for(Method m : methods){
            if (m.isAnnotationPresent(BeforeSuite.class)){
                if (beforeSuiteFlag) throw  new RuntimeException("More than one BeforeSuite method");
                beforeSuiteFlag = true;
            }
            if (m.isAnnotationPresent(AfterSuite.class)){
                if (afterSuiteFlag) throw  new RuntimeException("More than one AfterSuite method");
                afterSuiteFlag = true;
            }
        }
    }


    private static void beforeSuite(Class cl, Method[] methods) {
        for (int i = 0; i < methods.length; i++){
            if (methods[i].isAnnotationPresent(BeforeSuite.class)){
                try {
                    account = (Account) methods[i].invoke(cl, "Firstname", "Lastname");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test (Account account, Method[] methods){
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(Test.class)) methodsList.add(methods[i]);
            }

            Method[] testMethod = methodsList.toArray(new Method[methodsList.size()]);
        for(int i = testMethod.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            if(testMethod[j].getAnnotation(Test.class).priority() < testMethod[j+1].getAnnotation(Test.class).priority()){
                Method tmp = testMethod[j];
                testMethod[j] = testMethod[j+1];
                testMethod[j+1] = tmp;
                }
            }
        }
        try {
            log.info("tested " + testMethod[0].getName());
            testMethod[0].invoke(account, 5);
            log.info("tested " + testMethod[1].getName());
            testMethod[1].invoke(account, 10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void afterSuite(Account account, Method[] methods) {
        for (int i = 0; i < methods.length; i++){
            if (methods[i].isAnnotationPresent(AfterSuite.class)){
                try {
                    methods[i].invoke(account);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
