package com.example.eventsmap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	Context myContext;
	 private static String DB_PATH = "/data/data/com.example.eventsmap/databases/";
	 
	 private static String DB_NAME = "EventsMapClient.db";
	
	public DatabaseHelper(Context context) {
		super( context, DB_NAME, null, 33 );
        myContext = context;
		// TODO Auto-generated constructor stub
	}
	
	private boolean databaseExists() {
        SQLiteDatabase checkDB = null;

        try { 
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase( myPath, null, SQLiteDatabase.OPEN_READONLY );
        }
        catch ( SQLiteException e ) {
            System.out.println( "database does not exist" );
        }

        return checkDB != null ? true : false;
    }
	
	public void createDatabase() throws IOException {
        boolean dbExists = databaseExists();

        if ( !dbExists ) {
            this.getReadableDatabase();

            try {
                copyDatabase();
            }
            catch ( IOException e ) {
                System.out.println( "copy database error" );
            }
        }
    }
	
	private void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME );

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream( outFileName );

        byte [] buffer = new byte[ 1024 ];
        int length;

        while( ( length = myInput.read( buffer ) ) > 0 )
            myOutput.write( buffer, 0, length );

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public SQLiteDatabase openDatabase() throws SQLException { 
        // Open the database
        String myPath = DB_PATH + DB_NAME;

        if ( databaseExists() )
            return SQLiteDatabase.openDatabase( myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS );

        return null;
    }
	
    public synchronized void close() {
        super.close();
    }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
