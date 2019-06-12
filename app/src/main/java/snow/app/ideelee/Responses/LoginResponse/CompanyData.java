
package snow.app.ideelee.Responses.LoginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyData {

    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_contact")
    @Expose
    private String companyContact;
    @SerializedName("company_address")
    @Expose
    private String companyAddress;
    @SerializedName("company_vat")
    @Expose
    private String companyVat;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyVat() {
        return companyVat;
    }

    public void setCompanyVat(String companyVat) {
        this.companyVat = companyVat;
    }

}
