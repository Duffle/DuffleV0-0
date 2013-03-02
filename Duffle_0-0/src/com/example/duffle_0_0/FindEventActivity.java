package com.example.duffle_0_0;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import APIFiles.BaseListActivity;
import APIFiles.JsonParser;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class FindEventActivity extends BaseListActivity {

	private String[] names;
	 private String[] ids;
	 
	 ArrayList<HashMap<String, String>> mylist;
	 final Context context = this;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.category_view);
        
        //Bundle extras = getIntent().getExtras();
        new showLoader().execute();
       
	}
       
       @Override
   	public void initalize(){
    	   mylist = new ArrayList<HashMap<String, String>>();
       //String jsonString = JsonParser.getJsonString("http://www.miamibeachapi.com/rest/a.pi/businesses/search?page=1&rows=35");
    	   String jsonString = JsonParser.getJsonString
    			   ("http://www.miamibeachapi.com/rest/a.pi/events/search?date_filter=20130301-20140202&category_filter=606&page=1&rows=100");
    	   String jsonStringDance= JsonParser.getJsonString("http://www.miamibeachapi.com/rest/a.pi/events/search?date_filter=20130301-20140202&category_filter=598&page=1&rows=25");
    	if(!jsonString.equals("") && !jsonStringDance.equals("")){
       	connectionError = false;
	        try{
	        	JSONObject json = new JSONObject(jsonString);
	        	JSONArray entries = json.getJSONArray("events");
	        	//for dance query
	        	JSONObject jsonDance = new JSONObject(jsonStringDance);
	        	JSONArray entriesDance = jsonDance.getJSONArray("events");
	        	
	        	int totalLength=entries.length()+entriesDance.length();
	        	ids = new String[totalLength];
	        	names = new String[totalLength];
	        	
		        for(int i=0;i<totalLength;i++){						
					HashMap<String, String> map = new HashMap<String, String>();	
					HashMap<String, String> map2 = new HashMap<String, String>();
					JSONObject e = entries.getJSONObject(i);
					JSONObject f= entriesDance.getJSONObject(i);
					
					if(!e.isNull("name")){
						map.put("name", e.getString("name"));
						names[i] = e.getString("name");
						System.out.println("e string is="+names[i]);
						System.out.println("e string MAP is="+ map.toString());
						mylist.add(map);
					}
					
					if(!f.isNull("name")){
						map2.put("name", f.getString("name"));
						names[(totalLength/2)+i]= f.getString("name");
						System.out.println("f string is="+names[(totalLength/2)+i]);
						System.out.println("f string MAP is="+ map2.toString());
						mylist.add(map2);
					}
		    
		        				
				}		
	        }catch(JSONException e)        {
	        	 Log.e("log_tag", "Error parsing data "+e.toString());
	        }
       }
       else{
       	connectionError = true;
       }
    	
    	
    	
    	
       
      }
       @Override
   	public void initCallback(){
       
       ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.category_list, 
               new String[] {"name" }, 
               new int[] {R.id.item_title});
		setListAdapter(adapter);
		
		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);	
	
       
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
