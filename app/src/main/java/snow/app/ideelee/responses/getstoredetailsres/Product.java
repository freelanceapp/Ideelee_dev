
package snow.app.ideelee.responses.getstoredetailsres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("products_details")
    @Expose
    private List<ProductsDetail> productsDetails = null;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductsDetail> getProductsDetails() {
        return productsDetails;
    }

    public void setProductsDetails(List<ProductsDetail> productsDetails) {
        this.productsDetails = productsDetails;
    }

}
