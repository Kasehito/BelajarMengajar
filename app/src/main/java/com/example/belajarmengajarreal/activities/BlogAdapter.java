package com.example.belajarmengajarreal.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.belajarmengajarreal.R;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    private List<BlogItem> blogItems;
    private Context context;

    public BlogAdapter(Context context, List<BlogItem> blogItems) {
        this.context = context;
        this.blogItems = blogItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.IV_ItemBlog);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BlogItem blogItem = blogItems.get(position);
        Glide.with(context).load(blogItem.getImageResourceId()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Blogger.class);
                intent.putExtra("title", blogItem.getTitle());
                intent.putExtra("description", blogItem.getDescription());
                intent.putExtra("imageResourceId", blogItem.getImageResourceId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogItems.size();
    }
}
