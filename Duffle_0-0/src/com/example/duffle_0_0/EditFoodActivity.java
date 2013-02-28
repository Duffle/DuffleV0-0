package com.example.duffle_0_0;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.duffle_0_0.db_adapters.FoodDBAdapter;
 
public class EditFoodActivity extends Activity {
	FoodDBAdapter dbAdapter;
	long id;
	String nm, ty;
	int cals, prot, ft;

	EditText name, type, calories, protein, fat;
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_edit_food);

 
        Intent i = getIntent();
        // getting attached intent data
        id= i.getLongExtra("id", -1);
        
        System.out.println("The id of the food was " + id);
        
		// Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
		System.out.println("EditFood -> Creating the database...");
		dbAdapter = new FoodDBAdapter(this.getBaseContext());
		System.out.println("EditFood -> Successfully created database!");
		System.out.println("EditFood -> " + dbAdapter.toString());
		
		name = (EditText) findViewById(R.id.foodEditNameText);
		type = (EditText) findViewById(R.id.foodEditTypeText);
		calories = (EditText) findViewById(R.id.foodEditCalsText);
		protein = (EditText) findViewById(R.id.foodEditProteinText);
		fat = (EditText) findViewById(R.id.foodEditFatText);
			
		//Set text fields eqla to DB values
		System.out.println("EditFood -> Opening db...");
		dbAdapter.open();
		System.out.println("EditFood -> Opened db! Pulling user selected entry...");
		//good above this
		Cursor cursor = dbAdapter.getFood(id);
		System.out.println("EditFood -> got cursor...");
		
		nm = cursor.getString(1);
		name.setText(nm);
		System.out.println("EditFood -> got name...:"+nm);
		
		ty=cursor.getString(2);
		type.setText(ty);
		System.out.println("EditFood -> got type...:"+ty);
		
		cals = cursor.getInt(3);
		calories.setText(Integer.toString(cals));
		System.out.println("EditFood -> got cals...:"+cals);
		
		prot = cursor.getInt(4);
		protein.setText(Integer.toString(prot));
		System.out.println("EditFood -> got cals...:"+prot);
		
		ft = cursor.getInt(5);
		fat.setText(Integer.toString(ft));
		System.out.println("EditFood -> got cals...:"+ft);
		
		dbAdapter.close();
 
    }
    
	public void upDBEntryClick(View view) {
		System.out.println("EditFoodAct.up -> Opening db...");
		dbAdapter.open();
		System.out.println("EditFoodAct.up -> Opened db! Updating user selected entry...");
		
		System.out.println("EditFoodAct.up -> getting new name...");
		nm=name.getText().toString();
		
		System.out.println("EditFoodAct.up -> getting new type...");
		ty= type.getText().toString();
		
		System.out.println("EditFoodAct.up -> getting new cals...");
		cals= Integer.parseInt(calories.getText().toString());
		
		System.out.println("EditFoodAct.up -> getting new cals...");
		prot= Integer.parseInt(protein.getText().toString());
		
		System.out.println("EditFoodAct.up -> getting new cals...");
		ft= Integer.parseInt(fat.getText().toString());
		
		System.out.println(nm + " " + ty + " " + cals + " " + prot + " " + fat);

		System.out.println("DBDisplay.up -> about to update...");
		dbAdapter.updateFood(id, nm, ty, cals, ft, prot);
		System.out.println("DBDisplay.up ->updated...");
		dbAdapter.close();
		finish();
	}
    
	public void delDBEntryClick(View view) {
		
		System.out.println("DBDisplay.del -> Opening db...");
		dbAdapter.open();
		System.out.println("DBDisplay.del -> Opened db! Deleting user selected entry...");
		dbAdapter.deleteFood(id);
		dbAdapter.close();
		finish();
	}
}

//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.support.v4.app.NavUtils;
//
//public class EditFoodActivity extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_edit_food);
//		// Show the Up button in the action bar.
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_edit_food, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			// This ID represents the Home or Up button. In the case of this
//			// activity, the Up button is shown. Use NavUtils to allow users
//			// to navigate up one level in the application structure. For
//			// more details, see the Navigation pattern on Android Design:
//			//
//			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
//			//
//			NavUtils.navigateUpFromSameTask(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//}
