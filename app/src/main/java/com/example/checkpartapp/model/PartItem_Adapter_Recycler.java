package com.example.checkpartapp.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartItem_Adapter_Recycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private List<PartItem> partItemList;

    public PartItem_Adapter_Recycler(List<PartItem> partItemList) {
        this.partItemList = partItemList;
    }
    public void updateData(List<PartItem> newData) {
        this.partItemList = newData;
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        // Return VIEW_TYPE_HEADER for the first row, and VIEW_TYPE_ITEM for other rows
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            //Inflate header layout for the first row
            View view = LayoutInflater.from(parent.getContext()).inflate(com.example.checkpartapp.R.layout.header_layout, parent, false);
            return new PartViewHolder.HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(com.example.checkpartapp.R.layout.part_item_layout, parent, false);
            return new PartViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PartViewHolder.HeaderViewHolder) {
            //Bind the header view(titles)
            PartViewHolder.HeaderViewHolder headerViewHolder = (PartViewHolder.HeaderViewHolder) holder;
            headerViewHolder.txtHeaderSiteId.setText("SITE_ID");
            headerViewHolder.txtHeaderUpnId.setText("UPN_ID");
            headerViewHolder.txtHeaderPartId.setText("PART_ID");
            Log.d("Adapter", "Binding position: " + position);

        } else if (holder instanceof PartViewHolder) {

            PartItem partItem = partItemList.get(position - 1);
            PartViewHolder itemViewHolder = (PartViewHolder) holder;
            itemViewHolder.siteIdTextView.setText(partItem.getSITE_ID());
            itemViewHolder.upnIdTextView.setText(partItem.getUPN_ID());
            itemViewHolder.partIdTextView.setText(partItem.getPART_ID());
            Log.d("Adapter", "Binding position: " + position);
        }

    }

    @Override
    public int getItemCount() {
        return partItemList.size() + 1;
    }

    public static class PartViewHolder extends RecyclerView.ViewHolder {
        TextView siteIdTextView, upnIdTextView, partIdTextView;

        public PartViewHolder(@NonNull View itemView) {
            super(itemView);
            siteIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtSiteID);
            upnIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtUpnID);
            partIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtPartID);
        }

        public static class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView txtHeaderSiteId, txtHeaderUpnId, txtHeaderPartId;

            public HeaderViewHolder(@NonNull View itemView) {
                super(itemView);
                txtHeaderSiteId = itemView.findViewById(com.example.checkpartapp.R.id.txtHeaderSiteId);
                txtHeaderUpnId = itemView.findViewById(com.example.checkpartapp.R.id.txtHeaderUpnId);
                txtHeaderPartId = itemView.findViewById(com.example.checkpartapp.R.id.txtHeaderPartId);

            }
        }
    }



}
