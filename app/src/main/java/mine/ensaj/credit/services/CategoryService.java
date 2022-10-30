package mine.ensaj.credit.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mine.ensaj.credit.classes.Category;
import mine.ensaj.credit.dao.IDao;
import mine.ensaj.credit.util.MySQLiteHelper;

public class CategoryService implements IDao<Category> {

    private static final String TABLE_NAME ="category";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";



    private static String [] COLUMNS = {KEY_ID, KEY_NAME, KEY_DESCRIPTION};

    private MySQLiteHelper helper;

    public CategoryService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }
    @Override
    public boolean create(Category e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, e.getName());
        values.put(KEY_DESCRIPTION, e.getDescription());

        db.insert(TABLE_NAME,
                null,
                values);
        Log.d("insert", e.getName()+"");
        db.close();
        return false;
    }

    @Override
    public boolean update(Category o) {
        return false;
    }

    @Override
    public boolean delete(Category o) {
        return false;
    }

    @Override
    public Category findById(int id) {
        Category e = null;
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
            e = new Category();
            e.setId(c.getInt(0));
            e.setName(c.getString(1));
            e.setDescription(c.getString(2));
        }
        db.close();
        return e;
    }

    @Override
    public List<Category> findAll() {
        List<Category> eds = new ArrayList<>();
        String req ="select * from "+TABLE_NAME;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        Category e = null;
        if(c.moveToFirst()){
            do{
                e = new Category();
                e.setId(c.getInt(0));
                e.setName(c.getString(1));
                e.setDescription(c.getString(2));
                eds.add(e);
                Log.d("id = ", e.getId()+"");
            }while(c.moveToNext());
        }
        return eds;
    }
}
