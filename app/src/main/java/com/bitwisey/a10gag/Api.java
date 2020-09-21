package com.bitwisey.a10gag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("gimme")
    Call<RetroInfo> getMemes();
}
