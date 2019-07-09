
package snow.app.ideelee.responses.getstoredetailsres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStoreDetailsRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("storedata")
    @Expose
    private Storedata storedata;

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

    public Storedata getStoredata() {
        return storedata;
    }

    public void setStoredata(Storedata storedata) {
        this.storedata = storedata;
    }

}
