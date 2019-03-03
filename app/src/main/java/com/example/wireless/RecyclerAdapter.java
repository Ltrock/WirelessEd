package com.example.wireless;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    List<CourseList> list;
    Context context;

    public RecyclerAdapter(List<CourseList> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_layout,parent,false);
        Holder Holders = new Holder(view);


        return Holders;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CourseList thelist = list.get(position);
        holder.name.setText(thelist.getChapName());
        holder.desc.setText(thelist.getDesc());

    }

    @Override
    public int getItemCount() {

        int a = 0;

        try{
            if(list.size()==0){

                a = 0;

            }
            else{

                a =list.size();
            }



        }catch (Exception e){


        }

        return a;

    }

    class Holder extends RecyclerView.ViewHolder{
        TextView name,desc;


        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            desc= (TextView) itemView.findViewById(R.id.desc);

        }
    }

}


