package com.example.duffle_0_0;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.duffle_0_0.db_adapters.FitDBAdapter;

public class ListWorkoutActivity extends ListActivity  {

	  private FitDBAdapter datasource; 
		
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
			datasource = new FitDBAdapter(this.getBaseContext());
		    datasource.open();
		    List<String> ls=new ArrayList<String>();
		    ls=datasource.getAllNames();
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, ls);
		    setListAdapter(adapter);
	        datasource.close();
		  }
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_workout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
