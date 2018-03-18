package com.infan.wtb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infan.wtb.Model.BarangModel;
import com.squareup.picasso.Picasso;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class DetailActivity extends AppCompatActivity {

    ImageView image1;
    TextView harga, text2, namabarang;
    Button btnBeli;
    MaterialRatingBar rating1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image1 = (ImageView) findViewById(R.id.image1);
        namabarang = (TextView) findViewById(R.id.namabarang);
        harga = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        btnBeli = (Button) findViewById(R.id.btnBeli);
        rating1 = (MaterialRatingBar) findViewById(R.id.rating1);
        rating1.setNumStars(5);
        rating1.setStepSize(.5f);

       if(getIntent().getExtras() != null){
           final String nama = getIntent().getStringExtra("nama");
           FirebaseDatabase database = FirebaseDatabase.getInstance();
           DatabaseReference refBarang = database.getReference("barang");
           final DatabaseReference refKategori = database.getReference("kategori");

           refBarang.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   // This method is called once with the initial value and again
                   // whenever data at this location is updated.
                   for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                       BarangModel value = ds.getValue(BarangModel.class);
                       if(value.getNama().equals(nama)){
                           String name = value.getNama();
                           String foto = value.getFoto();
                           String deskripsi = value.getDeskripsi();
                           float rating = value.getRating();
                           String price = value.getHarga();
                           int kategori_id = value.getKategori_id();
                           final String url = value.getUrl();
                           namabarang.setText(name);
                           harga.setText("Rp "+price);
                           text2.setText(deskripsi);
                           Picasso.with(DetailActivity.this).load(foto).into(image1);
                           rating1.setProgress((int) rating);
                           btnBeli.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   Intent intent = new Intent(Intent.ACTION_VIEW);
                                   intent.setData(Uri.parse(url));
                                   startActivity(intent);
                               }
                           });

                       }

                    /*refKategori.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                   }
               }

               @Override
               public void onCancelled(DatabaseError error) {
                   // Failed to read value
                   Log.w("", "Failed to read value.", error.toException());
               }
           });
       }

    }
}
