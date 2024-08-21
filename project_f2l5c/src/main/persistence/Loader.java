package persistence;

import model.DecidingGuest;
import model.Guest;
import model.GuestList;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// This class is used load all the information that relate to the party(Item, DecingGuest, Guest)
public class Loader {

    //EFFECT; Load the saved item list
    public static void loadItem(GuestList guestList) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(GuestList.PATH1));
        if (lines.size() == 0) {
            return;
        }
        String jsonArrayString = lines.get(0); // there is only 1 line in the file
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            int price = jsonObject.getInt("price");
            guestList.addItem(new Item(name, price));
        }
    }

    //EFFECT; Load the saved DecidingGuests list
    public static void loadDecidingGuests(GuestList guestList) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(GuestList.PATH));
        if (lines.size() == 0) {
            return;
        }
        String jsonArrayString = lines.get(0); // there is only 1 line in the file
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String reason = jsonObject.getString("reason");
            String status = jsonObject.getString("status");
            guestList.addDecingGuest(new DecidingGuest(name, reason, status));
        }
    }

    //EFFECT; Load the saved Guest list
    public static void loadGuests(GuestList guestList) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(GuestList.PATH2));
        if (lines.size() == 0) {
            return;
        }
        String jsonArrayString = lines.get(0); // there is only 1 line in the file
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String status = jsonObject.getString("status");
            guestList.addGuest(new Guest(name, status));
        }
    }
}
