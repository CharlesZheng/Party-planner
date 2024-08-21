package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GuestList {
    private final ArrayList<Guest> guestList = new ArrayList<>();
    private final ArrayList<DecidingGuest> decidingList = new ArrayList<>();
    private final ArrayList<Item> itemList = new ArrayList<>();
    private final ArrayList<DecidingGuest> theGuestThatHasBeenMoved = new ArrayList<>();
    public static final String PATH = "./data/savedList.json"; //DecidingList
    public static final String PATH1 = "./data/itemList.json";
    public static final String PATH2 = "./data/comingList.json";

    public void addGuest(Guest guest) {
        Event event = new Event(guest.getName()
                + " ( Status is " + guest.getStatus() + ") is added.");
        EventLog.getInstance().logEvent(event);
        guestList.add(guest);
    }

    public void addDecingGuest(DecidingGuest guest) {
        decidingList.add(guest);
    }

    public void addItem(Item item) {
        Event event = new Event(item.getName()
                + " ( Price is " + item.getPrice() + " ) is added.");
        EventLog.getInstance().logEvent(event);
        itemList.add(item);
    }

    public void addMovedGuest(DecidingGuest a) {
        theGuestThatHasBeenMoved.add(a);
    }

    public void clearGuestList() {
        try {
            Event event = new Event("Guest list is emptied");
            EventLog.getInstance().logEvent(event);
            guestList.clear();
            clearFile2();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }

    public void clearDecidingList() throws IOException {
        decidingList.clear();
        clearFile();
    }

    public void clearItemList() {
        try {
            Event event = new Event("Item list is emptied");
            EventLog.getInstance().logEvent(event);
            itemList.clear();
            clearFile1();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }

    public void clearFile()  throws IOException {
        PrintWriter writer = new PrintWriter(PATH,"UTF-8");
        writer.print("");
        writer.close();
    }

    public void clearFile1() throws IOException {
        PrintWriter writer = new PrintWriter(PATH1,"UTF-8");
        writer.print("");
        writer.close();
    }

    public void clearFile2() throws IOException {
        PrintWriter writer = new PrintWriter(PATH2,"UTF-8");
        writer.print("");
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: set the coming guest's status as COMING and remove it from the original list
    public boolean setGuestAsComing(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < decidingList.size()) {
            DecidingGuest guest = decidingList.get(itemIndex);
            guest.setStatus(DecidingGuest.GuestStatus.COMING);
            decidingList.remove(guest);
            theGuestThatHasBeenMoved.add(guest);
            Event event = new Event(guest.getName()
                    + " ( Status is " + guest.getStatus() + ") is COMING.");
            EventLog.getInstance().logEvent(event);
            return true;
        } else {
            System.out.println("Invalid");
            return false;
        }
    }

    public int getSizeOfGuestList() {
        return guestList.size();
    }

    public int getSizeOfMovedGuestList() {
        return theGuestThatHasBeenMoved.size();
    }

    public int getSizeOfDecidingGuestList() {
        return decidingList.size();
    }

    public int getSizeOfItemList() {
        return itemList.size();
    }

    // EFFECTS: print the guest list
    public String listToString() {
        StringBuilder stringBuilder = new StringBuilder("ALL the Guests:\n");
        for (Guest guest : guestList) {
            String string = "[" + guestList.indexOf(guest) + "]:  " + guest.getName() + " ==  Status: "
                    + guest.getStatus();
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }

    // EFFECTS: print the deciding guest list
    public String decidingListToString() {
        StringBuilder stringBuilder = new StringBuilder("ALL the Guests:\n");
        for (DecidingGuest guest : decidingList) {
            String string = "[" + decidingList.indexOf(guest) + "]:  " + guest.getName() + " ==  Status: "
                    + guest.getStatus();
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }


    // EFFECTS: print the item list
    public String itemListToString() {
        StringBuilder stringBuilder = new StringBuilder("ALL the Items:\n");
        for (Item item : itemList) {
            String string = "[" + itemList.indexOf(item) + "]:  " + item.getName() + " ==  Price: "
                    + item.getPrice();
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }


    // EFFECTS: print the list that contains the deciding guests who have been moved
    public String movedListToString() {
        StringBuilder stringBuilder = new StringBuilder("ALL the guests that eventually decide to come:\n");
        for (DecidingGuest decidingGuest  : theGuestThatHasBeenMoved) {
            String string = "[" + theGuestThatHasBeenMoved.indexOf(decidingGuest) + "]:  "
                    + decidingGuest.getName() + " ==  Status: "
                    + decidingGuest.getStatus();
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<Item> getItems() {
        return itemList;
    }

    public ArrayList<DecidingGuest> getDecidingGuests() {
        return decidingList;
    }

    public ArrayList<Guest> getGuests() {
        return guestList;
    }

    public Guest getSpecificGuest(int index) {
        return guestList.get(index);
    }


    public DecidingGuest getSpecificDecidingGuest(int index) {
        return decidingList.get(index);
    }

    public Item getSpecificItem(int index) {
        return itemList.get(index);
    }
//    public ArrayList<DecidingGuest> getTheGuestThatHasBeenMoved() {
//        return theGuestThatHasBeenMoved;
//    }

    public void printLog() {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }
}
