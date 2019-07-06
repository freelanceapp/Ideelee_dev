
package snow.app.ideelee.responses.deliverysubcatres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicesdata {

    @SerializedName("parentdetail")
    @Expose
    private Parentdetail parentdetail;
    @SerializedName("stores_detail")
    @Expose
    private List<StoresDetail> storesDetail = null;

    public Parentdetail getParentdetail() {
        return parentdetail;
    }

    public void setParentdetail(Parentdetail parentdetail) {
        this.parentdetail = parentdetail;
    }

    public List<StoresDetail> getStoresDetail() {
        return storesDetail;
    }

    public void setStoresDetail(List<StoresDetail> storesDetail) {
        this.storesDetail = storesDetail;
    }

}
