/*
 * Copyright 2019 AJ Sherrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ajsherrell.android.bakingapp.Utils;

import android.util.Log;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.Models.Bakery;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

// Used resource: https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792
// Used resource: https://stackoverflow.com/questions/39874017/jacksonconverterfactory-in-retrofit-builder

public class NetworkUtil implements Serializable {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    private static NetworkUtil instance = new NetworkUtil();

    private NetworkApiService networkApiService;

    private NetworkUtil() {
        if(instance != null) {
            throw new RuntimeException("getInstance() gets a single class instance");
        }

        // implement retrofit & jackson converter factory here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.JSON_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        networkApiService = retrofit.create(NetworkApiService.class);
    }

    public static NetworkUtil getInstance() {
        if (instance == null) {
            synchronized (NetworkUtil.class) {
                if (instance == null) instance = new NetworkUtil();
            }
        }
        return instance;
    }

    public void getBakery(final NetworkApiCallback<List<Bakery>> networkApiCallback) {
        networkApiService.getBakery().enqueue(new Callback<List<Bakery>>(){
            @Override
            public void onResponse(Call<List<Bakery>> call, Response<List<Bakery>> response) {
                networkApiCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Bakery>> call, Throwable t) {
                if (call.isCanceled()) {
                    Log.d(TAG, "onFailure: !!! this was cancelled" + networkApiCallback);
                    networkApiCallback.onCancel();
                } else {
                    Log.d(TAG, "onFailure: !!! was not cancelled");
                    networkApiCallback.onResponse(null);
                }
            }
        });
    }

}
