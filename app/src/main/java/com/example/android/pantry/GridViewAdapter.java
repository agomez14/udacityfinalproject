package com.example.android.pantry;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by barryjohnsonsmith on 8/3/15.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList();
    //private ArrayList<GridItem> gridData = new ArrayList<GridItem>();



   /* public GridViewAdapter(Context context, int layoutResourceId, ArrayList<GridItem> gridData) {
        super(context, layoutResourceId, gridData);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.gridData = gridData;
    }

*/


    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

//    public void setGridData(ArrayList<GridItem> gridData) {
  //      this.gridData = gridData;
    //    notifyDataSetChanged();
    //}

   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.title);
            holder.image= (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        GridItem item = gridData.get(position);
        holder.imageTitle.setText(Html.fromHtml(item.getTitle()));

        Picasso.with(context).load(item.getImage()).resize(150,150).into(holder.image);
        return row;
    }
*/
     @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        ImageItem item;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());
        return row;
    }
    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}