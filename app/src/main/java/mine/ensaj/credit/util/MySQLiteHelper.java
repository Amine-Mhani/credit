package mine.ensaj.credit.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "dukan";

    private static final String CREATE_TABLE_CLIENT = "create table client(" +
            "id INTEGER primary key autoincrement," +
            "nom TEXT," +
            "prenom TEXT," +
            "cin TEXT," +
            "telephone INTEGER)";

    private static final String CREATE_TABLE_CATEGORY = "create table category(" +
            "id INTEGER primary key autoincrement," +
            "name STRING," +
            "description STRING)";

    private static final String CREATE_TABLE_PRODUCT = "create table product(" +
            "id INTEGER primary key autoincrement," +
            "name STRING," +
            "description STRING," +
            "category_id INTEGER NOT NULL," +
            "FOREIGN KEY (category_id) REFERENCES category(id))";

    private static final String CREATE_TABLE_CREDIT = "create table credit(" +
            "id INTEGER primary key autoincrement," +
            "prix FLOAT," +
            "client_id INTEGER NOT NULL," +
            "category_id INTEGER NOT NULL," +
            "product_id INTEGER NOT NULL,"+
            "date DATE,"+
            "etat STRING," +
            "FOREIGN KEY (client_id) REFERENCES client(id)," +
            "FOREIGN KEY (category_id) REFERENCES category(id)," +
            "FOREIGN KEY (product_id) REFERENCES product(id))";



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_CREDIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists credit");
        db.execSQL("DROP table if exists product");
        db.execSQL("DROP table if exists category");
        db.execSQL("DROP table if exists client");
        this.onCreate(db);
    }
}