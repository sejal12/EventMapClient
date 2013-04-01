package com.example.eventsmap;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ExpandableListView;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class DisplayEvents extends Activity {
    private ExpandableListView mExpandableList;
    private SQLiteDatabase database; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);
        
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
 	 	
 	 	
 	 	System.out.println("Heeloo events");
 	 	List<Event> result =  getAllEvents();
 	 	
 	 	int count = result.size();
 	 	System.out.println("SIZE = " + count);
 	 	
 	 	//System.out.println("Event - title --- :  " + result.get(1).title);
 	 	
 	 	//result = getAllEvents();
       
 	 	//Object[] = result.toArray();
 	 	
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
 
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();

        
        //here we set the parents and the children
        for (int i = 0; i < count; i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent = new Parent();
            parent.setTitle("Event " + result.get(i).title);
            
            
           arrayChildren = new ArrayList<String>();
            
            arrayChildren.add("Location :  " + result.get(i).locationId);
            arrayChildren.add("Start Time : " + result.get(i).startTime);
            arrayChildren.add("End Time : " + result.get(i).endTime);
            arrayChildren.add("Details : " +result.get(i).content);
            
           /* for (int j = 0; j < 10; j++) {
                arrayChildren.add("Child " + j);
            }*/
            parent.setArrayChildren(arrayChildren);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(DisplayEvents.this,arrayParents));
 
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
		
}