package com.ajsherrell.android.bakingapp.Utils;

import com.ajsherrell.android.bakingapp.Models.Bakery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// used resource: https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792

public interface NetworkApiService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Bakery>> getBakery();

}
