
package snow.app.ideelee.responses.coupondetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupondata {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("service_provider_id")
    @Expose
    private String serviceProviderId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("layout_type")
    @Expose
    private String layoutType;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("voucher_offer_price")
    @Expose
    private String voucherOfferPrice;
    @SerializedName("voucher_text")
    @Expose
    private String voucherText;
    @SerializedName("term_conditions")
    @Expose
    private String termConditions;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coupondata() {
    }

    /**
     * 
     * @param logo
     * @param status
     * @param voucherText
     * @param categoryId
     * @param image
     * @param endDate
     * @param serviceProviderId
     * @param voucherOfferPrice
     * @param id
     * @param price
     * @param createdAt
     * @param termConditions
     * @param layoutType
     * @param banner
     */
    public Coupondata(String id, String serviceProviderId, String categoryId, String layoutType, String logo, String endDate, Object image, String voucherOfferPrice, String voucherText, String termConditions, String price, String banner, String status, String createdAt) {
        super();
        this.id = id;
        this.serviceProviderId = serviceProviderId;
        this.categoryId = categoryId;
        this.layoutType = layoutType;
        this.logo = logo;
        this.endDate = endDate;
        this.image = image;
        this.voucherOfferPrice = voucherOfferPrice;
        this.voucherText = voucherText;
        this.termConditions = termConditions;
        this.price = price;
        this.banner = banner;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getVoucherOfferPrice() {
        return voucherOfferPrice;
    }

    public void setVoucherOfferPrice(String voucherOfferPrice) {
        this.voucherOfferPrice = voucherOfferPrice;
    }

    public String getVoucherText() {
        return voucherText;
    }

    public void setVoucherText(String voucherText) {
        this.voucherText = voucherText;
    }

    public String getTermConditions() {
        return termConditions;
    }

    public void setTermConditions(String termConditions) {
        this.termConditions = termConditions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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

}
