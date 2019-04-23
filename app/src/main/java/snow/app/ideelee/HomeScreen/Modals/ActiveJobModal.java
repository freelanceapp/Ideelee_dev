package snow.app.ideelee.HomeScreen.Modals;

public class ActiveJobModal {
    public ActiveJobModal(String status, String date, String address, String phonenumber,
                          String payment_method, String name, String service_type) {
        this.status = status;
        this.date = date;
        this.address = address;
        this.phonenumber = phonenumber;
        this.payment_method = payment_method;
        this.name = name;
        this.service_type = service_type;
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    String status,date,address,phonenumber,payment_method,name,service_type;
    double rating;
}
