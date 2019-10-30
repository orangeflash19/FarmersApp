package farmerapp.com;

import android.graphics.Bitmap;

public class AddProduct {
    String type, quantity, sold, remaining;
    Bitmap image;

    public AddProduct(String type, String quantity, String sold, String remaining, Bitmap image) {
        this.type = type;
        this.quantity = quantity;
        this.sold = sold;
        this.remaining = remaining;
        this.image = image;
    }

    public AddProduct(String type, Bitmap image) {
        this.type = type;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSold() {
        return sold;
    }

    public String getRemaining() {
        return remaining;
    }

    public Bitmap getImage() {
        return image;
    }
}


