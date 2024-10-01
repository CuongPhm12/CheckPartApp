package com.example.checkpartapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.checkpartapp.R;

import java.util.List;

public class PartItemAdapter extends ArrayAdapter<PartItem> {
    public PartItemAdapter(Context context, List<PartItem> partItems) {
        super(context, 0, partItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PartItem partItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_part, parent, false);
        }

        // Lookup view for data population
        TextView tvSiteId = convertView.findViewById(R.id.tvSiteId);
        TextView tvUpnId = convertView.findViewById(R.id.tvUpnId);
        TextView tvPartId = convertView.findViewById(R.id.tvPartId);

        // Populate the data into the template view using the data object
        tvSiteId.setText(partItem.getSITE_ID());
        tvUpnId.setText(partItem.getUPN_ID());
        tvPartId.setText(partItem.getPART_ID());

        // Return the completed view to render on screen
        return convertView;
    }
}
