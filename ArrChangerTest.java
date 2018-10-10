package HomeWork5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrChangerTest {

    ArrChanger arrChanger;

    @Before
    public  void init(){
        arrChanger = new ArrChanger();

    }

    @Test(expected = RuntimeException.class)
    public void cutOff() {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {1, 2, 3, 3, 5, 6, 7};
        int[] arr3 = {1, 2, 3, 4, 5, 6, 4};
        int[] expected1 = {5, 6 , 7};


        Assert.assertArrayEquals(expected1, arrChanger.cutOff(arr1, 4));
        Assert.assertEquals(new RuntimeException("value not found"), arrChanger.cutOff(arr2, 4));
        Assert.assertArrayEquals(null, arrChanger.cutOff(arr3, 4));


    }

    @Test
    public void findValue() {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {1, 2, 3, 3, 5, 6, 7};
        int[] arr3 = {2, 2, 3, 3, 5, 6, 7};

        Assert.assertNotNull(arr1);
        Assert.assertTrue(arrChanger.findValue(arr2, 1, 4));
        Assert.assertFalse(arrChanger.findValue(arr3, 1, 4));
    }
}