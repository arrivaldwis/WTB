package com.infan.wtb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.infan.wtb.App;
import com.infan.wtb.DetailActivity;
import com.infan.wtb.Model.BarangTypeModel;
import com.infan.wtb.R;
import com.infan.wtb.Model.BarangModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Infan Diskamulya H on 12/31/2017.
 */

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    ArrayList<BarangModel> list;
    ArrayList<ArrayList<BarangTypeModel>> barangCompareList;
    Context context;
    final String[] arrayType = {"designer", "student", "office", "gamer"};

    public BarangAdapter(ArrayList<BarangModel> list, Context context, ArrayList<ArrayList<BarangTypeModel>> barangCompare){
        this.list = list;
        this.context = context;
        this.barangCompareList = barangCompare;
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
        private LinearLayout llCompare;
        private Button btnRefresh;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            linearLayoutRow = (LinearLayout) itemView.findViewById(R.id.linearLayoutRow);
            llCompare = (LinearLayout) itemView.findViewById(R.id.llCompare);
            btnRefresh = (Button) itemView.findViewById(R.id.btnRefresh);

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

    int designer = 0;
    int student = 0;
    int gamer = 0;
    int office = 0;

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        final BarangModel data = list.get(position);

        designer = 0;
        student = 0;
        gamer = 0;
        office = 0;

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

        loadClassification(holder, data);
        holder.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadClassification(holder, data);
            }
        });

        //animate(holder);
    }

    private void loadClassification(ViewHolder holder, BarangModel data) {
        holder.llCompare.removeAllViewsInLayout();

        for (ArrayList<BarangTypeModel> x: barangCompareList) {
            for (BarangTypeModel b:x) {
                //Toast.makeText(context, b.getBarang().getNama() + " - "+b.getType()+": "+b.getSum(), Toast.LENGTH_SHORT).show();
                if(b.getBarang().getNama().equals(data.getNama())) {
                    if (b.type.equals("student"))
                        student = b.getSum();
                    if (b.type.equals("designer"))
                        designer = b.getSum();
                    if (b.type.equals("office"))
                        office = b.getSum();
                    if (b.type.equals("gamer"))
                        gamer = b.getSum();
                }
            }
        }

        for (String s:arrayType) {
            LinearLayout ll = new LinearLayout(context);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv = new TextView(context);
            int progress = 0;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    330,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0, 0, 12, 4);

            ProgressBar p = new ProgressBar(context, null,
                    android.R.attr.progressBarStyleHorizontal);
            p.setMax(6);
            p.setLayoutParams(params2);

            if(s.equals("student"))
                progress = student;
            if(s.equals("designer"))
                progress = designer;
            if(s.equals("office"))
                progress = office;
            if(s.equals("gamer"))
                progress = gamer;

            tv.setText(s.toUpperCase()+" ("+progress+")");
            tv.setLayoutParams(params);

            p.setProgress(progress);

            ll.addView(tv);
            ll.addView(p);
            holder.llCompare.addView(ll);
        }
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
