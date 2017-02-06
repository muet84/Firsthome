package com.example.mibrahim.firsthome;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by M.Ibrahim on 1/23/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    ArrayList<String> list;
    ArrayList<String> listhoz;


    public  MyAdapter(Context context,ArrayList<String> list){
        this.context= context;
        this.list= list;

        listhoz = new ArrayList<>();
        for(int j =100; j<1000; j++){

            listhoz.add("This is Index" + j);

        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        RecyclerView recyclerViewhoz;




        public ViewHolder(View itemView) {

            super(itemView);



            recyclerViewhoz = (RecyclerView) itemView.findViewById(R.id.recycler_hoz);
            recyclerViewhoz.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
            recyclerViewhoz.setItemAnimator(new DefaultItemAnimator());
            recyclerViewhoz.setAdapter(new MyAdapterHOz(context,listhoz));

            textView = (TextView) itemView.findViewById(R.id.category_name);

        }
    }

}
