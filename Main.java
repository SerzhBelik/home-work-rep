package HomeWork5;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
	arrChange();
	arrChange2();
    }



    private static void arrChange() {
        float[] arr = new float[size];

        for (float i:arr) {
            arr[(int) i] = 1;
        }

        long a = System.currentTimeMillis();

        for (float i:arr) {
            arr[(int)i] = (float)(arr[(int)i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);
    }

    private static void arrChange2() {
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        for (float i:arr) {
            arr[(int) i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        MyThread thread1 = new MyThread(a1);
        MyThread thread2 = new MyThread(a2);
        thread1.start();
        thread2.start();
gdfgdfg
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);

    }
}

