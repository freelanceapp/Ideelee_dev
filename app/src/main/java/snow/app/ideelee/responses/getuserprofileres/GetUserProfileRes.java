
package snow.app.ideelee.responses.getuserprofileres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserProfileRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("servicedata")
    @Expose
    private Servicedata servicedata;

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

    public Servicedata getServicedata() {
        return servicedata;
    }

    public void setServicedata(Servicedata servicedata) {
        this.servicedata = servicedata;
    }

}
