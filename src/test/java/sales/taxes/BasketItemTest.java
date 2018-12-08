package sales.taxes;

import org.junit.Before;
import org.junit.Test;
import sales.taxes.item.StandardItem;
import sales.taxes.item.math.Rounder;

import static org.junit.Assert.*;

public class BasketItemTest {

    BasketItem basketItem;
    @Before
    public void initialize(){
        basketItem = new BasketItem(1, new StandardItem(true, "book", 10, new Rounder()));
    }

    @Test
    public void getQuantity() {
        assertEquals(1, basketItem.getQuantity(), 0);
    }

    @Test
    public void getItem() {
        assertEquals(StandardItem.class, basketItem.getItem().getClass());
    }

    @Test
    public void addQuantity() {
        basketItem.addQuantity(10);
        assertEquals(11, basketItem.getQuantity(), 0);
    }
}