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

public class CategoryAdaptor {
	protected static final String TAG = "CategoryAdapter";	
	private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private String table_Name="Category";

    public CategoryAdaptor(Context context) 
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
    
    public List<String> getAllCategories() {
    	  List<Category> record = new ArrayList<Category>();
    	  List<String> categoryNames = new ArrayList<String>();
    	  try {
		   String selectQuery = "SELECT * FROM " + table_Name;
		   Cursor cursor = mDb.rawQuery(selectQuery, null);
		   if (cursor != null && cursor.moveToFirst()) {
		   do
    	    {
    	     Category model = new Category();
    	     model.setId(cursor.getInt(0));
    	     model.setName(cursor.getString(1));
    	     record.add(model);
    	     categoryNames.add(model.getName());
    	    } while (cursor.moveToNext());
    	   }
    	   return categoryNames;
    	  } catch (SQLiteException se) {
    	   Log.v("DatabaseHandler getCategoryRecord Exception",
    	     Log.getStackTraceString(se));
    	  } catch (Exception e) {
    	   Log.v("DatabaseHandler getCategoryRecord Exception",
    	     Log.getStackTraceString(e));
    	  } finally {
    	   mDb.close();
    	  }
    	  return categoryNames;
    	 }
}
