package com.example.arman.eshop.Api;
import com.example.arman.eshop.Api.Models.MCategorias;
import com.example.arman.eshop.Categorias;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CategoriasClient {

    @POST("api/categorias/")
    Call<MCategorias> getData();

    @FormUrlEncoded
    @POST("api/categorias/new")
    Call<MCategorias> NewCategory(
            @Field("nombreCategoria") String Categoria
    );

    @FormUrlEncoded
    @POST("api/categorias/consult")
    Call<MCategorias> ConsultCatergory(
            @Field("id") String idCategoria
    );

    @FormUrlEncoded
    @POST("api/categorias/edit")
    Call<ResponseBody> Editar(
            @Field("id") String idCategoria,
            @Field("Nombre") String Nombre
    );

    @FormUrlEncoded
    @POST("api/categorias/del")
    Call<ResponseBody> Eliminar(
            @Field("id") String idCategoria
    );
}
