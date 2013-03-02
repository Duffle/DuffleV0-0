package APIFiles;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;



public class BaseListActivity extends ListActivity{
	public boolean loginError = false;
	public boolean connectionError = false;
	protected Fragment mFrag;
	
	public class showLoader extends AsyncTask{
		ProgressDialog dialog;
       protected void onPreExecute (){
            dialog = ProgressDialog.show(BaseListActivity.this ,null,"loading");
       }

		
		public void hideLoad(){
			dialog.dismiss();
		}
		
		protected void onPostExecute(Object ob){
            dialog.dismiss();
            showConnectionError();
            showLoginError();
            initCallback();
       }


		@Override
		protected Object doInBackground(Object... params) {
			initalize();
			return new Object();
		}
	}
	
	public void showClass(int position){
		try {
				Class thisClass = Class.forName("com.solodev.medity.MainActivity");
				Intent intent = new Intent(this, thisClass);
			    startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	
	public void showConnectionError(){
		if(connectionError){
			final Builder alert = new AlertDialog.Builder(this)
		      .setMessage("Please Check internet Connection")
		      .setCancelable(true)
		      .setPositiveButton("OK",
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){}
		         });
	    	 alert.show();
		}
	}
	
	public void showLoginError(){
		if(loginError){
			final Builder alert = new AlertDialog.Builder(this)
		      .setMessage("Email/Password Mismatch")
		      .setCancelable(true)
		      .setPositiveButton("OK",
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){}
		         });
	    	 alert.show();
		}
	}
	

	public void initalize() {
		// TODO Auto-generated method stub
		
	}

	public void initCallback() {
		// TODO Auto-generated method stub
		
	}

}
