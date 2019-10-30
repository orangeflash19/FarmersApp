package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BillActivity extends AppCompatActivity {
    Button btnOpenMap;
    DatabaseHelper dh;
    TextView tvType,tvName,tvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        String quantity=intent.getStringExtra("quantity");
        String sold=intent.getStringExtra("sold");
        String remaining=intent.getStringExtra("remaining");
        String name=intent.getStringExtra("name");
        final String location=intent.getStringExtra("location");

        tvType=findViewById(R.id.tvType);
        tvName=findViewById(R.id.tvName);
        tvLocation=findViewById(R.id.tvLocation);
        btnOpenMap=findViewById(R.id.btnOpenMap);

        String x=type+"\t"+remaining;
        tvType.setText(x);
        tvName.setText(name);
        tvLocation.setText(location);

        dh=new DatabaseHelper(this);
        dh.updateItem(type, quantity, sold, remaining);

        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+location));
                startActivity(intent);
            }
        });
    }
}
