package model;

import java.util.Objects;

public class DecidingGuest {
    public enum GuestStatus { COMING, NO, DECIDING }

    private String name;
    private String reason;
    private GuestStatus status;

    // MODIFIES: this
    // EFFECTS: the constructor of deciding guest class, construct a new deciding guest that contains name and reason
    // and status.
    public DecidingGuest(String name, String reason, String status) {
        this.name = name;
        this.reason = reason;
        this.status = GuestStatus.DECIDING;
    }

    public void setStatus(GuestStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public GuestStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DecidingGuest that = (DecidingGuest) o;
        return name.equals(that.name) && reason.equals(that.reason) && status == that.status;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, reason, status);
//    }
}
