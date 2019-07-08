
package snow.app.ideelee.responses.gethelpcat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHelpCatRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("subcatdata")
    @Expose
    private List<Subcatdatum> subcatdata = null;

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

    public List<Subcatdatum> getSubcatdata() {
        return subcatdata;
    }

    public void setSubcatdata(List<Subcatdatum> subcatdata) {
        this.subcatdata = subcatdata;
    }

}
