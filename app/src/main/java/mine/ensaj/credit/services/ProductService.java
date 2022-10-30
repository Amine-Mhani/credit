package mine.ensaj.credit.services;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mine.ensaj.credit.classes.Product;
import mine.ensaj.credit.dao.IDao;
import mine.ensaj.credit.util.MySQLiteHelper;

public class ProductService implements IDao<Product> {

    private static final String TABLE_NAME ="product";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY ="category_id";


    private static String [] COLUMNS = {KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_CATEGORY};

    private MySQLiteHelper helper;

    public ProductService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }
    @Override
    public boolean create(Product e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, e.getName());
        values.put(KEY_DESCRIPTION, e.getDescription());
        values.put(KEY_CATEGORY, e.getCategory());
        db.insert(TABLE_NAME,
                null,
                values);
        Log.d("insert", e.getName()+"");
        db.close();
        return false;
    }

    @Override
    public boolean update(Product o) {
        return false;
    }

    @Override
    public boolean delete(Product o) {
        return false;
    }

    @Override
    public Product findById(int id) {
        Product e = null;
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
            e = new Product();
            e.setId(c.getInt(0));
            e.setName(c.getString(1));
            e.setDescription(c.getString(2));
            e.setCategory(c.getInt(3));
        }
        db.close();
        return e;
    }

    @Override
    public List<Product> findAll() {
        List<Product> eds = new ArrayList<>();
        String req ="select * from "+TABLE_NAME;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        Product e = null;
        if(c.moveToFirst()){
            do{
                e = new Product();
                e.setId(c.getInt(0));
                e.setName(c.getString(1));
                e.setDescription(c.getString(2));
                e.setCategory(c.getInt(3));
                eds.add(e);
                Log.d("id = ", e.getId()+"");
            }while(c.moveToNext());
        }
        return eds;
    }
}
