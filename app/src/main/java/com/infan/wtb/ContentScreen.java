package com.infan.wtb;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infan.wtb.Adapter.BarangAdapter;
import com.infan.wtb.Model.BarangModel;
import com.infan.wtb.Model.KategoriModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContentScreen extends AppCompatActivity {

    RecyclerView rvData;
    ArrayList<BarangModel> barangList;
    ProgressBar pbLoading;
    int id;
    ImageView image1;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_screen);
        prefs = getSharedPreferences("preference",MODE_PRIVATE);
        barangList = new ArrayList<>();
        rvData = (RecyclerView) findViewById(R.id.rvData);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        image1 = (ImageView) findViewById(R.id.image1);

        if(getIntent().getExtras() != null){
            id = getIntent().getIntExtra("kategori_id", 0);
        }

        LinearLayoutManager ll = new LinearLayoutManager(this);
        ll.setOrientation(LinearLayoutManager.VERTICAL);

        final BarangAdapter adapter = new BarangAdapter(barangList, this);

        rvData.setLayoutManager(ll);
        rvData.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refBarang = database.getReference("barang");
        final DatabaseReference refKategori = database.getReference("kategori");

        refBarang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                barangList.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    BarangModel value = ds.getValue(BarangModel.class);


                    if(value.getKategori_id() == id){
                        String nama = value.getNama();
                        String foto = value.getFoto();
                        String deskripsi = value.getDeskripsi();
                        int rating = value.getRating();
                        String harga = value.getHarga();
                        int kategori_id = value.getKategori_id();
                        String url = value.getUrl();
                        String tag = value.getTag();
                        Log.d("kategoriii",value.getKategori_id() + "");

                    refKategori.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                KategoriModel kategori = ds.getValue(KategoriModel.class);
                                if (kategori.getId() == id) {
                                    String imageurl = kategori.getImageurl();
                                    Picasso.with(ContentScreen.this).load(imageurl).into(image1);
                                    Log.d("kategoriii",kategori.getId() + "");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                        if(tag.contains(prefs.getString("nama_profil", ""))){
                            if(!(nama.isEmpty()||nama.equals(""))){
                                barangList.add(new BarangModel(deskripsi,foto,harga,kategori_id,nama,rating,url,tag));
                                adapter.notifyDataSetChanged();
                            }
                        }

                    }
                }
                pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
    }
}