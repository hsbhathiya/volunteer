package com.example.volunteer;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class RegisterActivity extends AsyncTask<String , Void, String>{
	
	
	   private TextView statusField;
	   private Context context;	     
	   //flag 0 means get and 1 means post.(By default it is get.)
	   public RegisterActivity(Context context,TextView statusField) {
	      this.context = context;
	      this.statusField = statusField;
	      
	    
	   }	   
	   @Override
	protected void onPreExecute() {
	
	}

	@Override
	protected String doInBackground(String... formPara) {
		
		String data = "";
		String result ="";
	
	try{	
		 // create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // make POST request to the given URL
        String url= "http://175.157.7.19/letsvolunteer/web/app.php/registerAndroid";
        HttpPost httpPost = new HttpPost(url);
        
        
		//Read Data
		String userName  = (String)formPara[0];
		String email = (String)formPara[1]; // should validate
		String password = (String) formPara[2];
		String rePassword = (String) formPara[3];	
		
		String json ="";
		
	//	InputStream inputStream =null;	
		
		
		JSONObject jsonObject = new JSONObject();
		if(password !=null && password.equals(rePassword)){
			jsonObject.accumulate("status", "ok");
			jsonObject.accumulate("userName", userName);
			jsonObject.accumulate("password", password );
			jsonObject.accumulate("email", email);	
			
			json = jsonObject.toString();
					
			
		}else{
			jsonObject.accumulate("status", "Exception:Password Mismatch").toString();
		}
		//  set json to StringEntity
		StringEntity se = new StringEntity(json);
		
		// set httpPost Entity
		httpPost.setEntity(se);
		
        // Set some headers to inform server about the type of the content   
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        
       // Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);
        //Check the status code
        if(httpResponse.getStatusLine().getStatusCode() == 200){
        	result  = "Registration Success";
        }else{
            result = "Registration Error";
        }

       /* //  receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
            result = inputStream.toString(); //***Response should be decoded
        
        else{
            result = "Did not work!";
        }    */
    } catch (Exception e) {
        Log.d("InputStream", e.getLocalizedMessage());
        result = result + e.toString();
    }   
    return result;

	}
	
	@Override
	protected void onPostExecute(String result) {
		  //This may be a toast 		
	      this.statusField.setText(result);    
	}

}
