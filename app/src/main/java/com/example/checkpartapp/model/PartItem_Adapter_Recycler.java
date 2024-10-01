package com.example.checkpartapp.model;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkpartapp.R;

import java.util.List;

public class PartItem_Adapter_Recycler extends RecyclerView.Adapter<PartItem_Adapter_Recycler.PartViewHolder> {
    private List<PartItem> partItemList;

    public PartItem_Adapter_Recycler(List<PartItem> partItemList){
        this.partItemList = partItemList;
    }
    @NonNull
    @Override
    public PartItem_Adapter_Recycler.PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.checkpartapp.R.layout.part_item_layout, parent, false);
        return new PartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartItem_Adapter_Recycler.PartViewHolder holder, int position) {
PartItem partItem = partItemList.get(position);
holder.siteIdTextView.setText(partItem.getSITE_ID());
holder.upnIdTextView.setText(partItem.getUPN_ID());
holder.partIdTextView.setText(partItem.getPART_ID());
    }

    @Override
    public int getItemCount() {
        return partItemList.size();
    }

    public static class PartViewHolder extends RecyclerView.ViewHolder {
        TextView siteIdTextView, upnIdTextView, partIdTextView;
        public PartViewHolder(@NonNull View itemView){
            super(itemView);
            siteIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtSiteID);
            upnIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtUpnID);
            partIdTextView = itemView.findViewById(com.example.checkpartapp.R.id.txtPartID);
    }
}


}
