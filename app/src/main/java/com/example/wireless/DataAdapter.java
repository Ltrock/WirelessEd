package com.example.wireless;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.rd.utils.DensityUtils.dpToPx;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private List<DataModel> dataModelList;
    private Context context;

    public DataAdapter(List<DataModel> result) {
        this.dataModelList = result;
//        this.context = context;

    }

    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DataViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(final DataViewHolder dataViewHolder, final int i) {
        final DataModel dataModel = dataModelList.get(i);
        dataViewHolder.textTitle.setText(dataModel.title);
        Picasso.get().load(dataModel.photos).resize(dpToPx(80), dpToPx(80)).centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(dataViewHolder.imageView);
        dataViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = dataModel.file;
                Log.d(TAG, "filePath=" + filePath);
                Intent readActivity = new Intent(v.getContext(),Read.class);
                readActivity.putExtra("filePath",filePath);
                readActivity.putExtra("title",dataModel.title);
                v.getContext().startActivity(readActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imageView;
        public DataViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.thumbnail);
        }
    }
}
