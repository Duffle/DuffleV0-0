package com.example.duffle_0_0;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.duffle_0_0.classes.Workout;
import com.duffle_0_0.db_adapters.FitDBAdapter;

public class ModifyWorkoutActivity extends ListActivity {

private FitDBAdapter datasource; 
		
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
			datasource = new FitDBAdapter(this.getBaseContext());
		    datasource.open();
		    final List<Workout> w=datasource.getAllWorkouts();
		    System.out.println("ModifyActivity ---> About to get all workouts");
		    //w=datasource.getAllWorkouts();
		    ArrayAdapter<Workout> adapter2 = new ArrayAdapter<Workout>(this,
			        android.R.layout.simple_list_item_1, w);
		    System.out.println("ModifyActivity ---> About to set adapter");
		    setListAdapter(adapter2);
	        datasource.close();

	        final ListView lv = getListView();

	        // listening to single list item on click
	        lv.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	 
	        	  System.out.println("ModifyActivity ---> about to get tag");

	        	  long workoutID= w.get(position).getID();
	        	  
	              // selected item
	              String product = ((TextView) view).getText().toString();
	 
	              // Launching new Activity on selecting single List Item
	              Intent i = new Intent(getApplicationContext(), EditWorkoutActivity.class);
	       
	              // sending data to new activity
	              i.putExtra("product", product);
	              System.out.println("ModifyActivity --> Giving the click it's id for the intent");
	              i.putExtra("id", workoutID);
	              startActivity(i);
	              finish(); 
	          }
	        });       
		  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_modify_workout, menu);
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