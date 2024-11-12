package com.example.checkpartapp.api;

import com.example.checkpartapp.model.ApiResponse;
import com.example.checkpartapp.model.GetPartByUpn_Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    //http://172.28.10.17:5003/Umes/GetPart?floor=1&upn_id=NQ8N4GG
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://172.28.10.17:5003/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("Umes/GetPart")
//    Call<List<PartItem>> getPartItemList(@Query("floor") int floor,
//                                         @Query("upn_id") String upn_id);

    Call<ApiResponse> getPartItemList(@Query("floor") int floor,
                                      @Query("upn_id") String upn_id);

//    http://172.28.10.17:5003/Umes/GetPartByUpn?upn_id=NE7X2KN
    @GET("Umes/GetPartByUpn")
    Call<GetPartByUpn_Response> getPartByUpn(@Query("upn_id") String upn_id);

}
