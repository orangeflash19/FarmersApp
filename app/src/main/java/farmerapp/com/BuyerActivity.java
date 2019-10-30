package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BuyerActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<AddProduct> addProductList;
    BuyerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        databaseHelper=new DatabaseHelper(this);
        addProductList=databaseHelper.getBuyerData();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new BuyerAdapter(addProductList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BuyerAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
               AddProduct addProduct=addProductList.get(position);
                openItem(addProduct.getType());
            }
        });

    }

    private void openItem(String type) {
        startActivity(new Intent(BuyerActivity.this,OpenActivity.class).putExtra("type",type));
    }
}
