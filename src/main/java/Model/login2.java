package Model;

public class login2 {
    int id;
    String ordername;
    String buyername;
    String res;
    String location;
    String date;
    Float price;
    int user_id;

    public login2(int id, String ordername, String buyername, String res, String location, String date,
            Float price,int user_id) {
        this.id = id;
        this.ordername = ordername;
        this.buyername = buyername;
        this.res = res;
        this.location = location;
        this.date = date;
        this.price = price;
        this.user_id = user_id;
    }
//top restaurent   

public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

   
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "login2 [res=" + res + ", location=" + location + "]";
    }
    public void setPrice(Float price) {
        this.price = price;
    }

}
