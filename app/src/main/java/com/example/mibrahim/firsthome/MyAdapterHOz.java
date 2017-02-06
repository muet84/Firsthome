package com.example.mibrahim.firsthome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by M.Ibrahim on 1/23/2017.
 */
public class MyAdapterHOz extends RecyclerView.Adapter<MyAdapterHOz.ViewHolder> {

    Context context;
    ArrayList<String> listHOz;


    public MyAdapterHOz(Context context, ArrayList<String> list){
        this.context= context;
        this.listHOz = list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(context).inflate(R.layout.custom_hoz,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(listHOz.get(position));
    }

    @Override
    public int getItemCount() {
        return listHOz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.category_name_hoz);

        }
    }

}
