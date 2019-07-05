
package snow.app.ideelee.responses.deliverysubcatres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parentdetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_logo")
    @Expose
    private String serviceLogo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceLogo() {
        return serviceLogo;
    }

    public void setServiceLogo(String serviceLogo) {
        this.serviceLogo = serviceLogo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
