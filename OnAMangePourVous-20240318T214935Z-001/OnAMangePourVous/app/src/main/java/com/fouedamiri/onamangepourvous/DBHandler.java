package com.fouedamiri.onamangepourvous;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Evaluations.db";

    private static final String TABLE_NAME = "Evaluation";

    // Column names
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_FOOD = "food";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STARS = "stars";
    private static final String COLUMN_ID_RESTAURANT = "id" ;
    public static final String COLUMN_NAME_RESTAURANT = "name";
    public static final String COLUMN_ADDRESS_RESTAURANT = "adress";
    private final Context context;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID_RESTAURANT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_RESTAURANT + " TEXT," +
                COLUMN_ADDRESS_RESTAURANT + " TEXT," +
                COLUMN_SERVICE + " TEXT, " +
                COLUMN_FOOD + " TEXT, " +
                COLUMN_PRICE + " FLOAT, " +
                COLUMN_STARS + " INTEGER" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEvaluation(String name, String address, String service, String food, Float price, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_RESTAURANT, name);
        values.put(COLUMN_ADDRESS_RESTAURANT, address);
        values.put(COLUMN_SERVICE, service);
        values.put(COLUMN_FOOD, food);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_NAME, null, values);
        if (result==-1){
            Toast.makeText(context, "Failed to add.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Evaluation enregistrée!", Toast.LENGTH_SHORT).show();
        }
    }
    /*public Cursor getAllEvaluations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }*/
    public ArrayList getAllrecord() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);
            String t7 = res.getString(6);
            arrayList.add(t1 + " \nNom: " + t2 + " \nAdresse: " + t3 + " \nService: " + t4 + " \nPlats: " + t5 + " \nPrix moyen en dt: " + t6+ "\nNote globale sur 5: "+t7);
            res.moveToNext();
            res.close();
            db.close();
        }
        return arrayList;


    }





   public void updateData (String name, String address, String service, String food, String price, String stars){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put ("name",name);
        contentValues.put("adress",address);
       contentValues.put ("service ", (service));
       contentValues.put ("food", (food));
        contentValues.put ("price", price );
        contentValues.put ("stars", (stars));
        db.update("Restaurants",contentValues,"idRestaurant= ?",new String[]{ }) ;
   }
    public void deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID_RESTAURANT + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

}