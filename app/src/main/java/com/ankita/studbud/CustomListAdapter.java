package com.ankita.studbud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Courses> items; //data source of the list adapter

    //public constructor
    public CustomListAdapter(Context context, ArrayList<Courses> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, parent, false);
        }

        // get current item to be displayed
        Courses currentItem = (Courses) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.text_view_course_name);
        TextView textViewItemDescription = (TextView)
                convertView.findViewById(R.id.text_view_course_description);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getCourseName());
        textViewItemDescription.setText(currentItem.getCourseDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // returns the view for the current row
        return convertView;
    }
}
