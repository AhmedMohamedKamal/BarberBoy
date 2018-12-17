package kamal.agooz.ahmed.barberboy;

public class BookList
{
    String name,shavehair,shavebeard,hairprigment,facemusk,time,date, price;

    public BookList() { }

    public BookList(String name, String shavehair, String shavebeard, String hairprigment, String facemusk, String time, String date, String price) {
        this.name = name;
        this.shavehair = shavehair;
        this.shavebeard = shavebeard;
        this.hairprigment = hairprigment;
        this.facemusk = facemusk;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShavehair() {
        return shavehair;
    }

    public void setShavehair(String shavehair) {
        this.shavehair = shavehair;
    }

    public String getShavebeard() {
        return shavebeard;
    }

    public void setShavebeard(String shavebeard) {
        this.shavebeard = shavebeard;
    }

    public String getHairprigment() {
        return hairprigment;
    }

    public void setHairprigment(String hairprigment) {
        this.hairprigment = hairprigment;
    }

    public String getFacemusk() {
        return facemusk;
    }

    public void setFacemusk(String facemusk) {
        this.facemusk = facemusk;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}