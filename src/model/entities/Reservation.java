package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {

    }

    public Reservation(Integer roomNumber, Date checkin, Date checkout) {
        if (!checkout.after(checkin)) {
            throw new DomainException("Check-ou date must be after check-in date");
        }

        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public long duration() {
        // Diference in milliseconds
        long diff = this.checkout.getTime() - this.checkin.getTime();
        // Transform milliseconds to days
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkin, Date checkout) {
        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)) {
            // Retornando uma excessão
            throw new DomainException("Reservation dates for update must be future dates");
        }

        if (!checkout.after(checkin)) {
            throw new DomainException("Check-ou date must be after check-in date");
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {

        return "Room " + roomNumber + ", check-in: " + sdf.format(checkin) + ", check-out: " + sdf.format(checkout)
                + ", " + duration() + " nights.";
    }

}
