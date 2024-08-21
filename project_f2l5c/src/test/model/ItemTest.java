package model;

import org.junit.jupiter.api.Test;
import persistence.Loader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item a;
    GuestList guestList;

    @Test
    public void testItem() {
        a = new Item("s",100);
        assertEquals(a.getName(),"s");
        assertEquals(a.getPrice(),100);
    }

    @Test
    public void testEquals() {
        guestList = new GuestList();
        Item item1 = new Item("a",90);
        Item item = new Item("b", 100);
        guestList.addItem(item1);
        guestList.addItem(item);
        assertEquals(guestList.getItems().get(0), item1);
        assertEquals(guestList.getItems().get(1), item);
        assertNotEquals(guestList.getItems().get(1), item1);
        Item item2 = new Item("dsad",1000);
        assertFalse(item2.equals(null));
        guestList.clearItemList();
        assertTrue(guestList.getItems().isEmpty());
    }
}