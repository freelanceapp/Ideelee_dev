
package snow.app.ideelee.responses.ondemandservicessubcatres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnDemandSubCatRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("servicesdata")
    @Expose
    private Servicesdata servicesdata;

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

    public Servicesdata getServicesdata() {
        return servicesdata;
    }

    public void setServicesdata(Servicesdata servicesdata) {
        this.servicesdata = servicesdata;
    }

}
