package com.example.rooms;

public class Room {
    String check_in;
    String check_out;
    int adultNo;
    int childNo;

    public Room() {
    }


    public Room(String check_in, String check_out, int adultNo, int childNo) {
        this.check_in = check_in;
        this.check_out = check_out;
        this.adultNo = adultNo;
        this.childNo = childNo;
    }

    public String getCheck_out() {
        return check_out;
    }

    public int getChildNo() {
        return childNo;
    }

    public void setChildNo(int childNo) {
        this.childNo = childNo;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public int getAdultNo() {
        return adultNo;
    }

    public void setAdultNo
            (int adultNo) {
        this.adultNo = adultNo;
    }
}
