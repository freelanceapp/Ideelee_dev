
package snow.app.ideelee.responses.morecatres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreCategoryRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("parentcatdata")
    @Expose
    private List<Parentcatdatum> parentcatdata = null;

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

    public List<Parentcatdatum> getParentcatdata() {
        return parentcatdata;
    }

    public void setParentcatdata(List<Parentcatdatum> parentcatdata) {
        this.parentcatdata = parentcatdata;
    }

}
