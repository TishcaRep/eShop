package com.example.arman.eshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.arman.eshop.Api.LoginClient;
import com.example.arman.eshop.Api.Models.MLoginClient;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private EditText User;
    private EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        User = findViewById(R.id.user);
        Password = findViewById(R.id.password);
    }

    public void Login(View view) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        CookieHandler cookieHandler = new CookieManager();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        LoginClient loginClient = retrofit.create(LoginClient.class);
        Call<MLoginClient> call = loginClient.Login(
                User.getText().toString(), Password.getText().toString()
        );

        call.enqueue(new Callback <MLoginClient>() {

            @Override
            public void onResponse(Call<MLoginClient> call, Response<MLoginClient> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error de peticion",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),""+response.body().getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MLoginClient> call, Throwable t) {

            }
        });

    }
}
