package it.dennis;
 
import android.graphics.Bitmap;
 
public class Laptop {
    private String brand;
    private String model;
    private String description;
    private String techDetails;
    private String price;
    private String imageURL;
    private Bitmap imageBitmap;
 
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTechDetails() {
        return techDetails;
    }
    public void setTechDetails(String techDetails) {
        this.techDetails = techDetails;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
}