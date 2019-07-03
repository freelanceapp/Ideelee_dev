
package snow.app.ideelee.responses.homescreenres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homescreendata {

    @SerializedName("parentCatArray")
    @Expose
    private List<ParentCatArray> parentCatArray = null;
    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("popular_profile")
    @Expose
    private List<PopularProfile> popularProfile = null;

    public List<ParentCatArray> getParentCatArray() {
        return parentCatArray;
    }

    public void setParentCatArray(List<ParentCatArray> parentCatArray) {
        this.parentCatArray = parentCatArray;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<PopularProfile> getPopularProfile() {
        return popularProfile;
    }

    public void setPopularProfile(List<PopularProfile> popularProfile) {
        this.popularProfile = popularProfile;
    }

}
