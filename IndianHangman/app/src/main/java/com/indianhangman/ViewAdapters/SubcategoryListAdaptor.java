package com.indianhangman.ViewAdapters;

import java.util.ArrayList;

import com.indianhangman.Models.SubCategory;
import com.indianhangman.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubcategoryListAdaptor extends BaseAdapter{

	Context context;
	
	ArrayList<SubCategory> subcategoryList;
	
	public SubcategoryListAdaptor(Context context ,ArrayList<SubCategory> list)
	{
		this.context = context;
		this.subcategoryList = list;
	}

	@Override
	public int getCount() {
		return subcategoryList.size();
	}

	@Override
	public Object getItem(int position) {
		return subcategoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return subcategoryList.get(position).getId();
	}
	
	private static class SubCategoryVeiwHolder
	{
		 ImageView image;
		 TextView name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SubCategoryVeiwHolder holder;
		LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertView==null)
		{
			convertView = inflator.inflate(R.layout.subcategory_item, null);
			holder = new SubCategoryVeiwHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.subcategoryPhoto);
			holder.name = (TextView)convertView.findViewById(R.id.subcategoryName);
			convertView.setTag(holder);
		}
		else
			holder = (SubCategoryVeiwHolder)convertView.getTag();
		
		if(subcategoryList.size()>0)
        {
			SubCategory subcat=null;
            subcat = (SubCategory) subcategoryList.get( position );             
           // holder.image.setImageResource(cat.getPhoto());( tempValues.getCompanyName() );
            holder.name.setText( subcat.getName());
//            holder.image.setImageResource(
//                          R.getIdentifier(
//                          "com.androidexample.customlistview:drawable/"+tempValues.getImage()
//                          ,null,null));
            //convertView.setOnClickListener(new OnItemClickListener( position ));
        }
		return convertView;
	}
		
}
