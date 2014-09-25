package com.codepath.apps.simpletwitter;

import java.util.ArrayList;
import com.codepath.apps.simpletwitter.models.Tweet;
import org.json.JSONArray;
import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TimelineActivity extends Activity {
	private static final int REQUEST_CODE = 1;
	private PullToRefreshListView lvTweets;
	//long maxTweetId = Long.MAX_VALUE;

 private TwitterClient client;
 private ArrayList<Tweet> tweets;
 private ArrayAdapter<Tweet> aTweets;
 protected long curr_max_id;
// private ListView lvTweets;
protected long lastId;
private static long maxId = -1;
private static long sinceId = -1;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		lvTweets = (PullToRefreshListView) findViewById(R.id.lvTweets);
	    client = TwitterApplication.getRestClient();
	    tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(this,tweets);
	    lvTweets.setAdapter(aTweets);
	   // lvTweets = (ListView) findViewById(R.id.lvTweets);
	  
	    lvTweets.setOnRefreshListener( new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				aTweets.clear();
				populateTimeline("0");
			}
		});
		
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
		        customLoadMoreDataFromApi(page); 
		   
	                // or customLoadMoreDataFromApi(totalItemsCount);
	        }
	        });
		
		populateTimeline("0");
	}
	// Append more data into the adapter
    public void customLoadMoreDataFromApi(int page) {
      // This method probably sends out a network request and appends new data items to your adapter. 
      // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
      // Deserialize API response and then construct new objects to append to the adapter
    	//Tweet last_tweet=tweets.get(tweets.size()-1);
		//String lastId = String.valueOf(last_tweet.getUid());
        populateTimeline("0");
   } 
	
	public void populateTimeline(String max_id)
	{	
		
		client.getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			
			public void onSuccess(JSONArray json) {
				aTweets.addAll(Tweet.fromJSONArray(json));
				//aTweets.notifyDataSetChanged();
				//lvTweets.onRefreshComplete();
				//ArrayList<Tweet> allTweets = Tweet.fromJSONArray(json);
				//if(allTweets.size() > 0){
				//	aTweets.addAll(allTweets);
					//Get the id and set the current_max_id
					//Tweet last_tweet=tweets.get(tweets.size()-1);
					//lastId = last_tweet.getUid();
					//TimelineActivity.this.lastId = last_tweet.getUid();
					//}
				maxId = tweets.get(tweets.size() - 1).getUid() - 1;
				sinceId = tweets.get(0).getUid() - 1;
					lvTweets.onRefreshComplete();
				
			}
			
			@Override
			public void onFailure(Throwable e, String s) {
				// TODO Auto-generated method stub
				Log.d("debug", e.toString() );
				Log.d("debug", s.toString() );
			}
		},maxId, sinceId, 10);
	}
	
	public void onCompose(MenuItem menu)
	{
		Intent i = new Intent(this,Compose_Tweet.class);
		startActivityForResult(i, REQUEST_CODE);
				
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 /* if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
		     Tweet compose = (Tweet) i.getSerializableExtra("postedTweet");
		     aTweets.insert(compose, 0);
		     Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
		  }*/
		
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
		{
				String tweet = data.getStringExtra("tweet");
				client.postToTimeline(new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject json) {
						aTweets.clear();
						populateTimeline("0");
					}

					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("debug", e.toString());
						Log.d("debug", s.toString());
					}
			}, tweet);
	
		}
	} 
	
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.compose_item, menu);
		return true;
		}
}
