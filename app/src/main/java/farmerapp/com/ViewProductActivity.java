package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.List;


public class ViewProductActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    List<AddProduct> addProductList;
    RVadapter rVadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        recyclerView=findViewById(R.id.recyclerView);
        databaseHelper=new DatabaseHelper(this);

        addProductList=databaseHelper.getAllData();
        rVadapter=new RVadapter(addProductList);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rVadapter);
    }

}
