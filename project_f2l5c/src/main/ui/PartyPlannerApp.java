package ui;

import model.DecidingGuest;
import model.Guest;
import model.GuestList;
import model.Item;
import persistence.Loader;
import persistence.Saver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartyPlannerApp {
    private final Scanner scanner = new Scanner(System.in);
    private final GuestList guestList = new GuestList();
    private final GuestList decidingGuestList = new GuestList();
    private final GuestList itemList = new GuestList();
    private final GuestList movedGuestsList = new GuestList();
    private String theme;
    private String date;
    private final ArrayList<String> reasons = new ArrayList<>();
    private int number;

//    public PartyPlannerApp() {
//        runPartyPlanner();
//    }


    public void runPartyPlanner() {
//        boolean keepGoing = true;
//        String command = null;
//        while (keepGoing) {
//            displayMenu();
//            command = scanner.next();
//            command = command.toLowerCase();
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                command = scanner.nextLine();
//                System.out.println("You selected: [" + command + "].");
//                System.out.println("==========================================");
//                processCommand(command);
//                System.out.println("==========================================");
//            }
//        }
        String command = null;
        try {
            Loader.loadItem(itemList);
            Loader.loadDecidingGuests(decidingGuestList);
            Loader.loadGuests(guestList);
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading todo list.");
        }
        while (true) {
            displayMenu();
            command = scanner.nextLine();
            System.out.println("You selected: [" + command + "].");
            System.out.println("==========================================");
            processCommand(command);
            System.out.println("==========================================");
            if (command.equals("q")) {
                System.out.println("Finish");
                break;
            }
        }
        tryCatch();
    }


    private void tryCatch() {
        try {
            Saver.saveItem(itemList);
            Saver.saveDecidingGuests(decidingGuestList);
            Saver.saveComingGuests(guestList);
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading todo list.");
        }
        System.out.println("Thank you. Bye!");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Enter the theme of the party");
        System.out.println("\t2 -> Change the theme of the party");
        System.out.println("\t3 -> Enter the name of the guest");
        System.out.println("\t4 -> Show all the guests that come");
        System.out.println("\t5 -> Show all the guests that are deciding");
        System.out.println("\t6 -> clear List");
        System.out.println("\t7 -> Change the guest from deciding to come");
        System.out.println("\t8 -> Enter the item and its price");
        System.out.println("\t9 -> Split the bill");
        System.out.println("\tw -> View the information of this party");
        System.out.println("\tq -> Save information to file and quit");
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            enterTheme();
        } else if (command.equals("2")) {
            this.theme = null;
            this.date = null;
            enterTheme();
        } else if (command.equals("3")) {
            enterGuestName();
        } else if (command.equals("4")) {
            System.out.println(guestList.listToString());
            System.out.println(decidingGuestList.movedListToString());
        } else if (command.equals("5")) {
            System.out.println(decidingGuestList.decidingListToString());
        } else if (command.equals("6")) {
            clearList();
        } else if (command.equals("7")) {
            System.out.println(decidingGuestList.decidingListToString());
            setTheGuestAsComing();
        } else if (command.equals("8")) {
            enterItemName();
        } else {
            processCommand2(command);
        }
    }

    private void processCommand2(String command) {
        if (command.equals("9")) {
            splitBill();
        } else if (command.equals("w")) {
            printInfo();
        }
    }

    private void setTheGuestAsComing() {
        System.out.println("Mark one as Coming");
        int itemIndex = scanner.nextInt();
        scanner.nextLine();
        decidingGuestList.setGuestAsComing(itemIndex);
        System.out.println("This person is coming.");
    }

    private void clearList() {
        System.out.println("Which list do you want to clear(guestList(1),decidingGuestList(2),itemList(3))");
        String list = scanner.nextLine();
        if (list.equals("1")) {
            guestList.clearGuestList();
            System.out.println("The guestList is cleared.");
        } else if (list.equals("2")) {
            try {
                decidingGuestList.clearDecidingList();
            } catch (IOException e) {
                System.out.println("Encountered IOException while loading todo list.");
            }
        } else if (list.equals("3")) {
            itemList.clearItemList();
            System.out.println("The itemList is cleared.");
        }
    }


    private void enterGuestName() {
        System.out.println("Enter the guest name: ");
        String name = scanner.nextLine();
        System.out.println("Is he/she coming? (COMING,NO,DECIDING)");
        String status = scanner.nextLine();
        if (status.equals("DECIDING")) {
            System.out.println("why not?");
            String reason = scanner.nextLine();
            decidingGuestList.addDecingGuest(new DecidingGuest(name, reason, status));
            reasons.add(reason);
        } else if (status.equals("COMING")) {
            guestList.addGuest(new Guest(name, status));
        } else {
            System.out.println("Hope you can come next time :)");
        }
    }

    private void enterTheme() {
        System.out.println("Enter Theme name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the date of party:  e.g. 2002-01-14");
        String date = scanner.nextLine();
        this.theme = name;
        this.date = date;
    }

    private void enterItemName() {
        System.out.println("Enter the item name: ");
        String name = scanner.nextLine();
        System.out.println("what is the price?");
        String price = scanner.nextLine();
        int money = Integer.parseInt(price);
        itemList.addItem(new Item(name, money));
        System.out.println("Do you want to see the list?(yes,no)");
        String option = scanner.nextLine();
        if (option.equals("yes")) {
            System.out.println(itemList.itemListToString());
        }
    }

    private void splitBill() {
        System.out.println("Here is how much each person needs to pay");
        int a = 0;
        for (Item b : itemList.getItems()) {
            a += b.getPrice();
        }
        number = 0;
        number = a / (guestList.getSizeOfGuestList() + decidingGuestList.getSizeOfMovedGuestList());
        String s = Integer.toString(number);
        System.out.println(s);
    }

    private void printInfo() {
        System.out.println("This is the theme of your party:" + theme);
        System.out.println("This is the date of your party:" + date);
        System.out.println("This are the people that will come to your party: \n" + guestList.listToString());
        System.out.println(decidingGuestList.movedListToString());
        System.out.println("This are the people that are still deciding: \n"
                + decidingGuestList.decidingListToString());
        System.out.println("This are the reasons:");
        reasonsListToString();
        System.out.println("This are the items:" + itemList.itemListToString());
        splitBill();
    }

    public void reasonsListToString() {
        for (int i = 0; i < reasons.size(); i++) {
            System.out.println(reasons.get(i));
        }
    }

//    public void movedGuestsListToString() {
//        for (DecidingGuest decidingGuest : decidingGuestList.getTheGuestThatHasBeenMoved()) {
//            movedGuestsList.addDecingGuest(decidingGuest);
//        }
//    }
}
