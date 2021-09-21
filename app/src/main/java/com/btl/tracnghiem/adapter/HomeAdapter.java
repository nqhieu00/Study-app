package com.btl.tracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.btl.tracnghiem.MainActivity;
import com.btl.tracnghiem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context context;
    private List<String> tiles;
    private List<Integer> images;
    private RecyclerViewClickListener listener;

    public HomeAdapter(Context context, List<String> tiles, List<Integer> images,RecyclerViewClickListener listener) {
        this.context = context;
        this.tiles = tiles;
        this.images = images;
        this.listener=listener;

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.grid_item_home,parent,false);



        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position));
        holder.textView_subject.setText(tiles.get(position));

    }

    @Override
    public int getItemCount() {
        return tiles.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int Position);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView_subject;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_subject=(TextView)itemView.findViewById(R.id.textView_subject);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(imageView,getAdapterPosition());
        }
    }
}
