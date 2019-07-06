
package snow.app.ideelee.responses.ondemandserviceproviderlistres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headerdata {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("parentid")
    @Expose
    private String parentid;
    @SerializedName("subparentid")
    @Expose
    private String subparentid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getSubparentid() {
        return subparentid;
    }

    public void setSubparentid(String subparentid) {
        this.subparentid = subparentid;
    }

}
