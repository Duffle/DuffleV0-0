package com.example.duffle_0_0;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.duffle_0_0.db_adapters.FitDBAdapter;
 
public class EditWorkoutActivity extends Activity {
	FitDBAdapter dbAdapter;
	long id;
	String n, c, t;
	EditText name, type, calories;
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_edit_workout);

 
        Intent i = getIntent();
        // getting attached intent data
        id= i.getLongExtra("id", -1);
        
        System.out.println("The id of the workout was "+id);
        
		// Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
		System.out.println("EditWorkout -> Creating the database...");
		dbAdapter = new FitDBAdapter(this.getBaseContext());
		System.out.println("EditWorkout -> Successfully created database!");
		System.out.println("EditWorkout -> " + dbAdapter.toString());
		
		name = (EditText) findViewById(R.id.nameText);
		calories = (EditText) findViewById(R.id.calsText);
		type = (EditText) findViewById(R.id.typeText);
		
		
		
		
		System.out.println("EditWorkout -> Opening db...");
		dbAdapter.open();
		System.out.println("EditWorkout -> Opened db! Pulling user selected entry...");
		//good above this
		Cursor cursor = dbAdapter.getFit(id);
		System.out.println("EditWorkout -> got cursor...");
		n=cursor.getString(1);
		name.setText(n);
		System.out.println("EditWorkout -> got name...:"+n);
		c=cursor.getString(3);
		calories.setText(c);
		System.out.println("EditWorkout -> got cals...:"+c);
		t=cursor.getString(2);
		type.setText(t);
		System.out.println("EditWorkout -> got type...:"+t);
		dbAdapter.close();
 
    }
    
	public void upDBEntryClick(View view) {
		System.out.println("DBDisplay.up -> Opening db...");
		dbAdapter.open();
		System.out.println("DBDisplay.up -> Opened db! Updating user selected entry...");
		
		System.out.println("DBDisplay.up -> getting new name...");
		n=name.getText().toString();
		System.out.println("DBDisplay.up -> getting new type...");
		t= type.getText().toString();
		System.out.println("DBDisplay.up -> getting new cals...");
		c= calories.getText().toString();
		System.out.println(n+" "+t+ " "+c);

		System.out.println("DBDisplay.up -> about to update...");
		dbAdapter.updateFit(id, n, t, c);
		System.out.println("DBDisplay.up ->updated...");
		dbAdapter.close();
		finish();
	}
    
	public void delDBEntryClick(View view) {
		
		System.out.println("DBDisplay.del -> Opening db...");
		dbAdapter.open();
		System.out.println("DBDisplay.del -> Opened db! Deleting user selected entry...");
		dbAdapter.deleteFit(id);
		dbAdapter.close();
		finish();
	}
}
