package J3HomeWork1;

import java.util.Random;

abstract  public class Fruit {
    private float weight;

    public Fruit(){
        this.weight = (float) 0.15 + (new Random().nextFloat());
    }

    public Fruit (float weight){
        this.weight = weight;
    }

    public float getWeight(){
        return this.weight;
    }
}
