package DataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import BussinessLayer.JobRequest;
import BussinessLayer.User;

public class UserDataHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "dbUsers";
    private static final String TABLE_USERS = "Users";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_MRANK = "employersrank";
    private static final String KEY_SRANK = "seekersrank";

    public UserDataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_MRANK + " INTEGER," + KEY_SRANK + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Create tables again
        onCreate(db);
    }


    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_EMAIL, user.getEmailAddress());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_MRANK, user.getEmployersRank());
        values.put(KEY_SRANK, user.getSeekersRank());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USERNAME,
                        KEY_EMAIL, KEY_PASSWORD, KEY_MRANK, KEY_SRANK }, KEY_USERNAME + "=?",
                new String[] { username }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
        // return contact
        return user;
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setEmailAddress(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmployersRank(Integer.parseInt(cursor.getString(3)));
                user.setSeekersRank(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                usersList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return usersList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmailAddress());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_MRANK, user.getEmployersRank());
        values.put(KEY_SRANK, user.getSeekersRank());

        // updating row
        return db.update(TABLE_USERS, values, KEY_USERNAME + " = ?",
                new String[] { user.getUsername() });
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USERNAME + " = ?",
                new String[] { user.getUsername() });
        db.close();
    }


    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

