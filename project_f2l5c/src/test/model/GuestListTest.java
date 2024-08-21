package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GuestListTest {
    DecidingGuest a;
    Guest f;
    DecidingGuest r;
    ArrayList<DecidingGuest> s;
    GuestList guestList;
    GuestList guestList2;
    Item item;

    @BeforeEach
    public void setup() {
        a = new DecidingGuest("s","DECIDING","DECIDING");
        r = new DecidingGuest("sh","DECIDING","COMING");
        f = new Guest("S","COMING");
        s = new ArrayList<>();
        item = new Item("s",10);
        guestList = new GuestList();
        guestList2 = new GuestList();
    }

    @Test
    public void testSetGuestAsComing () {
        s.add(a);
        guestList.addDecingGuest(a);
        guestList.setGuestAsComing(0);
        assertEquals(a.getStatus(), DecidingGuest.GuestStatus.COMING);
        assertFalse(guestList.setGuestAsComing(3));
    }

    @Test
    public void testBasicFunctions() {
        guestList.addGuest(f);
        assertEquals(guestList.getSizeOfGuestList(),1);
        guestList2.addGuest(f);
        assertEquals(guestList.getGuests(),guestList2.getGuests());
        guestList.clearGuestList();
        assertEquals(guestList.getSizeOfGuestList(),0);
        guestList.addDecingGuest(a);
        assertEquals(guestList.getSizeOfDecidingGuestList(),1);
        guestList2.addDecingGuest(a);
        assertEquals(guestList.getDecidingGuests(),guestList2.getDecidingGuests());
        try {
            guestList.clearDecidingList();
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        assertEquals(guestList.getSizeOfDecidingGuestList(),0);
        guestList.addItem(item);
        assertEquals(guestList.getSizeOfItemList(),1);
        guestList2.addItem(item);
        assertEquals(guestList.getItems(),guestList2.getItems());
        guestList.addMovedGuest(a);
        assertEquals(guestList.getSizeOfMovedGuestList(),1);

    }

    @Test
    public void testPrintMethods(){
        guestList.addDecingGuest(a);
        String item = "ALL the Guests:\n" +
                "[0]:  s ==  Status: DECIDING\n";
        assertEquals(guestList.decidingListToString(), item);
        guestList.addGuest(f);
        assertEquals(guestList.listToString(),"ALL the Guests:\n" +
                "[0]:  S ==  Status: COMING\n");
        String k = "ALL the Items:\n" +
                "[0]:  sh ==  Status: DECIDING\n";
        guestList.addMovedGuest(r);
        assertEquals(guestList.movedListToString(), k);
        guestList.addItem(this.item);
        guestList2.addItem(this.item);
        assertEquals(guestList.getItems(),guestList2.getItems());
        String y = "ALL the Items:\n" +
                "[0]:  s ==  Price: 10\n";
        assertEquals(guestList2.itemListToString(),y);
    }
}
