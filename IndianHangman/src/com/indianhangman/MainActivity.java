package com.indianhangman;

import java.util.Iterator;
import java.util.List;

import com.indianhangman.Models.Category;
import com.indianhangnam.Dbutils.CategoryAdaptor;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	private CategoryAdaptor categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        categories = new CategoryAdaptor(this);
      //  categories.open();

        List<String> values = categories.getAllCategories();
        
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onResume() {
      categories.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      categories.close();
      super.onPause();
    }

}
