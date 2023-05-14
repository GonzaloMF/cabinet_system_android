package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.csc306_project.ui.models.Artefact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserManager.db";
    private static final int DATABASE_VERSION = 3;

    // Table names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_ARTEFACTS = "artefacts";

    // Table user fields
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    // Table artefacts fields
    public static final String COLUMN_ARTEFACT_ID = "id";
    public static final String COLUMN_ARTEFACT_TITLE = "title";
    public static final String COLUMN_ARTEFACT_DESCRIPTION = "description";
    public static final String COLUMN_ARTEFACT_HISTORY = "history";
    public static final String COLUMN_ARTEFACT_IMAGE_PATH = "image_path";

    // Creates, SQL queries
    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_ROLE + " TEXT)";

    private static final String SQL_CREATE_ARTEFACTS =
            "CREATE TABLE " + TABLE_ARTEFACTS + " (" +
                    COLUMN_ARTEFACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ARTEFACT_TITLE + " TEXT," +
                    COLUMN_ARTEFACT_DESCRIPTION + " TEXT," +
                    COLUMN_ARTEFACT_HISTORY + " TEXT," +
                    COLUMN_ARTEFACT_IMAGE_PATH + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_ARTEFACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Calls the database everytime that the database is updated
        if (oldVersion < 2) {
            db.execSQL(SQL_CREATE_ARTEFACTS);
        }
        if (oldVersion < 3) {
            String SQL_ADD_IMAGE_PATH_TO_ARTEFACTS = "ALTER TABLE " + TABLE_ARTEFACTS + " ADD COLUMN " + COLUMN_ARTEFACT_IMAGE_PATH + " TEXT";
            db.execSQL(SQL_ADD_IMAGE_PATH_TO_ARTEFACTS);
        }
    }

    /**
     * Functions about user
     */
    // Add new user
    public void addUser(String username, String password, String role) {
        // Get the database in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_USERS, null, values);

        // Close the database
        db.close();
    }

    // Get role about user
    public String getUserRole(String username, String password) {
        // Get the database in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database to use
        String[] projection = { COLUMN_ROLE };

        // Define a selection criteria
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };

        // Query the database
        Cursor cursor = db.query(
                TABLE_USERS,   // The table to query
                projection,    // The columns to return
                selection,     // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,          // Don't group the rows
                null,          // Don't filter by row groups
                null           // The sort order
        );

        String role = null;
        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE));
        }

        cursor.close();
        db.close();

        return role;
    }
    /** ****************************************************** */

    // Add new curator
    public void addCurator(String username, String password) {
        addUser(username, password, "curator");
    }


    /**
     * Functions about artefacts
     */
    // Add new artefacts
    public void addArtefact(Artefact artefact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTEFACT_TITLE, artefact.getTitle());
        values.put(COLUMN_ARTEFACT_DESCRIPTION, artefact.getDescription());
        values.put(COLUMN_ARTEFACT_HISTORY, artefact.getHistory());
        values.put(COLUMN_ARTEFACT_IMAGE_PATH, artefact.getImagePath());

        long newId = db.insert(TABLE_ARTEFACTS, null, values);
        artefact.setId((int) newId);
        db.close();
    }
    // Update artefacts
    public void updateArtefact(Artefact artefact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTEFACT_TITLE, artefact.getTitle());
        values.put(COLUMN_ARTEFACT_DESCRIPTION, artefact.getDescription());
        values.put(COLUMN_ARTEFACT_HISTORY, artefact.getHistory());
        values.put(COLUMN_ARTEFACT_IMAGE_PATH, artefact.getImagePath());

        db.update(TABLE_ARTEFACTS, values, COLUMN_ARTEFACT_ID + " = ?", new String[]{String.valueOf(artefact.getId())});
        db.close();
    }

    // Get new artefacts
    public List<Artefact> getArtefacts() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ARTEFACT_ID,
                COLUMN_ARTEFACT_TITLE,
                COLUMN_ARTEFACT_DESCRIPTION,
                COLUMN_ARTEFACT_HISTORY,
                COLUMN_ARTEFACT_IMAGE_PATH
        };

        Cursor cursor = db.query(
                TABLE_ARTEFACTS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Artefact> artefacts = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_DESCRIPTION));
            String history = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_HISTORY));
            String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_IMAGE_PATH));
            Artefact artefact = new Artefact(id, title, description, history, imagePath);
            artefacts.add(artefact);
        }
        cursor.close();
        db.close();

        return artefacts;
    }

    // Delete artefacts
    public void deleteArtefact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTEFACTS, COLUMN_ARTEFACT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    /** ****************************************************** */

    /**
     * Implementation for Pending artefacts
     */
    /*
    public List<Artefact> getPendingArtefacts() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_ARTEFACT_ID,
                COLUMN_ARTEFACT_TITLE,
                COLUMN_ARTEFACT_DESCRIPTION,
                COLUMN_ARTEFACT_HISTORY,
                COLUMN_ARTEFACT_IMAGE_PATH,
                COLUMN_ARTEFACT_PENDING
        };

        String selection = COLUMN_ARTEFACT_PENDING + " = ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = db.query(
                TABLE_ARTEFACTS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<Artefact> artefacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_DESCRIPTION));
            String history = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_HISTORY));
            String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_IMAGE_PATH));
            boolean isPending = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTEFACT_PENDING)) == 1;

            Artefact artefact = new Artefact(id, title, description, history, imagePath, isPending);
            artefacts.add(artefact);
        }
        cursor.close();
        db.close();

        return artefacts;
    }


    public boolean isUserCurator(String username) {
        // Get the database in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database to use
        String[] projection = {COLUMN_ROLE};

        // Define a selection criteria
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        // Query the database
        Cursor cursor = db.query(
                TABLE_USERS,   // The table to query
                projection,    // The columns to return
                selection,     // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,          // Don't group the rows
                null,          // Don't filter by row groups
                null           // The sort order
        );

        boolean isCurator = false;
        if (cursor.moveToFirst()) {
            String role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE));
            isCurator = "curator".equals(role);
        }

        cursor.close();
        db.close();

        return isCurator;
    }

     */
}