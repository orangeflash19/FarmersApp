package farmerapp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    EditText etType, etQuantity;
    private Bitmap bitmap;
    Button mCaptureBtn;
    private ImageView ivDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etType = findViewById(R.id.etType);
        etQuantity = findViewById(R.id.etQuantity);
        ivDisplay = findViewById(R.id.imageView);

        mCaptureBtn = findViewById(R.id.capture_image_btn);
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(AddProductActivity.this);
            }
        });
    }

    public void postAdd(View view) {
        String quantity = etQuantity.getText().toString();
        String type = etType.getText().toString();

        if (db.insertData(type, quantity, "0", quantity, bitmap)) {
            Toast.makeText(this, "Product Added", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Error Adding Product!", Toast.LENGTH_SHORT).show();
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your product picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == 0) {
                if (resultCode == RESULT_OK && data != null) {
                    bitmap = null;
                    bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    ivDisplay.setImageBitmap(bitmap);
                }
            }
        }
    }
}
