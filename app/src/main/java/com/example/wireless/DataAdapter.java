package com.example.wireless;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

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
        DataModel dataModel = dataModelList.get(i);
        dataViewHolder.textTitle.setText(dataModel.title);
        Picasso.get().load(dataModel.photos).resize(dpToPx(80), dpToPx(80)).centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(dataViewHolder.imageView);
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
