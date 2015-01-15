package com.indianhangnam.Dbutils;

import java.util.ArrayList;
import com.indianhangman.Models.Category;
import com.indianhangman.Models.Question;
import com.indianhangman.Models.SubCategory;
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
    private String table_Category="Category";
    private String table_Subcategory="Subcategory";
    private String table_Question = "Question";
    private String queryGetAllCategories = "SELECT * FROM " + table_Category;
    private String queryGetSubcategoryByCategoryId = "SELECT * FROM "+table_Subcategory+" WHERE CategoryId=";
    private String queryGetQuestionBySubcatId = "SELECT * FROM "+table_Question+" WHERE SubCatId=";
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

    public ArrayList<SubCategory> getSubcatByCategory(int id)
    {
    	ArrayList<SubCategory> subcategories = new ArrayList<SubCategory>();
    	queryGetSubcategoryByCategoryId = queryGetSubcategoryByCategoryId +id;
    	try
		  {
			   cursor = mDb.rawQuery(queryGetSubcategoryByCategoryId, null);
			   if (cursor != null && cursor.moveToFirst()) {
			   do
	    	    {
	    	     SubCategory model = new SubCategory();
	    	     model.setId(cursor.getInt(0));
	    	     model.setName(cursor.getString(1));
	    	     model.setPhoto(cursor.getString(2));
	    	     model.setCatId(cursor.getInt(3));
	    	     subcategories.add(model);
	    	    } while (cursor.moveToNext());
	    	   }
	    	   return subcategories;
    	  } 
    	  catch (SQLiteException se) 
    	  {
    	   Log.v("DatabaseHandler get Subcategory Exception",
    	     Log.getStackTraceString(se));
    	  } 
    	  return subcategories;
    }

    public ArrayList<Question> getQuestionsBySubcat(int id)
    {
    	ArrayList<Question> questions = new ArrayList<Question>();
    	queryGetQuestionBySubcatId =queryGetQuestionBySubcatId + id;
    	try
    	{
    		cursor =mDb.rawQuery(queryGetQuestionBySubcatId, null);
    		if(cursor!= null && cursor.moveToFirst())
    		{
    			do
    			{
    			Question model = new Question();
    			model.setId(cursor.getInt(0));
    			model.setSubCatId(cursor.getInt(1));
    			model.setAnswer(cursor.getString(2));
    			model.setDifficultyLevel(cursor.getInt(3));
    			model.setHintType(cursor.getString(4));
    			model.setHint(cursor.getString(5));
                questions.add(model);
    			}while(cursor.moveToNext());   			
    		}
    	}
    	catch (SQLiteException e) {
    		Log.v("DatabaseHandler get Question Exception",
    	    Log.getStackTraceString(e));
		}
    	return questions;
    }
}
