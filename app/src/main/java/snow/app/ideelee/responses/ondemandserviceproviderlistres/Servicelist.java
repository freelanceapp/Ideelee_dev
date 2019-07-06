
package snow.app.ideelee.responses.ondemandserviceproviderlistres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicelist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_provider_id")
    @Expose
    private String serviceProviderId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("pay_type")
    @Expose
    private String payType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("rating")
    @Expose
    private Long rating;
    @SerializedName("otherservices")
    @Expose
    private String otherservices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getOtherservices() {
        return otherservices;
    }

    public void setOtherservices(String otherservices) {
        this.otherservices = otherservices;
    }

}
