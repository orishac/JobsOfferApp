package DataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import BussinessLayer.JobRequest;


public class JobsDataHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbusers";
    private static final String TABLE_CONTACTS = "Jobs";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";

    public JobsDataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_CONTENT + " TEXT," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }


    public void addJob(JobRequest jobRequest) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, jobRequest.getTitle());
        values.put(KEY_CONTENT, jobRequest.getContent());
        values.put(KEY_DATE, jobRequest.getDate());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public JobRequest getJob(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_TITLE, KEY_CONTENT, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        JobRequest job = new JobRequest(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return job;
    }

    public List<JobRequest> getAllJobs() {
        List<JobRequest> jobsList = new ArrayList<JobRequest>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                JobRequest job = new JobRequest();
                job.setID(Integer.parseInt(cursor.getString(0)));
                job.setTitle(cursor.getString(1));
                job.setContent(cursor.getString(2));
                job.setDate(cursor.getString(3));
                // Adding contact to list
                jobsList.add(job);
            } while (cursor.moveToNext());
        }

        // return contact list
        return jobsList;
    }

    public List<String> getAllJobsTitles() {
        List<JobRequest> jobsList = getAllJobs();
        List<String> titles = new ArrayList<>();
        for (JobRequest job : jobsList) {
            titles.add(job.getTitle());
        }
        return titles;
    }

    public int updateJob(JobRequest jobRequest) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, jobRequest.getTitle());
        values.put(KEY_CONTENT, jobRequest.getContent());
        values.put(KEY_DATE, jobRequest.getDate());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(jobRequest.getID()) });
    }

    public void deleteJob(JobRequest jobRequest) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(jobRequest.getID()) });
        db.close();
    }


    public int getJobsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}