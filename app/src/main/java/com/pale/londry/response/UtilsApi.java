package com.pale.londry.response;

import com.pale.londry.network.ApiServices;
import com.pale.londry.network.RetrofitClient;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://192.168.100.209/api_londry/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(ApiServices.class);
    }
}
