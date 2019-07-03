
package snow.app.ideelee.responses.registerres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userregisterdata")
    @Expose
    private Userregisterdata userregisterdata;

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

    public Userregisterdata getUserregisterdata() {
        return userregisterdata;
    }

    public void setUserregisterdata(Userregisterdata userregisterdata) {
        this.userregisterdata = userregisterdata;
    }

}
