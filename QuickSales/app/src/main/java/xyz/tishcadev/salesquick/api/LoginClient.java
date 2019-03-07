package xyz.tishcadev.salesquick.api;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.tishcadev.salesquick.api.Models.MLoginClient;

public interface LoginClient {
    @FormUrlEncoded
    @POST("api/login/")
    Call<MLoginClient> sendLoginFeedback(
            @Field("User") String User,
            @Field("Password") String Password
    );
}
