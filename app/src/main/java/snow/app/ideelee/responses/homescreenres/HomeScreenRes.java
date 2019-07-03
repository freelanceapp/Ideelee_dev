
package snow.app.ideelee.responses.homescreenres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeScreenRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("homescreendata")
    @Expose
    private Homescreendata homescreendata;

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

    public Homescreendata getHomescreendata() {
        return homescreendata;
    }

    public void setHomescreendata(Homescreendata homescreendata) {
        this.homescreendata = homescreendata;
    }

}
