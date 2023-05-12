package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserManager.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla
    public static final String TABLE_USERS = "users";

    // Columnas de la tabla
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    // Crear tabla SQL query
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_ROLE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cuando la base de datos se crea por primera vez, aquí es donde se creará la tabla
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes manejar las actualizaciones a la base de datos
        // Se llama cuando se incrementa la DATABASE_VERSION
    }

    /**
     * Adds a new user to the database.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
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
    /**
     * Adds a new curator to the database.
     *
     * @param username The username of the curator.
     * @param password The password of the curator.
     */
    public void addCurator(String username, String password) {
        // Same as addUser, but the role is always "curator"
        addUser(username, password, "curator");
    }


    /**
     * Gets the role of a user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The role of the user.
     */
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

     /*public void deleteUser(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USERNAME + " = ?", new String[]{user});
        db.close();
    }*/
}
