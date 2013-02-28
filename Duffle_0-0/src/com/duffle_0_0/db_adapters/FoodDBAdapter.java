package com.duffle_0_0.db_adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.duffle_0_0.classes.Food;

//This will be FoodDBAdapter when put into actual project

public class FoodDBAdapter {
	public static final String DATABASE_NAME = "newFoodDB";
	public static final int DATABASE_VERSION = 1;
	
	public static final String ROW_ID = "_id";
	public static final String COLUMN_NAME_NAME = "name";
	public static final String COLUMN_NAME_TYPE = "type"; 
	public static final String COLUMN_NAME_CALORIES = "calories";
	public static final String COLUMN_NAME_FAT = "fat";
	public static final String COLUMN_NAME_PROTEIN =  "protein";
	
	private static final String DATABASE_TABLE = "foodInfo";
	
	private static final String CREATE_TABLE_FOOD = 
			"CREATE TABLE foodInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME_NAME + " TEXT,"
			+ COLUMN_NAME_TYPE + " TEXT,"
			+ COLUMN_NAME_CALORIES + " TEXT,"
			+ COLUMN_NAME_FAT + " TEXT,"
			+ COLUMN_NAME_PROTEIN + " TEXT" + ");" ;
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	private final Context context;
	
	/**
     * Constructor
     * @param ctx
     */
    public FoodDBAdapter(Context ctx) {
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
			System.out.println("DatabaseHelper -> Creating database...");
			db.execSQL(CREATE_TABLE_FOOD);
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
    public FoodDBAdapter open() throws SQLException {
        this.db = this.dbHelper.getWritableDatabase();
        System.out.println("FoodDBAdapter.open() -> printing db toString()..." + this.db.toString());
        return this;
    }

    /**
     * close the db 
     * return type: void
     */
    public void close() 
    {
        this.dbHelper.close();
    }
    
    /**
     * Create a new food. If the food is successfully created return the new
     * rowId for that food, otherwise return a -1 to indicate failure.
     * 
     * @param name
     * @param type
     * @param calories
     * @param fat
     * @param protein
     * @return rowId or -1 if failed
     */
    public long createFood(String name, /*String quantity,*/ String type, String calories, 
    		String fat, String protein) {
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(COLUMN_NAME_NAME, name);
    	initialValues.put(COLUMN_NAME_TYPE, type);
    	initialValues.put(COLUMN_NAME_CALORIES, calories);
    	initialValues.put(COLUMN_NAME_FAT, fat);
    	initialValues.put(COLUMN_NAME_PROTEIN, protein);
    	
    	return this.db.insert(DATABASE_TABLE, null, initialValues);
    }
 
    
 //=======================================================================
    public List<String> getAllNames() {
    	System.out.println("FoodDBAdapter -> in getAllNames");
		List<String> foodNames = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		String s;
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				s = cursor.getString(1);
				System.out.println("Retrieved " + s);
				// Adding contact to list
				foodNames.add(s);
				System.out.println("Sucessfully added "+s+ " to list");
			} while (cursor.moveToNext());
		}

		// return contact list
		System.out.println("FoodDBAdapter -> about to return get all names");
		
		for(int i=0; i<foodNames.size();i++)
		{
			System.out.println("i="+i+"  .... " +foodNames.get(i));
		}
		
		return foodNames;
	}
    
    public String[] getFoodNames() {
    	System.out.println("FoodDBAdapter -> in getFoodNames");
		String[] foodNames = null;
		// Select All Query
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		String s;
		
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			int i=0;
			do {
				s=cursor.getString(1);
				System.out.println("Retrieved "+s);
				// Adding contact to list
				foodNames[i]=s;
				System.out.println("Sucessfully added "+s+ " to list");
				i++;
			} while (cursor.moveToNext());
		}

		// return contact list
		System.out.println("FitDBAdapter---->about to return get all names");
		
		for(int i=0; i<foodNames.length;i++)
		{
			System.out.println("i="+i+"  .... " +foodNames[i]);
		}
		
		return foodNames;
	}
    
    public List<Food> getFoodList() {
		List<Food> foodList = new ArrayList<Food>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				//name, type, calories, fat protein
				Integer cals = Integer.parseInt(cursor.getString(3));
				Integer fat = Integer.parseInt(cursor.getString(4));
				Integer prot = Integer.parseInt(cursor.getString(5));
				Food f = new Food();
				f.setID(cursor.getLong(0));
				f.setName(cursor.getString(1));
				System.out.println("FoodDBAdapter --> cursor 0 = " + cursor.getString(0));
				f.setType(cursor.getString(2));
				f.setCalories(cals);
				f.setFat(fat);
				f.setProtein(prot);
				// Adding contact to list
				foodList.add(f);
				System.out.println("Sucessfully added "+f.getName()+ " to list");
			} while (cursor.moveToNext());
		}

		// return contact list
		return foodList;
	}
//=======================================================================    
    /**
     * Delete the food with the given rowId
     * 
     * @param rowId
     * @return true if deleted, false otherwise
     */
    public boolean deleteFood(long rowId) {
    	return this.db.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0;
    }
    
    /**
     * Return a Cursor over the list of all food in the database
     * 
     * @return Cursor over all food
     */
    public Cursor getAllFood() {
    	return this.db.query(DATABASE_TABLE, 
    			new String[] { 
    			ROW_ID, 
    			COLUMN_NAME_NAME, 
    			COLUMN_NAME_TYPE, 
    			COLUMN_NAME_CALORIES,
    			COLUMN_NAME_FAT, 
    			COLUMN_NAME_PROTEIN }, 
    			null, null, null, null, null);
    }
    
    
    /**
     * Return a Cursor positioned at the food that matches the given rowId
     * @param rowId
     * @return Cursor positioned to matching food, if found
     * @throws SQLException if food could not be found/retrieved
     */
    public Cursor getFood(long rowId) throws SQLException {	
    	Cursor mCursor = this.db.query(true,
    			DATABASE_TABLE, new String[] { ROW_ID, COLUMN_NAME_NAME, COLUMN_NAME_TYPE, 
    			COLUMN_NAME_CALORIES, COLUMN_NAME_FAT, COLUMN_NAME_PROTEIN },
    			ROW_ID + "=" + rowId, null, null, null, null, null);
    			
    	if(mCursor != null) {
    		mCursor.moveToFirst();
    	}
    	
    	return mCursor;
    }
    
    /**
     * Update the food.
     * 
     * @param rowId
	 * @param name
     * @param type
     * @param calories
     * @param fat
     * @param protein
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateFood(long rowId, String name, String type, 
    		int calories, int fat, int protein) {
    	ContentValues args = new ContentValues();
    	args.put(COLUMN_NAME_CALORIES, calories);
    	args.put(COLUMN_NAME_FAT, fat);
    	args.put(COLUMN_NAME_NAME, name);
    	args.put(COLUMN_NAME_PROTEIN, protein);
    	args.put(COLUMN_NAME_TYPE, type);
    	
    	return this.db.update(DATABASE_TABLE, args, ROW_ID, null) > 0;
    }
}
