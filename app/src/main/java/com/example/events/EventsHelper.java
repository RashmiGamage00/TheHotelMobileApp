package com.example.events;

public class EventsHelper {
    String eventDate;
    int guestNo;

    public EventsHelper() {
    }

    public EventsHelper(String eventDate, int guestNo) {
        this.eventDate = eventDate;
        this.guestNo = guestNo;
    }


    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getGuestNo() {
        return guestNo;
    }

    public void setGuestNo(int guestNo) {
        this.guestNo = guestNo;
    }
}
