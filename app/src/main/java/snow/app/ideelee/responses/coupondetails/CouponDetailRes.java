
package snow.app.ideelee.responses.coupondetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponDetailRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("coupondata")
    @Expose
    private Coupondata coupondata;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CouponDetailRes() {
    }

    /**
     * 
     * @param message
     * @param coupondata
     * @param status
     */
    public CouponDetailRes(Boolean status, String message, Coupondata coupondata) {
        super();
        this.status = status;
        this.message = message;
        this.coupondata = coupondata;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Coupondata getCoupondata() {
        return coupondata;
    }

    public void setCoupondata(Coupondata coupondata) {
        this.coupondata = coupondata;
    }

}
