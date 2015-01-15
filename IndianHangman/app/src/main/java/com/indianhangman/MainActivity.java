package com.indianhangman;

import java.util.ArrayList;

import com.indianhangman.Models.Category;
import com.indianhangman.ViewAdapters.CategoryListAdaptor;
import com.indianhangnam.Dbutils.DatabaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private DatabaseAdapter dbAdapter;
	ListView catListView;
	CategoryListAdaptor categoryAdaptor;
    ArrayList<Category> categories ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        dbAdapter = new DatabaseAdapter(this);
        catListView =(ListView)findViewById(R.id.catlist);
        categories = dbAdapter.getAllCategories();
        categoryAdaptor = new CategoryListAdaptor(this, categories);
        catListView.setAdapter(categoryAdaptor);
        catListView.setOnItemClickListener(this);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    {
        Intent intent = new Intent(this,SubCategoryActivity.class);
        intent.putExtra("CategoryId", categories.get(position).getId());
        startActivity(intent);
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
