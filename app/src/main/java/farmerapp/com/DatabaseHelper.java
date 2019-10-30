package farmerapp.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "farmer.db";
    private static final String TABLE_NAME = "Products";
    private static final String COL_1 = "Type";
    private static final String COL_2 = "Quantity";
    private static final String COL_3 = "Sold";
    private static final String COL_4 = "Remaining";
    private static final String COL_5 = "Image";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ProductId INTEGER PRIMARY KEY AUTOINCREMENT,Type TEXT,Quantity TEXT,Sold TEXT,Remaining TEXT,Image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    /*public Boolean insertData(St) {
            SQLiteDatabase database=this.getWritableDatabase();
            Bitmap imageToStoreBitmap=addProduct.getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] imageInBytes = byteArrayOutputStream.toByteArray();
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL_1,addProduct.getType());
            contentValues.put(COL_2,addProduct.getQuantity());
            contentValues.put(COL_3,addProduct.getSold());
            contentValues.put(COL_4,addProduct.getRemaining());
            contentValues.put(COL_5, imageInBytes);
            if(database.insert(TABLE_NAME,null,contentValues)!= 0){
                return true;
            }
        return false;
    }*/

    public ArrayList<AddProduct> getAllData(){
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            ArrayList<AddProduct> arrayList=new ArrayList<>();
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

                while(cursor.moveToNext()){
                    String type=cursor.getString(1);
                    String quantity=cursor.getString(2);
                    String sold=cursor.getString(3);
                    String remaining=cursor.getString(4);
                    byte[] image=cursor.getBlob(5);
                    Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
                    arrayList.add(new AddProduct(type,quantity,sold,remaining,bitmap));
                }
                return arrayList;
    }

    //for report activity
    public String getReportData(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        String info = "";

        while(cursor.moveToNext()){
            String type=cursor.getString(1);
            String quantity=cursor.getString(2);
            String sold=cursor.getString(3);
            String remaining=cursor.getString(4);
            byte[] image=cursor.getBlob(5);
            Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);

            info = info + "\n\n" + "Type: " + type + "\nQuantity: " + quantity + "\nSold: " + sold + "\nBought: " + remaining;
        }
        /*return cursor;*/
        return info;
    }

    public ArrayList<AddProduct> getBuyerData() {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<AddProduct> arrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT "+COL_1+","+COL_5+" FROM "+TABLE_NAME,null);

        while(cursor.moveToNext()){
            String type=cursor.getString(0);
            byte[] image=cursor.getBlob(1);
            Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
            arrayList.add(new AddProduct(type,bitmap));
        }

        Log.e(DatabaseHelper.class.getSimpleName(),String.valueOf(arrayList.size()));
        Log.e(DatabaseHelper.class.getSimpleName(),arrayList.toString());
        return arrayList;
    }

    public ArrayList<AddProduct> getOnlyData(String otype){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<AddProduct> arrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE Type='"+otype+"'",null);
        while(cursor.moveToNext()){
            String type=cursor.getString(1);
            String quantity=cursor.getString(2);
            String sold=cursor.getString(3);
            String remaining=cursor.getString(4);
            byte[] image=cursor.getBlob(5);
            Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
            arrayList.add(new AddProduct(type,quantity,sold,remaining,bitmap));
        }
        Log.e(DatabaseHelper.class.getSimpleName(),String.valueOf(arrayList.size()));
        Log.e(DatabaseHelper.class.getSimpleName(),arrayList.toString());
        return arrayList;
    }

    public void updateItem(String otype, String quantity,String sold,String remaining) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ArrayList<AddProduct> arrayList=getOnlyData(otype);
        AddProduct addProduct=arrayList.get(0);

        Bitmap imageToStoreBitmap=addProduct.getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] imageInBytes = byteArrayOutputStream.toByteArray();

        ContentValues values=new ContentValues();
        values.put(COL_1,otype);
        values.put(COL_2,quantity);
        values.put(COL_3,sold);
        values.put(COL_4,remaining);
        values.put(COL_5,imageInBytes);

        sqLiteDatabase.update(TABLE_NAME,values,COL_1+" = ?",new String[]{otype});
        sqLiteDatabase.close();
    }

    boolean insertData(String type, String quantity, String s, String quantity1, Bitmap imageToStore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        byte[] data = outputStream.toByteArray();
        ContentValues values = new ContentValues();
        values.put(COL_1,type);
        values.put(COL_2,quantity);
        values.put(COL_3,s);
        values.put(COL_4,quantity1);
        values.put(COL_5,data);
        long x=db.insert(TABLE_NAME, null, values);
        db.close();
        return x != 0;
    }
}
