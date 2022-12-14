package mine.ensaj.credit.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import mine.ensaj.credit.classes.Client;
import mine.ensaj.credit.classes.Credit;
import mine.ensaj.credit.dao.IDao;
import mine.ensaj.credit.util.MySQLiteHelper;


public class CreditService implements IDao<Credit> {


    private static final String TABLE_NAME ="credit";

    private static final String KEY_ID = "id";
    private static final String KEY_PRIX = "prix";
    private static final String KEY_CLIENT = "client_id";
    private static final String KEY_CATEGORY ="category_id";
    private static final String KEY_PRODUCT ="product_id";
    private static final String KEY_DATE ="date";
    private static final String KEY_ETAT ="etat";

    private static String [] COLUMNS = {KEY_ID, KEY_PRIX, KEY_CLIENT, KEY_CATEGORY, KEY_PRODUCT, KEY_DATE, KEY_ETAT};

    private MySQLiteHelper helper;

    public CreditService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    @Override
    public boolean create(Credit e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRIX, e.getPrix());
        values.put(KEY_CLIENT, e.getClient());
        values.put(KEY_CATEGORY, e.getCategory());
        values.put(KEY_PRODUCT, e.getProduct());
        values.put(KEY_DATE, e.getDate()+"");
        values.put(KEY_ETAT, e.getEtat());
        db.insert(TABLE_NAME,
                null,
                values);
        Log.d("insert", e.getPrix()+"");
        db.close();
        return false;
    }

    public boolean update(Credit e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, e.getId());
        values.put(KEY_PRIX, e.getPrix());
        values.put(KEY_CLIENT, e.getClient());
        values.put(KEY_CATEGORY, e.getCategory());
        values.put(KEY_PRODUCT, e.getProduct());
        values.put(KEY_DATE, e.getDate()+"");
        values.put(KEY_ETAT, e.getEtat());

        db.update(TABLE_NAME,
                values,
                "id = ?",
                new String[]{e.getId()+""});
        db.close();
        return true;
    }


    public Credit findById(int id){
        Credit e = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c;
        c = db.query(TABLE_NAME,
                COLUMNS,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(c.moveToFirst()){
            e = new Credit();
            e.setId(c.getInt(0));
            e.setPrix(c.getFloat(1));
            e.setClient(c.getInt(2));
            e.setCategory(c.getInt(3));
            e.setProduct(c.getInt(4));
            e.setDate(Date.valueOf(c.getString(5)));
            e.setEtat(c.getString(6));
        }
        db.close();
        return e;
    }


    public boolean delete(Credit e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.delete(TABLE_NAME,
                "id = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
        return true;
    }

    public List<Credit> findAll(){
        List<Credit> eds = new ArrayList<>();
        String req ="select * from "+TABLE_NAME;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        Credit e = null;
        if(c.moveToFirst()){
            do{
                e = new Credit();
                e.setId(c.getInt(0));
                e.setPrix(c.getFloat(1));
                e.setClient(c.getInt(2));
                e.setCategory(c.getInt(3));
                e.setProduct(c.getInt(4));
                e.setDate(Date.valueOf(c.getString(5)));
                e.setEtat(c.getString(6));
                eds.add(e);
                Log.d("id = ", e.getId()+"");
            }while(c.moveToNext());
        }
        Log.d("findall", eds.get(0).getEtat());
        return eds;

    }

    public List<Credit> findNonPay??(){
        List<Credit> eds = new ArrayList<>();
        String req ="select * from "+TABLE_NAME+" where etat == 'Non Pay??'";
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        Credit e = null;
        if(c.moveToFirst()){
            do{
                e = new Credit();
                e.setId(c.getInt(0));
                e.setPrix(c.getFloat(1));
                e.setClient(c.getInt(2));
                e.setCategory(c.getInt(3));
                e.setProduct(c.getInt(4));
                e.setDate(Date.valueOf(c.getString(5)));
                e.setEtat(c.getString(6));
                eds.add(e);
                Log.d("id = ", e.getId()+"");
            }while(c.moveToNext());
        }
        Log.d("findall", eds.get(0).getEtat());
        return eds;

    }
}
