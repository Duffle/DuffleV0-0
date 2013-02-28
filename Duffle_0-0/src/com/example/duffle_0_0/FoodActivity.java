package com.example.duffle_0_0;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class FoodActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_food, menu);
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

	//============BUTTONS
	
	
			public void addFoodClick(View view){
				Intent i= new Intent(this,AddFoodActivity.class);
				startActivity(i);
			}
			
			public void modFoodClick(View view){
				Intent i= new Intent(this,ModifyFoodActivity.class);
				startActivity(i);
			}
			
			public void searchFoodClick(View view){
				//Intent i= new Intent(this,searchFoodActivity.class);
				//startActivity(i);
			}
			
			public void listFoodClick(View view){
				Intent i= new Intent(this,ListFoodActivity.class);
				startActivity(i);
			}
	
}
