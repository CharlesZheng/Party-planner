package model;

import java.util.Objects;

public class Item {
    private String name;
    private int price;

    // MODIFIES: this
    // EFFECTS: the constructor of item class, construct a new item that contains name and price
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return price == item.price && name.equals(item.name);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, price);
//    }
}
