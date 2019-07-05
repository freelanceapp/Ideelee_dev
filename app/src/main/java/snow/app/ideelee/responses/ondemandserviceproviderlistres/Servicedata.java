
package snow.app.ideelee.responses.ondemandserviceproviderlistres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicedata {

    @SerializedName("headerdata")
    @Expose
    private Headerdata headerdata;
    @SerializedName("servicelist")
    @Expose
    private List<Servicelist> servicelist = null;

    public Headerdata getHeaderdata() {
        return headerdata;
    }

    public void setHeaderdata(Headerdata headerdata) {
        this.headerdata = headerdata;
    }

    public List<Servicelist> getServicelist() {
        return servicelist;
    }

    public void setServicelist(List<Servicelist> servicelist) {
        this.servicelist = servicelist;
    }

}
