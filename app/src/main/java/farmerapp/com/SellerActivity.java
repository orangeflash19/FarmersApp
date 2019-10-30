package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SellerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

    }

    public void moveToAddProduct(View view) {
        startActivity(new Intent(SellerActivity.this,AddProductActivity.class));
    }

    public void moveToViewProduct(View view) {
        startActivity(new Intent(SellerActivity.this,ViewProductActivity.class));
    }
}
