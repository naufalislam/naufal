package com.pale.londry;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pale.londry.network.ApiServices;
import com.pale.londry.response.OrderItem;
import com.pale.londry.response.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateData extends AppCompatActivity {

    // Buat Global variable untuk manampung context
    Context context;
    List<OrderItem> orderan;


    private DatePickerDialog tglSimpan,tglUpdate;
    private SimpleDateFormat dateFormatter;
    private TextView tvTglSimpan,tvTglUpdate;
    private Button btTglSimpan,btTglUpdate,btUbah;

    @BindView(R.id.ubah_id)
    EditText ubahid;
    @BindView(R.id.ubah_nama_customer)
    EditText ubahNamaCustomer;
    @BindView(R.id.ubah_jenis_pakaian)
    EditText ubahJenisPakaian;
    @BindView(R.id.ubah_berat)
    EditText ubahBerat;
    @BindView(R.id.ubah_total_harga)
    EditText ubahTotalHarga;
    @BindView(R.id.ubah_nama_kasir)
    EditText ubahNamaKasir;
    @BindView(R.id.tv_ubah_tgl_simpan)
    TextView ubahTanggalSimpan;
    @BindView(R.id.tv_ubah_tgl_update)
    TextView ubahTanggalUpdate;
    @BindView(R.id.buttonUbah)
    Button btUbahOrderan;
    @BindView(R.id.button_delete)
    ImageButton btHapusOrderan;

    ProgressDialog loading;

    ApiServices mApiService;
    Context mContext;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

       tanggal();


       btUbahOrderan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               requestUbahOrderan();
           }
       });

        btHapusOrderan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestdeleteOrderan();
            }
        });


        setTex();


    }

//menangkapa value
    private void setTex(){
        String id = getIntent().getStringExtra("id");
        String nama_customer = getIntent().getStringExtra("nama_customer");
        String jenis_pakaian = getIntent().getStringExtra("jenis_pakaian");
        String berat = getIntent().getStringExtra("berat");
        String total_harga = getIntent().getStringExtra("total_harga");
        String nama_kasir = getIntent().getStringExtra("nama_kasir");
        String tgl_simpan = getIntent().getStringExtra("tgl_simpan");
        String tgl_update = getIntent().getStringExtra("tgl_update");


        ubahid.setText(id);
        ubahNamaCustomer.setText(nama_customer);
        ubahJenisPakaian.setText(jenis_pakaian);
        ubahBerat.setText(berat);
        ubahTotalHarga.setText(total_harga);
        ubahNamaKasir.setText(nama_kasir);
        ubahTanggalSimpan.setText(tgl_simpan);
        ubahTanggalUpdate.setText(tgl_update);

    }









    private void tanggal(){

        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvTglSimpan = (TextView) findViewById(R.id.tv_ubah_tgl_simpan);
        btTglSimpan = (Button) findViewById(R.id.bt_ubah_tgl_simpan);
        tvTglUpdate = (TextView) findViewById(R.id.tv_ubah_tgl_update);
        btTglUpdate = (Button) findViewById(R.id.bt_ubah_tgl_update);

        btTglSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanggalSimpan();

            }


        });

        btTglUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanggalUpdate();

            }


        });
    }

    private void tanggalSimpan() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        tglSimpan = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvTglSimpan.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        tglSimpan.show();
    }


    private void tanggalUpdate() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        tglUpdate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {



            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvTglUpdate.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        tglUpdate.show();
    }


    private void requestUbahOrderan(){

        loading = ProgressDialog.show(mContext,null,"Harap Tunggu...", true,false);

        mApiService.ubahOrderan(ubahid.getText().toString(),
                ubahNamaCustomer.getText().toString(),
                ubahJenisPakaian.getText().toString(),
                ubahBerat.getText().toString(),
                ubahTotalHarga.getText().toString(),
                ubahNamaKasir.getText().toString(),
                ubahTanggalSimpan.getText().toString(),
                ubahTanggalUpdate.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext,"Orderan Berhasil di Update", Toast.LENGTH_LONG).show();
                            Intent varIntent = new Intent(UpdateData.this, MainActivity.class);
                            startActivity(varIntent);
                        }else {
                            loading.dismiss();
                            Toast.makeText(mContext,"Gagal mengupdate Orderan", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext,"Koneksi Internet Bermasalah", Toast.LENGTH_LONG).show();

                    }
                });

    }

    //api delete
    private void requestdeleteOrderan(){

        loading = ProgressDialog.show(mContext,null,"Harap Tunggu...", true,false);

        mApiService.hapusOrderan(ubahid.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext,"Orderan Berhasil di Hapus", Toast.LENGTH_LONG).show();
                            Intent varIntent = new Intent(UpdateData.this, MainActivity.class);
                            startActivity(varIntent);

                        }else {
                            loading.dismiss();
                            Toast.makeText(mContext,"Gagal menghapus Orderan", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext,"Koneksi Internet Bermasalah", Toast.LENGTH_LONG).show();

                    }
                });

    }










}

