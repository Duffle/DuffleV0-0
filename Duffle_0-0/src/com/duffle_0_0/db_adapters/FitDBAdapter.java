package com.duffle_0_0.db_adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.duffle_0_0.classes.Workout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FitDBAdapter {
	public static final String DATABASE_NAME = "newFitDB";
	public static final int DATABASE_VERSION = 1;
	
	public static final String FIT_ROW_ID = "_id";
	public static final String FIT_COLUMN_NAME_NAME = "name";
	public static final String FIT_COLUMN_NAME_TYPE = "type"; 
	public static final String FIT_COLUMN_NAME_CALORIES = "calories";
	public static final String FIT_COLUMN_NAME_DATE = "date";
	
	private static final String FIT_DATABASE_TABLE = "fitInfo";
	
	private static final String CREATE_TABLE_FIT = 
			"CREATE TABLE fitInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ FIT_COLUMN_NAME_NAME + " TEXT,"
			+ FIT_COLUMN_NAME_DATE + " TEXT,"
			+ FIT_COLUMN_NAME_TYPE + " TEXT,"
			+ FIT_COLUMN_NAME_CALORIES + " TEXT" + ");";
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
//	private String[] allColumns = { FIT_ROW_ID,
//		      FIT_COLUMN_NAME_NAME };
	
	private final Context context;
	
	/**
     * Constructor
     * @param ctx
     */
    public FitDBAdapter(Context ctx) {
        this.context = ctx;
        this.dbHelper = new DatabaseHelper(this.context);
    }
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//Create multiple tables here
			System.out.println("New DatabaseHelper -> Creating database...");
			db.execSQL(CREATE_TABLE_FIT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//Add table mods here
		}
	}
	
	/**
     * open the db
     * @return this
     * @throws SQLException
     * return type: DBAdapter
     */
    public FitDBAdapter open() throws SQLException {
        this.db = this.dbHelper.getWritableDatabase();
        System.out.println("FitDBAdapter.open() -> printing db toString()..." + this.db.toString());
        return this;
    }

    /**
     * close the db 
     * return type: void
     */
    public void close() 
    {
    	if (dbHelper != null)
    		dbHelper.close();
    }
    
    /**
     * Create a new workout. If the workout is successfully created return the new
     * rowId for that workout, otherwise return a -1 to indicate failure.
     * 
     * @param name
     * @param date
     * @param type
     * @param calories
     * @return rowId or -1 if failed
     */
    public long createFit(String name, String type, String calories) {
    	//get date information
    	Calendar cal= new GregorianCalendar();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    	System.out.println(dateFormat.format(cal.getTime()));
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(FIT_COLUMN_NAME_NAME, name);
    	initialValues.put(FIT_COLUMN_NAME_DATE, dateFormat.format(cal.getTime()));
    	initialValues.put(FIT_COLUMN_NAME_TYPE, type);
    	initialValues.put(FIT_COLUMN_NAME_CALORIES, calories);
    	
    	return this.db.insert(FIT_DATABASE_TABLE, null, initialValues);
    }
    
    /**
     * Delete the workout with the given rowId
     * 
     * @param rowId
     * @return true if deleted, false otherwise
     */
    public boolean deleteFit(long rowId) {
    	return this.db.delete(FIT_DATABASE_TABLE, FIT_ROW_ID + "=" + rowId, null) > 0;
    }
    
    /**
     * Return a Cursor over the list of all names in the database
     * 
     * @return Cursor over all names
     */
    
    public List<String> getAllNames() {
    	System.out.println("FitDBAdapter---->in get all names");
		List<String> workoutNames = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + FIT_DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		String s;
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				s=cursor.getString(1);
				System.out.println("Retrieved "+s);
				// Adding contact to list
				workoutNames.add(s);
				System.out.println("Sucessfully added "+s+ " to list");
			} while (cursor.moveToNext());
		}

		// return contact list
		System.out.println("FitDBAdapter---->about to return get all names");
		
		for(int i=0; i<workoutNames.size();i++)
		{
			System.out.println("i="+i+"  .... " +workoutNames.get(i));
		}
		
		return workoutNames;
	}
    
    public String[] getWorkoutNames() {
    	System.out.println("FitDBAdapter---->in get workout names");
		String[] workoutNames = null;
		// Select All Query
		String selectQuery = "SELECT  * FROM " + FIT_DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		String s;
		
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			int i=0;
			do {
				s=cursor.getString(1);
				System.out.println("Retrieved "+s);
				// Adding contact to list
				workoutNames[i]=s;
				System.out.println("Sucessfully added "+s+ " to list");
				i++;
			} while (cursor.moveToNext());
		}

		// return contact list
		System.out.println("FitDBAdapter---->about to return get all names");
		
		for(int i=0; i<workoutNames.length;i++)
		{
			System.out.println("i="+i+"  .... " +workoutNames[i]);
		}
		
		return workoutNames;
	}
    
    public List<Workout> getAllWorkouts() {
		List<Workout> workoutList = new ArrayList<Workout>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + FIT_DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Workout w = new Workout();
				w.setID(Integer.parseInt(cursor.getString(0)));
				w.setName(cursor.getString(1));
				System.out.println("getting date");
				w.setDate(cursor.getString(2));
				w.setType(cursor.getString(3));
				System.out.println("getting cals");
				w.setCal(Integer.parseInt(cursor.getString(4)));
				// Adding contact to list
				workoutList.add(w);
				System.out.println("Sucessfully added "+w.getName()+ " to list");
			} while (cursor.moveToNext());
		}

		// return contact list
		return workoutList;
	}
    
    public Cursor getAllFitness() {
    	return this.db.query(FIT_DATABASE_TABLE, 
    			new String[] { 
    			FIT_ROW_ID, 
    			FIT_COLUMN_NAME_NAME, 
    			FIT_COLUMN_NAME_TYPE, 
    			FIT_COLUMN_NAME_CALORIES }, 
    			null, null, null, null, null);
    }
    
    /**
     * Return a Cursor positioned at the fit that matches the given rowId
     * @param rowId
     * @return Cursor positioned to matching fit, if found
     * @throws SQLException if can could not be found/retrieved
     */
    public Cursor getFit(long rowId) throws SQLException {
    	Cursor mCursor = this.db.query(true,
    			FIT_DATABASE_TABLE, new String[] { FIT_ROW_ID, FIT_COLUMN_NAME_NAME, FIT_COLUMN_NAME_TYPE, 
    			FIT_COLUMN_NAME_CALORIES },
    			FIT_ROW_ID + "=" + rowId, null, null, null, null, null);
    			
    	if(mCursor != null) {
    		mCursor.moveToFirst();
    	}
    	
    	return mCursor;
    }
    
    /**
     * Update the fit.
     * 
     * @param rowId
	 * @param name
     * @param type
     * @param calories
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateFit(long rowId, String name, String type, 
    		String calories) {
    	ContentValues args = new ContentValues();
    	args.put(FIT_COLUMN_NAME_CALORIES, calories);
    	args.put(FIT_COLUMN_NAME_NAME, name);
    	args.put(FIT_COLUMN_NAME_TYPE, type);
    	
    	return this.db.update(FIT_DATABASE_TABLE, args, FIT_ROW_ID + "=" + rowId, null) > 0;
    }
    
    private Workout cursorToWorkout(Cursor cursor) {
        Workout w = new Workout();
        w.setID(cursor.getLong(0));
        w.setName(cursor.getString(1));
        return w;
      }
}
