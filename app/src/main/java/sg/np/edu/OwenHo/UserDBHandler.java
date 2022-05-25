package sg.np.edu.OwenHo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public UserDBHandler(Context context)
    {
        super(context, "userDB.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "
                + TABLE_USERS
                +"(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User u) {
        /*
         * INSERT INTO USER VALUES("name","description", "followed")
         */
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, u.name);
        values.put(COLUMN_DESCRIPTION, u.description);
        if(u.followed){
            values.put(COLUMN_FOLLOWED,1);
        }
        else{
            values.put(COLUMN_FOLLOWED,0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS,null,values);
        //db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES(\""+u.name+"\",\""+u.description+"\",\""+u.followed+"\")");
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        /*
         * SELECT * FROM USERS
         */
        ArrayList<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS,null);
        while(cursor.moveToNext()){
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            if(cursor.getInt(3)==1){
                u.followed = true;
            }
            else{
                u.followed = false;
            }
            userList.add(u);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User u){
        /*
         * UPDATE user SET followed = 0/1 WHERE name = name
         */
        Integer followedBool;
        if (u.followed) {
            followedBool = 1;
        }
        else{
            followedBool = 0;
        }
        String query = "UPDATE "+TABLE_USERS+" SET "
                + COLUMN_FOLLOWED + " = "
                + followedBool + " WHERE "
                + COLUMN_NAME + " = \""
                + u.name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public boolean deleteUserbyName(String name) {

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_NAME + " = \""
                + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User u = new User();
        if (cursor.moveToFirst()) {
            u.id = cursor.getInt(2);
            db.delete(TABLE_USERS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(u.id) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}
