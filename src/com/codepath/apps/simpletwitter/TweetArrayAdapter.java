package com.codepath.apps.simpletwitter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.net.ParseException;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.simpletwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;


public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetArrayAdapter(Context context, List<Tweet> tweets)
	{
		super(context,0,tweets);
	}
	
	 public String getRelativeTimeAgo(String rawJsonDate) throws java.text.ParseException {
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
		sf.setLenient(true);
		String relativeDate = "";
		try {
		long dateMillis = sf.parse(rawJsonDate).getTime();
		relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
		System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
		} catch (ParseException e) {
		e.printStackTrace();
		}
		 
		return relativeDate;
		} 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		 Tweet tweet = getItem(position);    
		 //find or inflate the template
		 View v;
	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	    	   LayoutInflater inflator = LayoutInflater.from(getContext());
	          v = inflator.inflate(R.layout.tweet_item, parent,false);
	       } else{
	    	   v = convertView;
	       }
	       // Find the views within template
	       ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
	       TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
	       TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
	       TextView timeLine = (TextView) v.findViewById(R.id.timeLine);
	       TextView userId = (TextView) v.findViewById(R.id.userId);
	       ivProfileImage.setImageResource(android.R.color.transparent);
	       ImageLoader imageLoader = ImageLoader.getInstance();
	       // Populate the data into the template view using the data object
	       imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
	       
	       tvUserName.setText(tweet.getUser().getScreenName());
	       tvBody.setText(tweet.getBody());
	       userId.setText("@" + tweet.getUser().getScreenName());
		   try {
			timeLine.setText(getRelativeTimeAgo(tweet.getCreatedAt()));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	       // Return the completed view to render on screen
	       return v;
	   }
	
	

	
	}

