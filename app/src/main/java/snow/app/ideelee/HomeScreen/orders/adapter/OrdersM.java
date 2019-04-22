package snow.app.ideelee.HomeScreen.orders.adapter;

public class OrdersM {
    String image;
    String ResName;
    String summery;
    String time;
    String price;
    String type;
    String status;

    public OrdersM(String image, String resName, String summery, String time, String price, String type, String status) {
        this.image = image;
        ResName = resName;
        this.summery = summery;
        this.time = time;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        ResName = resName;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
