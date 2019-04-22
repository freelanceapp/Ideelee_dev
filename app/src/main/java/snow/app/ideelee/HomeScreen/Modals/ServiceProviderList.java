package snow.app.ideelee.HomeScreen.Modals;

public class ServiceProviderList {
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    int image;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public ServiceProviderList(String name, String distance, double rating,int image) {
        this.name = name;
        this.distance = distance;
        this.rating = rating;
        this.image=image;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    String name,distance;
    double rating;
}
