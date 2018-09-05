package HomeWork5;

public class MyThread extends Thread {
    float arr[];

    public MyThread(float[] a) {
        this.arr = a;
    }

    @Override
    public void run(){
        for (float i:this.arr) {
            this.arr[(int) i] = (float) (this.arr[(int) i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
