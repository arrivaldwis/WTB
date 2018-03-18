package com.infan.wtb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infan.wtb.DetailActivity;
import com.infan.wtb.R;
import com.infan.wtb.Model.BarangModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Infan Diskamulya H on 12/31/2017.
 */

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    ArrayList<BarangModel> list;
    Context context;

    public BarangAdapter(ArrayList<BarangModel> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_barang, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama;
        private TextView tvDeskripsi;
        private ImageView imgFoto;
        private BarangModel data;
        private LinearLayout linearLayoutRow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            linearLayoutRow = (LinearLayout) itemView.findViewById(R.id.linearLayoutRow);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder (ViewHolder holder,int position) {
        final BarangModel data = list.get(position);
        holder.tvNama.setText(data.getNama());
        holder.tvDeskripsi.setText(data.getDeskripsi());
        try{
            Picasso.with(context).load(data.getFoto()).error(R.drawable.logo_wtb).into(holder.imgFoto);
        } catch(Exception e){

        }
        holder.linearLayoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("nama", data.getNama());
                context.startActivity(intent);
            }
        });
        //animate(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Insert a new item to the RecyclerView
    public void insert(int position, BarangModel data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
