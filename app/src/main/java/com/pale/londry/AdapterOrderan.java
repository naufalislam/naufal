package com.pale.londry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pale.londry.network.ApiServices;
import com.pale.londry.response.OrderItem;

import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class AdapterOrderan extends RecyclerView.Adapter<AdapterOrderan.MyViewHolder> {

//    @BindView()
//    EditText ubahid;
        @BindView(R.id.text_id_customer)
        EditText ubahid;

    ProgressDialog loading;

    ApiServices mApiService;
    Context mContext;

    // Buat Global variable untuk manampung context
    Context context;
    List<OrderItem> orderan;
    public AdapterOrderan(Context context, List<OrderItem> data_orderan) {
        // Inisialisasi
        this.context = context;
        this.orderan = data_orderan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.orderan_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Set widget
        holder.tvLabel.setText(orderan.get(position).getNamaCustomer());
        holder.tvNamaCustomer.setText(orderan.get(position).getNamaCustomer());
        holder.tvJenisPakaian.setText(orderan.get(position).getJenisPakaian());
        holder.tvBerat.setText(orderan.get(position).getBerat());
        holder.tvTotalHarga.setText(orderan.get(position).getTotalHarga());
        holder.tvNamaKasir.setText(orderan.get(position).getNamaKasir());
        holder.tvTglSimpan.setText(orderan.get(position).getTglSimpan());
        holder.tvTglUpdate.setText(orderan.get(position).getTglUpdate());


        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title dialog
                alertDialogBuilder.setTitle("Update Data");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Anda akan Update atau Hapus data ?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Update",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, maka akan menutup activity ini
                                //                pindah activity
                                Intent varIntent = new Intent(context, UpdateData.class);
                                //melampar value
                                varIntent.putExtra("id", orderan.get(position).getId());
                                varIntent.putExtra("nama_customer", orderan.get(position).getNamaCustomer());
                                varIntent.putExtra("jenis_pakaian", orderan.get(position).getJenisPakaian());
                                varIntent.putExtra("berat", orderan.get(position).getBerat());
                                varIntent.putExtra("total_harga", orderan.get(position).getTotalHarga());
                                varIntent.putExtra("nama_kasir", orderan.get(position).getNamaKasir());
                                varIntent.putExtra("tgl_simpan", orderan.get(position).getTglSimpan());
                                varIntent.putExtra("tgl_update", orderan.get(position).getTglUpdate());
//                               menyalakan activity yang baru
                                context.startActivity(varIntent);

                            }
                        })
                        .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
//                                Toast.makeText(context, "Berhasil Menghapus data", Toast.LENGTH_SHORT).show();
                                dialog.cancel();

                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();







            }
        });
    }


    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title dialog
        alertDialogBuilder.setTitle("Update Data");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda akan Update atau Hapus data ?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Update",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        Intent varIntent = new Intent(context, UpdateData.class);
                        context.startActivity(varIntent);

                    }
                })
                .setNegativeButton("Hapus",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        Toast.makeText(context, "Berhasil Menghapus data", Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }





    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return orderan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        TextView tvId,tvLabel,tvNamaCustomer,tvJenisPakaian,tvBerat,tvTotalHarga,tvNamaKasir,tvTglSimpan,tvTglUpdate;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            tvId = (TextView) itemView.findViewById(R.id.text_id_customer);
            tvLabel = (TextView) itemView.findViewById(R.id.label_nama_customer);
            tvNamaCustomer = (TextView) itemView.findViewById(R.id.nama_customer);
            tvJenisPakaian = (TextView) itemView.findViewById(R.id.jenis_pakaian);
            tvBerat = (TextView) itemView.findViewById(R.id.berat);
            tvTotalHarga = (TextView) itemView.findViewById(R.id.total_harga);
            tvNamaKasir = (TextView) itemView.findViewById(R.id.nama_kasir);
            tvTglSimpan = (TextView) itemView.findViewById(R.id.tgl_simpan);
            tvTglUpdate = (TextView) itemView.findViewById(R.id.tgl_update);
        }
    }
}
