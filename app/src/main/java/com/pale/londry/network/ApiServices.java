package com.pale.londry.network;

import com.pale.londry.response.ResponseOrderan;
import com.pale.londry.response.Value;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiServices {

    //@TIPEMETHOD("API_END_POINT")
    @GET("tampil_order.php")
    Call<ResponseOrderan> request_show_all_orderan();
    // <ModelData> nama_method()

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("id") String id,
                       @Field("nama_customer") String nama_customer,
                       @Field("jenis_pakaian") String jenis_pakaian,
                       @Field("berat") String berat,
                        @Field("total_harga") String total_harga,
                       @Field("nama_kasir") String nama_kasir,
                       @Field("tgl_simpan") String tgl_simpan,
                       @Field("tgl_update") String tgl_update);

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody> simpanOrderan(@Field("id") String id,
                                     @Field("nama_customer") String nama_customer,
                                     @Field("jenis_pakaian") String jenis_pakaian,
                                     @Field("berat") String berat,
                                     @Field("total_harga") String total_harga,
                                     @Field("nama_kasir") String nama_kasir,
                                     @Field("tgl_simpan") String tgl_simpan,
                                     @Field("tgl_update") String tgl_update);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseBody> ubahOrderan(@Field("id") String id,
                                     @Field("nama_customer") String nama_customer,
                                     @Field("jenis_pakaian") String jenis_pakaian,
                                     @Field("berat") String berat,
                                     @Field("total_harga") String total_harga,
                                     @Field("nama_kasir") String nama_kasir,
                                     @Field("tgl_simpan") String tgl_simpan,
                                     @Field("tgl_update") String tgl_update);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseBody> hapusOrderan(@Field("id") String id);

}
