package com.smartFarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    Context context;
    ArrayList<list_item> list_itemArrayList;

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }
    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;
    TextView content_textView;
    ImageView profile_imageView;


    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_board,null);
            nickname_textView = (TextView)convertView.findViewById(R.id.nickname_textview);
            content_textView = (TextView)convertView.findViewById(R.id.content_textview);
            date_textView = (TextView)convertView.findViewById(R.id.date_textview);
            title_textView  =(TextView)convertView.findViewById(R.id.title_textview);
            profile_imageView = (ImageView)convertView.findViewById(R.id.profile_imageview);
        }
        nickname_textView.setText(list_itemArrayList.get(position).getNickname());
        title_textView.setText(list_itemArrayList.get(position).getTitle());
        content_textView.setText(list_itemArrayList.get(position).getContent());
        date_textView.setText(list_itemArrayList.get(position).getWrite_date().toString());

        Glide.with(context).load(list_itemArrayList.get(position).getProfile_image()).into(profile_imageView);

        return convertView;
    }
}
