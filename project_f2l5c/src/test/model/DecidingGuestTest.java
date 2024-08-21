package model;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DecidingGuestTest {
    DecidingGuest a;
    DecidingGuest b;
    GuestList guestList;

    @Test
    public void testSetup() {
        a = new DecidingGuest("a","sss","YES");
        b = new DecidingGuest("SSS","SS","YES");
        b.setStatus(DecidingGuest.GuestStatus.DECIDING);
        assertEquals(a.getName(),"a");
        assertEquals(a.getStatus(),DecidingGuest.GuestStatus.DECIDING);
        assertEquals(a.getStatus(),a.getStatus());
        assertEquals(a.getReason(),"sss");
    }

    @Test
    public void testEquals() {
        guestList = new GuestList();
        a = new DecidingGuest("a","sss","YES");
        b = new DecidingGuest("SSS","SS","YES");
        b.setStatus(DecidingGuest.GuestStatus.DECIDING);
        guestList.addDecingGuest(a);
        guestList.addDecingGuest(b);
        assertEquals(guestList.getDecidingGuests().get(0), a);
        assertEquals(guestList.getDecidingGuests().get(1), b);
        assertNotEquals(guestList.getDecidingGuests().get(1), a);
        DecidingGuest decidingGuest = new DecidingGuest("ss","SS", "DECIDING");
        assertFalse(decidingGuest.equals(null));
        try {
            guestList.clearDecidingList();
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        assertTrue(guestList.getDecidingGuests().isEmpty());
    }
}
