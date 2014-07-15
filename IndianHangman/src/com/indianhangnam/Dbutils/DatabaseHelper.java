package com.indianhangnam.Dbutils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String LOG = "DatabaseHelper";
	private static String DB_Path = "/data/data/com.indianhangnam/databases/";
	private static String DB_Name = "HangmanDb.db";
	private SQLiteDatabase hangmanDb;
	private final Context myContext;
	
	/**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
	public DatabaseHelper(Context context)
	{
		super(context, DB_Name, null, 1);
	    DB_Path = "/data/data/" + context.getPackageName() + "/databases/";
	    this.myContext = context;
	}

	/*
	 * Creates a empty database on the system and rewrites it with your own database.
	 */
	public void createDataBase()throws IOException {
		boolean dbExist = checkDatabase();
		if(!dbExist)
		{
			this.getReadableDatabase();
			try
			{
				copyDatabase();
			}catch(Exception e)
			{
				throw new Error("Error copying Database");
			}
		}
		
	}

	 /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
	private void copyDatabase()throws IOException {
		InputStream inputStream = myContext.getAssets().open(DB_Name);
		String outFileName =DB_Path+DB_Name;
		OutputStream outputStream = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while((length=inputStream.read(buffer))>0)
			outputStream.write(buffer,0,length);
		
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	private boolean checkDatabase() {
		SQLiteDatabase checkDb=null;
		try
		{
			String dbPath = DB_Path+DB_Name;
			checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch(SQLiteException e)
		{
			
		}
		if(checkDb!=null)
			checkDb.close();
		
		return checkDb!=null?true:false;
	}
	
	public SQLiteDatabase openDataBase() throws SQLException
	{
		String path = DB_Path+DB_Name;
		hangmanDb =SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		return hangmanDb;
	}
	
	@Override
	public synchronized void close()
	{
		if(hangmanDb != null)
			hangmanDb.close();
		super.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
}
