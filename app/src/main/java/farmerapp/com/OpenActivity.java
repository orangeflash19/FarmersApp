package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OpenActivity extends AppCompatActivity {

    TextView tvType,tvQuantity;
    Button btnBuy;
    ImageView ivDisplay;
    EditText etBuy,etLocation,etName;
    DatabaseHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        ivDisplay=findViewById(R.id.ivDisplay);
        tvType=findViewById(R.id.tvType);
        tvQuantity=findViewById(R.id.tvQuantity);
        btnBuy=findViewById(R.id.btnBuy);
        etBuy=findViewById(R.id.etBuy);
        etName=findViewById(R.id.etName);
        etLocation=findViewById(R.id.etLocation);

        dh=new DatabaseHelper(this);

        Intent intent=getIntent();
        final String type=intent.getStringExtra("type");
        ArrayList<AddProduct> arrayList=dh.getOnlyData(type);
        AddProduct addProduct=arrayList.get(0);
        ivDisplay.setImageBitmap(addProduct.getImage());
        tvType.setText(addProduct.getType());
        tvQuantity.setText(addProduct.getRemaining());

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long demand = Long.parseLong(etBuy.getText().toString());
                long supply = Long.parseLong(tvQuantity.getText().toString());
                if(demand<=supply){
                    long unit=supply-demand;
                    Intent intent1=new Intent(OpenActivity.this,BillActivity.class);
                    intent1.putExtra("type",type);
                    intent1.putExtra("quantity",String.valueOf(supply));
                    intent1.putExtra("sold",String.valueOf(demand));
                    intent1.putExtra("remaining",String.valueOf(unit));
                    intent1.putExtra("name",etName.getText().toString());
                    intent1.putExtra("location",etLocation.getText().toString());
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(OpenActivity.this, "Demand must be Less than supply", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
