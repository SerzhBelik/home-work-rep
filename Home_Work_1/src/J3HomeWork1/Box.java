package J3HomeWork1;

import java.util.ArrayList;

public class Box<F extends Fruit> {
    private ArrayList<F> contentOfBox = new ArrayList<>();
    private float totalWeight = 0;


    public void add(F fruit){
        contentOfBox.add(fruit);
        this.totalWeight += fruit.getWeight();
    }

    public float getWeight(ArrayList<F> contentOfBox){
        return totalWeight;
    }

    public boolean compare (Box box){
        return this.totalWeight == box.totalWeight;
    }

    public void moveFromAnotherBox(Box<F> movedBox){
        if (this == movedBox) return;
        int s = movedBox.contentOfBox.size();
        for (int i = 0; i < s; i++){
            F f = movedBox.contentOfBox.get(0);
            this.add(movedBox.contentOfBox.get(0));
            movedBox.contentOfBox.remove(0);
        }
    }

    public void printContentOfBox(){
        String s;
        for (F fruit : this.contentOfBox){
            s = fruit.getClass().getTypeName();
            System.out.println(s.substring((s.lastIndexOf("."))+1) + " weight: " + fruit.getWeight());
        }
        System.out.println();
    }
}
