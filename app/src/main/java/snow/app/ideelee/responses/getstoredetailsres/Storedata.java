
package snow.app.ideelee.responses.getstoredetailsres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Storedata {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("delivery_service_id")
    @Expose
    private String deliveryServiceId;
    @SerializedName("service_provider_id")
    @Expose
    private String serviceProviderId;
    @SerializedName("store_image")
    @Expose
    private String storeImage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("min_order")
    @Expose
    private String minOrder;
    @SerializedName("opening_time")
    @Expose
    private String openingTime;
    @SerializedName("closing_time")
    @Expose
    private String closingTime;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDeliveryServiceId() {
        return deliveryServiceId;
    }

    public void setDeliveryServiceId(String deliveryServiceId) {
        this.deliveryServiceId = deliveryServiceId;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(String minOrder) {
        this.minOrder = minOrder;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
