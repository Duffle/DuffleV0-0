package com.example.duffle_0_0;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.duffle_0_0.db_adapters.FoodDBAdapter;

public class AddFoodActivity extends Activity {

	FoodDBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food);
		System.out.println("AddFoodActivity -> Creating the database...");
		dbAdapter = new FoodDBAdapter(this.getBaseContext());
		System.out.println("AddFoodActivity -> Successfully created database!");
		System.out.println("AddFoodActivity -> " + dbAdapter.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_food, menu);
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

	public void addMealClick(View view) {
		final EditText nameField = (EditText) findViewById(R.id.foodName);
		final EditText calsField = (EditText) findViewById(R.id.calorieCount);
		final EditText proteinField = (EditText) findViewById(R.id.proteinCount);
		final EditText fatField = (EditText) findViewById(R.id.fatCount);
		final Spinner typeSpinner = (Spinner) findViewById(R.id.foodType);
		
		String name = nameField.getText().toString();
		String cals = calsField.getText().toString();
		String protein = proteinField.getText().toString();
		String fat = fatField.getText().toString();
		String type = typeSpinner.getSelectedItem().toString();
		
		System.out.println("AddFoodActivity -> Name = " + name);
		System.out.println("AddFoodActivity -> Cals = " + cals);
		System.out.println("AddFoodActivity -> Protein = " + protein);
		System.out.println("AddFoodActivity -> Fat = " + fat);
		System.out.println("AddFoodActivity -> Type = " + type);
		
		System.out.println("AddFoodActivity -> Opening DB...");
		dbAdapter.open();
		System.out.println("AddFoodActivity -> Opened DB successfully! Creating food...");
		long tempID = dbAdapter.createFood(name, type, cals, fat, protein);
		System.out.println("AddFoodActivity -> ID = " + tempID + " Created food successfully! Retrieving entry names...");
		
		System.out.println("AddFoodActivity -> Closing DB...");
		dbAdapter.close();
		finish();
	}
}