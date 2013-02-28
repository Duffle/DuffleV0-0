package com.example.duffle_0_0;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
     }

//============BUTTONS
	
	
	public void fitClick(View view){
		Intent i= new Intent(this,FitnessActivity.class);
		startActivity(i);
	}
	
	public void foodClick(View view){
		Intent i= new Intent(this,FoodActivity.class);
		startActivity(i);
	}
	
	public void calClick(View view){
		//Intent i= new Intent(this,CalendarActivity.class);
		//startActivity(i);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
