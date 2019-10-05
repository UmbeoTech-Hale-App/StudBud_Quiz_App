package com.ankita.studbud;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Contact_Peer_Admins extends Fragment  {
    ListView mylistview;
    int countimage;

    String[] peer_admin_names={"Naitik","Ankita","Pranav","Riya","Priya","Akshita","Vallabhi","Dishant","Khusang","Mohit"};
    String[] contact_peer_admin_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact__peer__admins,null);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mylistview=view.findViewById(R.id.contact_peer_admin_listview);

        //countimage=image.length;

        CustomAdapter customAdapter=new CustomAdapter();
        mylistview.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return countimage;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.custom_list_view,null);

            TextView nametextview=(TextView) view.findViewById(R.id.custom_name_list_view);
            TextView namephoneno=(TextView) view.findViewById(R.id.custom_list_view_phoneno);

            //ImageView contactadmins=(ImageView) view.findViewById(R.id.contactlogo);

            nametextview.setText(peer_admin_names[position]);

            namephoneno.setText(contact_peer_admin_mob[position]);

            // contactadmins.setImageResource(image[position]);
            return view;
        }
    }
}
