package model;

import java.util.Objects;

public class Guest {
    public enum GuestStatus { COMING, NO, DECIDING }

    private String name;
    private GuestStatus status;

    // MODIFIES: this
    // EFFECTS: the constructor of the guest class and construct a new guest that has a name and status.
    public Guest(String name, String status) {
        this.name = name;
        if (status.equals("COMING")) {
            this.status = GuestStatus.COMING;
        } else if (status.equals("NO")) {
            this.status = GuestStatus.NO;
        } else {
            this.status = GuestStatus.DECIDING;
        }
    }

    public void setStatus(GuestStatus string) {
        this.status = string;
    }

    public String getName() {
        return name;
    }

    public GuestStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest) o;
        return name.equals(guest.name) && status == guest.status;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, status);
//    }
}
