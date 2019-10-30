package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToBuyerPage(View view) {
        startActivity(new Intent(MainActivity.this,BuyerActivity.class));
    }

    public void moveToSellerPage(View view) {
        startActivity(new Intent(MainActivity.this,SellerActivity.class));
    }

    public void showReport(View view) {
        startActivity(new Intent(MainActivity.this, ReportActivity.class));
    }
}
