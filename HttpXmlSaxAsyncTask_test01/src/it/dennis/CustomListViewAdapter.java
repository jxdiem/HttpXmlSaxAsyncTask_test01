package it.dennis;


import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// import it.dennis.activities.R;
 
public class CustomListViewAdapter extends ArrayAdapter<Laptop> {
    Activity context;
    List<Laptop> laptops;
 
    public CustomListViewAdapter(Activity context, List<Laptop> laptops) {
        super(context, R.layout.list_item, laptops);
        this.context = context;
        this.laptops = laptops;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtBrand;
        TextView txtModel;
        TextView txtPrice;
    }
 
    public Laptop getItem(int position) {
        return laptops.get(position);
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtBrand = (TextView) convertView.findViewById(R.id.brand);
            holder.txtModel = (TextView) convertView.findViewById(R.id.model);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        Laptop laptop = (Laptop) getItem(position);
 
        holder.txtBrand.setText(laptop.getBrand());
        holder.txtModel.setText(laptop.getModel());
        holder.imageView.setImageBitmap(laptop.getImageBitmap());
        holder.txtPrice.setText(laptop.getPrice() + "");
 
        return convertView;
    }
}
 
/* USING BaseAdapter */
/*public class CustomListViewAdapter extends BaseAdapter {
 
    Activity context;
    List<Laptop> laptops;
 
    public CustomListViewAdapter(Activity context, List<Laptop> laptops) {
        super();
        this.context = context;
        this.laptops = laptops;
    }
 
    private class ViewHolder {
        ImageView imageView;
        TextView txtBrand;
        TextView txtModel;
        TextView txtPrice;
    }
 
    public int getCount() {
        return laptops.size();
    }
 
    public Object getItem(int position) {
        return laptops.get(position);
    }
 
    public long getItemId(int position) {
        return 0;//employees.indexOf(getItem(position));
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtBrand = (TextView) convertView.findViewById(R.id.brand);
            holder.txtModel = (TextView) convertView.findViewById(R.id.model);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        Laptop laptop = (Laptop) getItem(position);
 
        holder.txtBrand.setText(laptop.getBrand());
        holder.txtModel.setText(laptop.getModel());
        holder.imageView.setImageBitmap(laptop.getImageBitmap());
        holder.txtPrice.setText(laptop.getPrice() + "");
 
        return convertView;
    }
}*/
