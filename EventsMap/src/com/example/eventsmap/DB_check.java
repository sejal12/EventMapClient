package com.example.eventsmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.ListActivity;
import android.view.View;


public class DB_check extends Activity {

	 SQLiteDatabase database; 
	 //public static ListView lv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db_check);
		
		Button b1 = (Button) findViewById(R.id.button1);
		
		DatabaseHelper myDbHelper = new DatabaseHelper(this);
       // myDbHelper = new DatabaseHelper(this);
		//System.out.println("HELOOO");
	    try {
	        	myDbHelper.createDatabase();
	 	} 
	    catch (IOException ioe) {
	 		throw new Error("Unable to create database");
	 	}
	 
	 	try {
	 		database = myDbHelper.openDatabase();
	 	}
	 	catch(SQLException sqle){
	 		throw sqle;
	 	}
	 	
	 	b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
            	Cursor c;
            	System.out.println("1");
        	 	c= database.rawQuery("Select * from Event", null);
        	 	//int count = c.getCount();
        	 	System.out.println("2");
        	 	List <Event> values = new ArrayList<Event>();
        	 	//int i = 0;
                
        	 	 for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        	         	
        	         	Event e = new Event();
        	         	e.categoryId = Integer.parseInt(c.getString(c.getColumnIndex("categoryId")));
        	         	System.out.println(e.categoryId);
        	         	e.content = c.getString(c.getColumnIndex("content"));
        	         	System.out.println(e.content);
        	         	e.title = c.getString(c.getColumnIndex("title"));
        	         	System.out.println(e.title);
        	         	e.status = c.getString(3);
        	         	System.out.println(e.status);
        	         
        	         	
        	         	/*try {
        						e.modifiedTime = new SimpleDateFormat(" YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(3));
        					} catch (ParseException e3) {
        						// TODO Auto-generated catch block
        						e3.printStackTrace();
        					}*/
        	         	
        	         	e.modifiedTime = c.getString(c.getColumnIndex("modifiedTime"));
        	         	System.out.println(e.modifiedTime);
        	         	e.locationId = Integer.parseInt(c.getString(c.getColumnIndex("locationId")));
        	         	
        	         	/*try {
        						e.endTime = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(5));
        					} catch (ParseException e2) {
        						// TODO Auto-generated catch block
        						e2.printStackTrace();
        					}*/
        	         	
        	         	e.endTime = c.getString(c.getColumnIndex("endTime"));
        	         	System.out.println(e.locationId);
        	         	System.out.println(e.endTime);
        	         	
        	         	/*try {
        						e.startTime = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(6));
        					} catch (ParseException e1) {
        						// TODO Auto-generated catch block
        						e1.printStackTrace();
        					}*/
        	         	
        	         	e.startTime = c.getString(c.getColumnIndex("startTime"));
        	         	System.out.println(e.startTime);
        	         	e.eventId = c.getInt(c.getColumnIndex("eventId"));
        	         	e.postedBy = c.getString(c.getColumnIndex("postedBy"));
        	         	//e.postedBy = c.getString(9);
        	         	System.out.println(e.eventId);
        	         	System.out.println(e.postedBy);
        	         	
        	         	values.add(e);
        	         	 //  i++;
        	         	}    
            }
        });
	}

	public List<Event> getAllEvents()
	{
		Cursor c;
    	System.out.println("1");
	 	c= database.rawQuery("Select * from Event", null);
	 	//int count = c.getCount();
	 	System.out.println("2");
	 	List <Event> values = new ArrayList<Event>();
	 	//int i = 0;
        
	 	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
         	
         	Event e = new Event();
         	e.categoryId = Integer.parseInt(c.getString(c.getColumnIndex("categoryId")));
         	System.out.println(e.categoryId);
         	e.content = c.getString(c.getColumnIndex("content"));
         	System.out.println(e.content);
         	e.title = c.getString(c.getColumnIndex("title"));
         	System.out.println(e.title);
         	e.status = c.getString(3);
         	System.out.println(e.status);
         
         	
         	/*try {
					e.modifiedTime = new SimpleDateFormat(" YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(3));
				} catch (ParseException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}*/
         	
         	e.modifiedTime = c.getString(c.getColumnIndex("modifiedTime"));
         	System.out.println(e.modifiedTime);
         	e.locationId = Integer.parseInt(c.getString(c.getColumnIndex("locationId")));
         	
         	/*try {
					e.endTime = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(5));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}*/
         	
         	e.endTime = c.getString(c.getColumnIndex("endTime"));
         	System.out.println(e.locationId);
         	System.out.println(e.endTime);
         	
         	/*try {
					e.startTime = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(c.getString(6));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
         	
         	e.startTime = c.getString(c.getColumnIndex("startTime"));
         	System.out.println(e.startTime);
         	e.eventId = c.getInt(c.getColumnIndex("eventId"));
         	e.postedBy = c.getString(c.getColumnIndex("postedBy"));
         	//e.postedBy = c.getString(9);
         	System.out.println(e.eventId);
         	System.out.println(e.postedBy);
         	
         	values.add(e);
         	 //  i++;
         	}    
        return values;
    }
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.db_check, menu);
		return true;
	}

}
