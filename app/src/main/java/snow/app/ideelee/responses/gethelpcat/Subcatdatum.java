
package snow.app.ideelee.responses.gethelpcat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subcatdatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("help_category")
    @Expose
    private String helpCategory;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHelpCategory() {
        return helpCategory;
    }

    public void setHelpCategory(String helpCategory) {
        this.helpCategory = helpCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
