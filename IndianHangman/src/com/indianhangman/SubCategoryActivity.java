package com.indianhangman;
import java.util.ArrayList;

import com.indianhangman.Models.SubCategory;
import com.indianhangman.ViewAdapters.SubcategoryListAdaptor;
import com.indianhangnam.Dbutils.DatabaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SubCategoryActivity extends Activity implements OnItemClickListener{
	
	private DatabaseAdapter dbAdapter;
	ListView subcatListView;
	SubcategoryListAdaptor subcategoryAdaptor;
    ArrayList<SubCategory> subcategories ;
    
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Intent intent = getIntent();
		int catId = intent.getIntExtra("CategoryId", 1001);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        dbAdapter = new DatabaseAdapter(this);
        subcatListView =(ListView)findViewById(R.id.catlist);
        subcategories = dbAdapter.getSubcatByCategory(catId);
        subcategoryAdaptor = new SubcategoryListAdaptor(this, subcategories);
        subcatListView.setAdapter(subcategoryAdaptor);
        subcatListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("SubCategoryId", subcategories.get(position).getId());
        startActivity(intent);
		
	}

}
