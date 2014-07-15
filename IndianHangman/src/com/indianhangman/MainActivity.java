package com.indianhangman;

import java.util.ArrayList;

import com.indianhangman.Models.Category;
import com.indianhangman.ViewAdapters.CategoryListAdaptor;
import com.indianhangnam.Dbutils.DatabaseAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	private DatabaseAdapter dbAdapter;
	ListView catListView;
	CategoryListAdaptor categoryAdaptor;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        dbAdapter = new DatabaseAdapter(this);
        catListView =(ListView)findViewById(R.id.catlist);

        ArrayList<Category> categories = dbAdapter.getAllCategories();
        categoryAdaptor = new CategoryListAdaptor(this, categories);
        catListView.setAdapter(categoryAdaptor);
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//            android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);

    }

    @Override
    protected void onResume() {
      dbAdapter.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
    	dbAdapter.close();
      super.onPause();
    }

}
