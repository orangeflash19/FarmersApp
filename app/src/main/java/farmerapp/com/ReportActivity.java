package farmerapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    public TextView txtDisplay;
    //ArrayList<AddProduct> addProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        txtDisplay = findViewById(R.id.textView_report);
        showReport();
    }

    private void showReport(){
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String info = databaseHelper.getReportData();
        txtDisplay.setText(info);
        databaseHelper.close();

    }
}
