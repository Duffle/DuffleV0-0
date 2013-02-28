package com.example.duffle_0_0;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FitnessActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fitness);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fitness, menu);
		return true;
	}
	
//============BUTTONS
	
		public void addWorkoutClick(View view){
			Intent i= new Intent(this,AddWorkoutActivity.class);
			startActivity(i);
		}
		
		public void modWorkoutClick(View view){
			Intent i= new Intent(this,ModifyWorkoutActivity.class);
			startActivity(i);
		}
		
		public void searchWorkoutClick(View view){
			//Intent i= new Intent(this,FitnessActivity.class);
			//startActivity(i);
		}
		
		public void listWorkoutClick(View view){
			Intent i= new Intent(this,ListWorkoutActivity.class);
			startActivity(i);
		}

}

