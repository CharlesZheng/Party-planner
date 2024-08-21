package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {
    Guest h;
    Guest f;
    Guest r;
    GuestList guestList;

    @Test
    public void testSetup() {
        h = new Guest("S","DECIDING");
        assertEquals(h.getName(),"S");
        assertEquals(h.getStatus(), Guest.GuestStatus.DECIDING);
        f = new Guest("f","COMING");
        assertEquals(f.getStatus(), Guest.GuestStatus.COMING);
        r = new Guest("r","NO");
        assertEquals(r.getStatus(), Guest.GuestStatus.NO);
    }

    @Test
    public void testEquals() {
        guestList = new GuestList();
        f = new Guest("f","COMING");
        r = new Guest("r","NO");
        guestList.addGuest(f);
        guestList.addGuest(r);
        assertEquals(guestList.getGuests().get(0), f);
        assertEquals(guestList.getGuests().get(1), r);
        assertNotEquals(guestList.getGuests().get(1), f);
        assertFalse(f.equals(null));
        guestList.clearGuestList();
        assertTrue(guestList.getGuests().isEmpty());
    }


}
