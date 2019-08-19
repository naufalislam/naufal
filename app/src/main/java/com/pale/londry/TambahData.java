package com.pale.londry;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pale.londry.network.ApiServices;
import com.pale.londry.response.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahData extends AppCompatActivity {

    private DatePickerDialog tglSimpan,tglUpdate;
    private SimpleDateFormat dateFormatter;
    private TextView tvTglSimpan,tvTglUpdate;
    private Button btTglSimpan,btTglUpdate;

    @BindView(R.id.tambah_id)
    EditText tambahid;
    @BindView(R.id.tambah_nama_customer)
    EditText tambahNamaCustomer;
    @BindView(R.id.tambah_jenis_pakaian)
    EditText tambahJenisPakaian;
    @BindView(R.id.tambah_berat)
    EditText tambahBerat;
    @BindView(R.id.tambah_total_harga)
    EditText tambahTotalHarga;
    @BindView(R.id.tambah_nama_kasir)
    EditText tambahNamaKasir;
    @BindView(R.id.tv_tambah_tgl_simpan)
    TextView tambahTanggalSimpan;
    @BindView(R.id.tv_tambah_tgl_update)
    TextView tambahTanggalUpdate;
    @BindView(R.id.buttonTambah)
    Button btTambahOrderan;

    ProgressDialog loading;


    ApiServices mApiService;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        tanggal();
        
        btTambahOrderan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestTambahOrderan();
            }


        });


    }

    private void tanggal() {

        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tvTglSimpan = (TextView) findViewById(R.id.tv_tambah_tgl_simpan);
        btTglSimpan = (Button) findViewById(R.id.bt_tambah_tgl_simpan);
        tvTglUpdate = (TextView) findViewById(R.id.tv_tambah_tgl_update);
        btTglUpdate = (Button) findViewById(R.id.bt_tambah_tgl_update);

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

    private void tanggalSimpan(){
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
                tvTglSimpan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        tglSimpan.show();
    }

    private void tanggalUpdate(){
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
                tvTglUpdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        tglUpdate.show();
    }


    private void requestTambahOrderan() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.simpanOrderan(tambahid.getText().toString(),
                tambahNamaCustomer.getText().toString(),
                tambahJenisPakaian.getText().toString(),
                tambahBerat.getText().toString(),
                tambahTotalHarga.getText().toString(),
                tambahNamaKasir.getText().toString(),
                tambahTanggalSimpan.getText().toString(),
                tambahTanggalUpdate.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext, "Orderan Berhasil diTambahkan", Toast.LENGTH_LONG).show();
                            Intent varIntent = new Intent(TambahData.this, MainActivity.class);
                            startActivity(varIntent);
                        }else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Menyimpan Orderan", Toast.LENGTH_LONG).show();
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
