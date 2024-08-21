package persistence;

import model.DecidingGuest;
import model.Guest;
import model.GuestList;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    GuestList guestList;

    @BeforeEach
    public void setup() {
        guestList = new GuestList();
        try {
            guestList.clearFile();
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testSaveLoadItem() throws IOException {
        Item item1 = new Item("a",90);
        Item item = new Item("b", 100);
        guestList.addItem(item1);
        guestList.addItem(item);
        Saver.saveItem(guestList);
        try {
            List<String> lines = Files.readAllLines(Paths.get(GuestList.PATH1));
            String actualString = lines.get(0);
            String expectedString = "[{\"price\":90,\"name\":\"a\"},{\"price\":100,\"name\":\"b\"}]";
            assertEquals(expectedString, actualString);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        guestList = new GuestList();
        Loader.loadItem(guestList);
        assertEquals(guestList.getItems().get(0), item1);
        assertEquals(guestList.getItems().get(1), item);
        guestList.clearItemList();
        assertTrue(guestList.getItems().isEmpty());
    }
    @Test
    public void testSaveLoadFailed() {

        try {
            List<String> lines = Files.readAllLines(Paths.get("abcd.json"));
            fail();
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testSaveLoadDecidingGuest() throws IOException{
        DecidingGuest guest = new DecidingGuest("a","study", "DECIDING");
        DecidingGuest guest1 = new DecidingGuest("b","work", "DECIDING");
        guestList.addDecingGuest(guest);
        guestList.addDecingGuest(guest1);
        Saver.saveDecidingGuests(guestList);
        try {
            List<String> lines = Files.readAllLines(Paths.get(guestList.PATH));
            String actualString = lines.get(0);
            String expectedString = "[{\"reason\":\"study\",\"name\":\"a\",\"status\":\"DECIDING\"},{\"reason\":\"work\",\"name\":\"b\",\"status\":\"DECIDING\"}]";
            assertEquals(expectedString, actualString);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        guestList = new GuestList();
        Loader.loadDecidingGuests(guestList);
        assertEquals(guestList.getDecidingGuests().get(0), guest);
        assertEquals(guestList.getDecidingGuests().get(1), guest1);
        guestList.clearDecidingList();
        assertTrue(guestList.getDecidingGuests().isEmpty());
    }

    @Test
    public void testSaveLoadGuests() throws IOException{
        Guest guest = new Guest("a","YES");
        Guest guest1 = new Guest("a","YES");
        Guest guest2 = new Guest("a","NO");
        guestList.addGuest(guest);
        guestList.addGuest(guest1);
        guestList.addGuest(guest2);
        Saver.saveComingGuests(guestList);
        try {
            List<String> lines = Files.readAllLines(Paths.get(guestList.PATH2));
            String actualString = lines.get(0);
            String expectedString = "[{\"name\":\"a\",\"status\":\"DECIDING\"},{\"name\":\"a\",\"status\":\"DECIDING\"},{\"name\":\"a\",\"status\":\"NO\"}]";
            assertEquals(expectedString, actualString);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        guestList = new GuestList();
        Loader.loadGuests(guestList);
        assertEquals(guestList.getGuests().get(0), guest);
        assertEquals(guestList.getGuests().get(1), guest1);
        guestList.clearGuestList();
        assertTrue(guestList.getGuests().isEmpty());
    }
}
