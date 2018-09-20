package com.example.kringle.infinity.retrofit;

import com.example.kringle.infinity.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IUser {

    @Headers("content-type: application/json")
    @GET("register.php")
    Call<User> performRegistration(@Query("username") String username,
                                   @Query("email") String email,
                                   @Query("password") String password,
                                   @Query("question1") String question1,
                                   @Query("question2") String question2,
                                   @Query("question3") String question3
                                   );

    @Headers("content-type: application/json")
    @GET("login.php")
    Call<User> performUserLogin(@Query("email") String email,
                                @Query("password") String password);


    @Headers("content-type: application/json")
    @GET("check.php")
    Call<User> checkUser(@Query("email") String email);

}
