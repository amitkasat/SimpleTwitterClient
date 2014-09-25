package com.codepath.apps.simpletwitter;

import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.apps.simpletwitter.models.Tweet;
import com.codepath.apps.simpletwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Compose_Tweet extends Activity {

     private TwitterClient client;
     //private User cuser;
     private TextView userName;
     private TextView userScreenName;
   //  private String uname;
     private ImageView userImage;
     
     @Override
 	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.activity_compose__tweet);
 		//userName = (TextView)findViewById(R.id.userName);
 		//uname = userName.toString();
 		client = TwitterApplication.getRestClient();
 		client.postTweet(new JsonHttpResponseHandler()
 		{
 			@Override
 			public void onSuccess(JSONObject json) {
 				userName = (TextView)findViewById(R.id.userName);
 				userScreenName = (TextView)findViewById(R.id.userScreenName);
 				userImage = (ImageView) findViewById(R.id.userImage);
 				try {
					userName.setText(json.getString("name"));
					userScreenName.setText("@"+json.getString("screen_name"));
					userImage.setImageResource(android.R.color.transparent);
				    ImageLoader imageLoader = ImageLoader.getInstance();
				       // Populate the data into the template view using the data object
				    imageLoader.displayImage(json.getString("profile_image_url"), userImage);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 			}
 			@Override
 			public void onFailure(Throwable throwable, String s) {
                Log.d("debug", throwable.toString());
                Log.d("debug", s.toString());
 			}
 		});
 		
 		
 		//Intent data = getIntent();
		//User user = (User)data.getExtras().getSerializable("current_user");
	//	ImageView userImage = (ImageView)findViewById(R.id.userImage);
	//	ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), userImage);
		
		// Fill in the user's name and screen name.
	//	TextView userName = (TextView)findViewById(R.id.userName);
	//	TextView tvScreenName = (TextView)findViewById(R.id.tvScreenName);
	//	userName.setText(user.getName());
		//if (user.getScreenName() != "") {
		//	tvScreenName.setText("@" + user.getScreenName());
		//}
     }
 		//client = TwitterApplication.getRestClient();
 	//	Button button1 = (Button)findViewById(R.id.button1);
 	//	Button button2 = (Button)findViewById(R.id.button2);
 		
 	  // TextView userName = (TextView) findViewById(R.id.userName);
 	  //cuser = (User) getIntent().getExtras().getSerializable("user");
      
 	  // userName.setText(this.cuser.getScreenName());
	  // userImage = (ImageView)findViewById(R.id.userImage);
	// Populate the data into the template view using the data object
	  
      // userName.setText("@" + cuser.getScreenName());
     //  userImage.setImageResource(android.R.color.transparent);
     //  ImageLoader loader = ImageLoader.getInstance();
     //  loader.displayImage(cuser.getProfileImageUrl(), userImage);
           
	       
 	
 		
 	/*	button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
 		*/
 	//	button2.setOnClickListener(new OnClickListener() {
			
			
			
		
 	
     
 		
	       
    
     
   
     
     
     
     
  /*   public void Onclick(View v)
     {
    	 post();
     }
     
     public void post()
     {
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
 		String tweet = editText1.getText().toString();
 		client.postToTimeline(new JsonHttpResponseHandler(){
 			
 		public void onSuccess(JSONObject response) {
 			Tweet post = Tweet.fromJSON(response);
			Intent i = new Intent();
			// Pass result back to Timeline Activity class
			i.putExtra("postedTweet", post);
			setResult(RESULT_OK, i); 
			finish(); 
		}
 		
 		public void onFailure(Throwable e, String s) {
 			Log.d("debug", e.toString());
			Log.d("debug", s);
		}
		
	}, tweet);
     } */
   
     
   
         
     public void ONCancel(View v)
     {
  		
    	  finish();
    	
     }
	 
 	public void ONClick(View v)
 	{
 		
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		Intent i = new Intent();
		i.putExtra("tweet", editText1.getText().toString());
		// submit my result to parent activity
		setResult(RESULT_OK, i);
		finish();
		
 	}
	
}
