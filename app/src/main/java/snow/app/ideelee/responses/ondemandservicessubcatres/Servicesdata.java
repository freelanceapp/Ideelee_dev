
package snow.app.ideelee.responses.ondemandservicessubcatres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicesdata {

    @SerializedName("parentdetail")
    @Expose
    private Parentdetail parentdetail;
    @SerializedName("services_detail")
    @Expose
    private List<ServicesDetail> servicesDetail = null;

    public Parentdetail getParentdetail() {
        return parentdetail;
    }

    public void setParentdetail(Parentdetail parentdetail) {
        this.parentdetail = parentdetail;
    }

    public List<ServicesDetail> getServicesDetail() {
        return servicesDetail;
    }

    public void setServicesDetail(List<ServicesDetail> servicesDetail) {
        this.servicesDetail = servicesDetail;
    }

}
