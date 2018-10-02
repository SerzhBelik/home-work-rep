package J3HomeWork2;

public class ABCPrinter {

    boolean flags[] = {true, false, false};

    public void ABCPrint (){
          new Thread(() -> {
              printLetter("A", 0);
          }).start();

        new Thread(() -> {
            printLetter("B", 1);
        }).start();

        new Thread(() -> {
            printLetter("C", 2);
        }).start();
    }

    synchronized private void printLetter(String a, int index) {
        for (int j = 0; j < 5; j++) {
            while (!flags[index]) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                System.out.println(a);
                flags[index] = false;
                if (index == 2) flags[0] = true;
                else flags[index + 1] = true;
                notifyAll();
            }
        }
    }

