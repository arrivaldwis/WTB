package com.infan.wtb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infan.wtb.Model.BarangModel;
import com.infan.wtb.Model.BarangTypeModel;
import com.infan.wtb.Model.ClassificationResultModel;
import com.infan.wtb.Model.HardwareModel;
import com.infan.wtb.Model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arrival on 3/24/18.
 */

public class Classification {

    public ArrayList<BarangTypeModel> classificationProductByTag(final Context context, final BarangModel barang) {
        final ArrayList<ClassificationResultModel> classificationList = new ArrayList<>();
        final HashMap<String, Integer> sumArray = new HashMap<>();
        final ArrayList<BarangTypeModel> extractType = new ArrayList<>();
        final ArrayList<BarangTypeModel> outExtractType = new ArrayList<>();
        final String[] arrayType = {"designer", "student", "office", "gamer"};

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("hardware");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (barang.getKategori_id() == 1) {
                        for (DataSnapshot ds2 : ds.getChildren()) {
                            sumArray.clear();
                            App.student = 0;
                            App.designer = 0;
                            App.gamer = 0;
                            App.office = 0;
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                extractType.clear();
                                HardwareModel model = ds3.getValue(HardwareModel.class);
                                for (String s : arrayType) {
                                    int sum = 0;

                                    if (model.getTag().contains(s)) {
                                        if (ds2.getKey().equals("display")) {
                                            if (barang.getDisplay_id() == model.getId()) {
                                                sum++;
                                            }
                                        }
                                        if (ds2.getKey().equals("vga")) {
                                            if (barang.getVga_id() == model.getId()) {
                                                sum++;
                                            }
                                        }
                                        if (ds2.getKey().equals("memory")) {
                                            if (barang.getMemory_id() == model.getId()) {
                                                sum++;
                                            }
                                        }
                                        if (ds2.getKey().equals("processor")) {
                                            if (barang.getProcessor_id() == model.getId()) {
                                                sum++;
                                            }
                                        }

                                        if (s.equals("gamer"))
                                            App.gamer++;
                                        if (s.equals("student"))
                                            App.student++;
                                        if (s.equals("office"))
                                            App.office++;
                                        if (s.equals("designer"))
                                            App.designer++;
                                    }

                                    extractType.add(new BarangTypeModel(barang, s, sum));
                                }

                                for (BarangTypeModel b : extractType) {
                                    Log.d("WTB3", barang.getNama() + " [" + ds3.getKey() + ". " + model.getName() + " (" + b.getType() + "): " + b.getSum() + "]");
                                    if(b.getBarang().getNama().equals(barang.getNama())) {
                                        if (b.getType().equals("gamer"))
                                            App.gamer += b.getSum();
                                        if (b.getType().equals("designer"))
                                            App.designer += b.getSum();
                                        if (b.getType().equals("office"))
                                            App.office += b.getSum();
                                        if (b.getType().equals("student"))
                                            App.student += b.getSum();
                                    }
                                    sumArray.put(ds.getKey(), b.getSum());
                                }
                            }

                            int sum = 0;
                            for (String s:arrayType) {
                                //Toast.makeText(context, App.gamer+", "+App.designer+", "+App.office+", "+App.student, Toast.LENGTH_SHORT).show();

                                if(s.equals("gamer"))
                                    sum = App.gamer;
                                if(s.equals("designer"))
                                    sum = App.designer;
                                if(s.equals("office"))
                                    sum = App.office;
                                if(s.equals("student"))
                                    sum = App.student;

                                outExtractType.add(new BarangTypeModel(barang, s, sum));

                            }
                            //Toast.makeText(context, App.gamer+", "+App.designer+", "+App.office+", "+App.student, Toast.LENGTH_SHORT).show();
                            Log.d("WTB2", ds2.getKey());

                        }
                        //Toast.makeText(context, App.gamer+", "+App.designer+", "+App.office+", "+App.student, Toast.LENGTH_SHORT).show();
                        Log.d("WTB1", ds.getKey() + " - " + App.classificationResult.size());
                        //classificationList.add(new ClassificationResultModel(barang, sumArray));
                    }
                }
                Log.d("JumlahData", App.classificationResult.size() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (BarangTypeModel m:outExtractType) {
            Toast.makeText(context, m.getType()+" - "+m.getSum(), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(context, App.gamer+", "+App.designer+", "+App.office+", "+App.student, Toast.LENGTH_SHORT).show();
        return outExtractType;
    }
}