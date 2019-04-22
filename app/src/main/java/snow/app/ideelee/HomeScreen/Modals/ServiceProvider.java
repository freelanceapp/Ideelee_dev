package snow.app.ideelee.HomeScreen.Modals;

public class ServiceProvider {
    private String title;
    private double rating;
    private int image;

    public ServiceProvider(String title, double rating,int image) {
        this.title = title;
        this.rating = rating;
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getImage() {
        return image;
    }

}
