package com.pale.londry.response;

import com.google.gson.annotations.SerializedName;

public class OrderItem{

	@SerializedName("tgl_update")
	private String tglUpdate;

	@SerializedName("nama_customer")
	private String namaCustomer;

	@SerializedName("berat")
	private String berat;

	@SerializedName("jenis_pakaian")
	private String jenisPakaian;

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("nama_kasir")
	private String namaKasir;

	@SerializedName("id")
	private String id;

	@SerializedName("tgl_simpan")
	private String tglSimpan;

	public void setTglUpdate(String tglUpdate){
		this.tglUpdate = tglUpdate;
	}

	public String getTglUpdate(){
		return tglUpdate;
	}

	public void setNamaCustomer(String namaCustomer){
		this.namaCustomer = namaCustomer;
	}

	public String getNamaCustomer(){
		return namaCustomer;
	}

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setJenisPakaian(String jenisPakaian){
		this.jenisPakaian = jenisPakaian;
	}

	public String getJenisPakaian(){
		return jenisPakaian;
	}

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setNamaKasir(String namaKasir){
		this.namaKasir = namaKasir;
	}

	public String getNamaKasir(){
		return namaKasir;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTglSimpan(String tglSimpan){
		this.tglSimpan = tglSimpan;
	}

	public String getTglSimpan(){
		return tglSimpan;
	}

	@Override
 	public String toString(){
		return 
			"OrderItem{" + 
			"tgl_update = '" + tglUpdate + '\'' + 
			",nama_customer = '" + namaCustomer + '\'' + 
			",berat = '" + berat + '\'' + 
			",jenis_pakaian = '" + jenisPakaian + '\'' + 
			",total_harga = '" + totalHarga + '\'' +
			",nama_kasir = '" + namaKasir + '\'' + 
			",id = '" + id + '\'' + 
			",tgl_simpan = '" + tglSimpan + '\'' + 
			"}";
		}
}