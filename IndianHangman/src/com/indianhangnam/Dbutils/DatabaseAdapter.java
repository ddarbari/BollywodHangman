package com.indianhangnam.Dbutils;

import java.util.ArrayList;
import java.util.List;

import com.indianhangman.Models.Category;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseAdapter {
	protected static final String TAG = "CategoryAdapter";	
	private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private String table_Name="Category";
    private String queryGetAllCategories = "SELECT * FROM " + table_Name;
    Cursor cursor;

    public DatabaseAdapter(Context context) 
    {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
        try
        {
        	mDbHelper.createDataBase();
        	mDb= mDbHelper.openDataBase();
        }
        catch(Exception e)
        {
        	
        }
    }
    
    public void open() throws SQLException {
    	mDb = mDbHelper.openDataBase();
      }
    
    public void close() {
        mDbHelper.close();
      }
    
    public ArrayList<Category> getAllCategories() {
    	ArrayList<Category> categories = new ArrayList<Category>();
    	  //List<String> categoryNames = new ArrayList<String>();
    	  try
    	  {
			   cursor = mDb.rawQuery(queryGetAllCategories, null);
			   if (cursor != null && cursor.moveToFirst()) {
			   do
	    	    {
	    	     Category model = new Category();
	    	     model.setId(cursor.getInt(0));
	    	     model.setName(cursor.getString(1));
	    	     model.setPhoto(cursor.getString(2));
	    	     categories.add(model);
	    	     //categoryNames.add(model.getName());
	    	    } while (cursor.moveToNext());
	    	   }
	    	   return categories;
    	  } 
    	  catch (SQLiteException se) 
    	  {
    	   Log.v("DatabaseHandler getCategoryRecord Exception",
    	     Log.getStackTraceString(se));
    	  } 
    	  return categories;
    	 }

    
}
