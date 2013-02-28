package com.example.duffle_0_0;

import com.duffle_0_0.classes.Workout;
import com.duffle_0_0.db_adapters.FitDBAdapter;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class AddWorkoutActivity extends Activity {

	private Button submit;
	private EditText name;
	private Spinner spinner1;
	private EditText calsField;
	public Workout w= new Workout();
	FitDBAdapter dbAdapter;

	@SuppressLint("NewApi")
	public void addListenerOnButton()
	{
		name= (EditText) findViewById(R.id.workout_name); 
		spinner1= (Spinner) findViewById(R.id.workout_spinner);
		calsField = (EditText) findViewById(R.id.cals_burned);
		submit= (Button) findViewById(R.id.btnSubmit);
		
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(AddWorkoutActivity.this,
						"OnClickListener : " + 
							    "\nName : "+ name.getText().toString() +
							    "\nCals Burned : "+ calsField.getText().toString() +
				                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) ,
							Toast.LENGTH_SHORT).show();
				
					String exerciseName=name.getText().toString();
					//determine the type
					String type=String.valueOf(spinner1.getSelectedItem());
					String cal= calsField.getText().toString();
					System.out.println(exerciseName+" "+type);
					
					w.setWorkout(exerciseName, type);
					System.out.println("Workout:"+w.getName()+" "+w.getType());
		
					
					Toast.makeText(AddWorkoutActivity.this,
							"Workout : " + 
								    "\nName : "+ w.getName() +
					                "\nType : "+ w.getType() ,
								Toast.LENGTH_SHORT).show();
				
					System.out.println("AddWorkoutActivity -> Opening DB...");
					dbAdapter.open();
					System.out.println("AddWorkoutActivity -> Opened DB successfully! Creating fit...");
					long tempID = dbAdapter.createFit(exerciseName,type,cal);
					System.out.println("AddWorkoutActivity -> ID = " + tempID + " Created fit successfully! Retrieving entry names...");
					
					dbAdapter.close();
					finish();
					
			}});
		

	}
	
	
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_workout);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		addListenerOnButton();	

		System.out.println("MainActivity -> Creating the database...");
		dbAdapter = new FitDBAdapter(this.getBaseContext());
		System.out.println("MainActivity -> Successfully created database!");
		System.out.println("MainActivity -> " + dbAdapter.toString());
	}
	

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_add_workout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
