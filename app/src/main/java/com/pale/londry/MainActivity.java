package com.pale.londry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pale.londry.network.ApiServices;
import com.pale.londry.network.InitRetrofit;
import com.pale.londry.response.OrderItem;
import com.pale.londry.response.ResponseOrderan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Deklarasi Widget
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();




    }






    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
//        // Siapkan request
//        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        Call<ResponseOrderan> orderanCall = api.request_show_all_orderan();
        // Kirim request
        orderanCall.enqueue(new Callback<ResponseOrderan>() {
            @Override
            public void onResponse(Call<ResponseOrderan> call, Response<ResponseOrderan> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<OrderItem> data_berita = response.body().getOrder();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status) {
                        // Buat Adapter untuk recycler view
                        AdapterOrderan adapter = new AdapterOrderan(MainActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(MainActivity.this, "Tidak ada Orderan saat Ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseOrderan> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }

    public void btTambah(View view) {

        Intent varIntent = new Intent(MainActivity.this, TambahData.class);
        startActivity(varIntent);
    }
}
