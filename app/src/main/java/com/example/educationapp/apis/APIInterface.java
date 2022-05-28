package com.example.educationapp.apis;

import com.example.educationapp.Models.PredictResponse;;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface
{

    @POST("http://ec2-13-239-85-240.ap-southeast-2.compute.amazonaws.com:8080/predict")
    Call<PredictResponse> predictServer(
            @Body RequestBody params
    );

}
