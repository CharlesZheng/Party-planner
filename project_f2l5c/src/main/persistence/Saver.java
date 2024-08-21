package persistence;

import model.DecidingGuest;
import model.Guest;
import model.GuestList;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// This class is used save all the information that relate to the party(Item, DecingGuest, Guest)
public class Saver {

    //EFFECT; Save the item list
    public static void saveItem(GuestList guestList) throws IOException {
        PrintWriter writer = new PrintWriter(GuestList.PATH1, "UTF-8");
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (Item i : guestList.getItems()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", i.getName());
            jsonObject.put("price", i.getPrice());
            jsonObjects.add(jsonObject);
        }
        JSONArray jsonArray = new JSONArray(jsonObjects);
        writer.println(jsonArray.toString());
        writer.close();
    }

    //EFFECT; Save the DecidingGuests list
    public static void saveDecidingGuests(GuestList guestList) throws IOException {
        PrintWriter writer = new PrintWriter(GuestList.PATH, "UTF-8");
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (DecidingGuest i : guestList.getDecidingGuests()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", i.getName());
            jsonObject.put("reason", i.getReason());
            jsonObject.put("status", i.getStatus());
            jsonObjects.add(jsonObject);
        }
        JSONArray jsonArray = new JSONArray(jsonObjects);
        writer.println(jsonArray.toString());
        writer.close();
    }


    //EFFECT; Save the Guest list
    public static void saveComingGuests(GuestList guestList) throws IOException {
        PrintWriter writer = new PrintWriter(GuestList.PATH2, "UTF-8");
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (Guest i : guestList.getGuests()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", i.getName());
            jsonObject.put("status", i.getStatus());
            jsonObjects.add(jsonObject);
        }
        JSONArray jsonArray = new JSONArray(jsonObjects);
        writer.println(jsonArray.toString());
        writer.close();
    }
}