package com.nicotimeout.app.database;

public class UserModel {
    public int user_id;
    public String quit_date;
    public int cig_per_day;
    public int cig_price;
    public int cig_years;

    //generators

    public UserModel(int user_id, String quit_date, int cig_per_day, int cig_price, int cig_years) {
        this.user_id = user_id;
        this.quit_date = quit_date;
        this.cig_per_day = cig_per_day;
        this.cig_price = cig_price;
        this.cig_years = cig_years;
    }

    public UserModel() {
    }

    // to string

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", quit_date=" + quit_date +
                ", cig_per_day=" + cig_per_day +
                ", cig_price=" + cig_price +
                ", cig_years=" + cig_years +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQuit_date() {
        return quit_date;
    }

    public void setQuit_date(String quit_date) {
        this.quit_date = quit_date;
    }

    public int getCig_per_day() {
        return cig_per_day;
    }

    public void setCig_per_day(int cig_per_day) {
        this.cig_per_day = cig_per_day;
    }

    public int getCig_price() {
        return cig_price;
    }

    public void setCig_price(int cig_price) {
        this.cig_price = cig_price;
    }

    public int getCig_years() {
        return cig_years;
    }

    public void setCig_years(int cig_years) {
        this.cig_years = cig_years;
    }
}
