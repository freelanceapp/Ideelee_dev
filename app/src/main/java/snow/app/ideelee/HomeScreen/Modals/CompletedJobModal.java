package snow.app.ideelee.HomeScreen.Modals;

public class CompletedJobModal {
    String status;
    String date;
    String name;
String payment;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    double rating;
    public String getStatus() {
        return status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public CompletedJobModal(String status, String date, String name, String servicetype, String address, String phonenumber,
                             String payment,double rating) {
        this.status = status;
        this.date = date;
        this.name = name;
        this.servicetype = servicetype;
        this.address = address;
        this.phonenumber = phonenumber;
        this.payment=payment;
        this.rating=rating;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
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

    String servicetype;
    String address;
    String phonenumber;

}
