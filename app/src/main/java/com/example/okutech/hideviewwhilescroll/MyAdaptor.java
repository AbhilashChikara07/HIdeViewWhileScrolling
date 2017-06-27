package com.example.okutech.hideviewwhilescroll;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 6/23/17
 */

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.HolderClass> {

    private Context context;
    private ArrayList<String> arrayList;

    public MyAdaptor(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public HolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_view_layout, parent, false);
        HolderClass holderClass = new HolderClass(view);
        return holderClass;
    }

    @Override
    public void onBindViewHolder(HolderClass holder, int position) {
        String text = arrayList.get(position);
        holder.textVew.setText(text);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HolderClass extends RecyclerView.ViewHolder {
        private TextView textVew;

        public HolderClass(View itemView) {
            super(itemView);
            textVew = (TextView) itemView.findViewById(R.id.textVew);
        }
    }
}
