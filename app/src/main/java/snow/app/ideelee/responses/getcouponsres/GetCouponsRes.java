
package snow.app.ideelee.responses.getcouponsres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCouponsRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("coupondata")
    @Expose
    private List<Coupondatum> coupondata = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetCouponsRes() {
    }

    /**
     * 
     * @param message
     * @param coupondata
     * @param status
     */
    public GetCouponsRes(Boolean status, String message, List<Coupondatum> coupondata) {
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

    public List<Coupondatum> getCoupondata() {
        return coupondata;
    }

    public void setCoupondata(List<Coupondatum> coupondata) {
        this.coupondata = coupondata;
    }

}
