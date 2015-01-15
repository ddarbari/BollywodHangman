package com.indianhangman.ViewAdapters;

import java.util.ArrayList;

import com.indianhangman.Models.Category;
import com.indianhangman.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryListAdaptor extends BaseAdapter{

	Context context;
	ArrayList<Category> categoryList;
	
	public CategoryListAdaptor(Context context ,ArrayList<Category> list)
	{
		this.context = context;
		this.categoryList = list;
	}
	@Override
	public int getCount() {
		return categoryList.size();
	}

	@Override
	public Object getItem(int position) {
		return categoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return categoryList.get(position).getId();
	}
	
	private static class CategoryVeiwHolder
	{
		 ImageView image;
		 TextView name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CategoryVeiwHolder holder;
		LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertView==null)
		{
			convertView = inflator.inflate(R.layout.category_item, null);
			holder = new CategoryVeiwHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.categoryPhoto);
			holder.name = (TextView)convertView.findViewById(R.id.categoryName);
			convertView.setTag(holder);
		}
		else
			holder = (CategoryVeiwHolder)convertView.getTag();
		
		if(categoryList.size()>0)
        {
			Category cat=null;
            cat = ( Category ) categoryList.get( position );             
           // holder.image.setImageResource(cat.getPhoto());( tempValues.getCompanyName() );
            holder.name.setText( cat.getName());
//            holder.image.setImageResource(
//                          R.getIdentifier(
//                          "com.androidexample.customlistview:drawable/"+tempValues.getImage()
//                          ,null,null));
            //convertView.setOnClickListener(new OnItemClickListener( position ));
        }
		return convertView;
	}
		
}
