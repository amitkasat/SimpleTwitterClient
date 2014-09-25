package com.codepath.apps.simpletwitter.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
	
	private String body;
       private long uid;
       private String createdAt;
       private User user;
   	         
    public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}
	


	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
		
		for(int i =0; i<jsonArray.length(); i++)
		{
			JSONObject tweetJson = null;
			try
			{
				 tweetJson = jsonArray.getJSONObject(i);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
			
			Tweet tweet = Tweet.fromJSON(tweetJson);
			if(tweet != null)
			{
				tweets.add(tweet);
			}
		}
		
		return tweets;
	}
	
	//method capable for extracting these values from Json, which returns a tweet
    public static Tweet fromJSON(JSONObject jsonObject)
    {
 	   Tweet tweet = new Tweet();
 	   //Extract values from json to populate
 	   try
 	   {
 		   tweet.body = jsonObject.getString("text");
 		   tweet.uid = jsonObject.getLong("id");
 		   tweet.createdAt = jsonObject.getString("created_at");
 		  tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
 		   
 	   } catch (JSONException e)       
 	   {
 		   e.printStackTrace();
 		   return null;
 	   }
 	   return tweet;
    }

	@Override
	public String toString() {
		return getBody() + "-" + getUser().getScreenName() ;
		
	}
	
    }

