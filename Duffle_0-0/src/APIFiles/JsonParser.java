package APIFiles;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class JsonParser {

	public JsonParser() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getJsonString(String url){
		
		
		
		String response = "";
		Log.v("jsonURL",url);
		try {
			
			String OAUTH_CONSUMER_KEY  = "anonymous"; 
			String OAUTH_CONSUMER_SECRET  = "anonymous";
			String access_token = "9880ef5233c6ed1e3f87e475a751fb0bb3f4d828";
			String access_token_secret = "d4db36f986b04e47fd9f46d51a067c73766f0ddf";
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(OAUTH_CONSUMER_KEY,OAUTH_CONSUMER_SECRET); 
			consumer.setTokenWithSecret(access_token,access_token_secret);
			
		    HttpClient client = new DefaultHttpClient();  
		    String getURL = url;
		    HttpGet get = new HttpGet(getURL);
		    try{
		    	consumer.sign(get);
		    	Log.v("signed","signed");
		    }catch(Exception e){
		    	Log.v("signed","Not signed");
		    }
		    HttpResponse responseGet = client.execute(get);  
		    HttpEntity resEntityGet = responseGet.getEntity();  
		    if (resEntityGet != null) {  
		        // do something with the response
		        response = EntityUtils.toString(resEntityGet);
		        //Log.i("GET RESPONSE", response);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    Log.v("error",e.toString());
		}
		Log.v("response",response);
		return response;
		
	}

}
