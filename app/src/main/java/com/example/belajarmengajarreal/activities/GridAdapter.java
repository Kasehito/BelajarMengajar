package com.example.belajarmengajarreal.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.belajarmengajarreal.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private String[] titles;
    private int[] images;
    private LayoutInflater inflater;

    public GridAdapter(Context context, String[] titles, int[] images) {
        this.context = context;
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_materi, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.ivMateri);
        TextView textView = convertView.findViewById(R.id.tvMateri);

        imageView.setImageResource(images[position]);
        textView.setText(titles[position]);

        return convertView;
    }
}
