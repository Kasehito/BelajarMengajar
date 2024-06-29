package com.example.belajarmengajarreal.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.models.Materi;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private String[] titles;
    private int[] images;
    private List<Materi> materiList;
    private LayoutInflater inflater;

//    public GridAdapter(Context context, String[] titles, int[] images) {
//        this.context = context;
//        this.titles = titles;
//        this.images = images;
//        this.inflater = LayoutInflater.from(context);
//    }

    public GridAdapter(Context context, List<Materi> materiList) {
        this.context = context;
        this.titles = new String[materiList.size()];
        this.images = new int[materiList.size()];
        this.inflater = LayoutInflater.from(context);
        this.materiList = materiList;
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

        if (!materiList.isEmpty()) {
            // use list from List<Materi>

            Materi materi = this.materiList.get(position);
            ImageView imageView = convertView.findViewById(R.id.ivMateri);
            TextView textView = convertView.findViewById(R.id.tvMateri);

            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MateriActivity.class);
                intent.putExtra("materi_id", materi.getId());
                context.startActivity(intent);
            });

            Glide.with(convertView.getContext())
                    .load(materi.getThumbnail())
                    .into(imageView);
            textView.setText(materi.getJudul());
        }

//        ImageView imageView = convertView.findViewById(R.id.ivMateri);
//        TextView textView = convertView.findViewById(R.id.tvMateri);
//
//        imageView.setImageResource(images[position]);
//        textView.setText(titles[position]);

        return convertView;
    }
}
