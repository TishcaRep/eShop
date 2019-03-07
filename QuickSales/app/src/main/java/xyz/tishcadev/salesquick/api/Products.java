package xyz.tishcadev.salesquick.api;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import xyz.tishcadev.salesquick.api.Models.MProduct;
import xyz.tishcadev.salesquick.api.Models.MProducts;

public interface Products {
    @POST("api/products/")
    Call<MProducts> ProductsFeed();

    @POST("api/products/new")
    Call<MProducts> ProductsNew(
            @Field("CodeBar") String CodeBar,
            @Field("Name") String Name,
            @Field("Cost") float Cost,
            @Field("Price") float Price,
            @Field("Stock") int Stock,
            @Field("Stock_Min") int Stock_Min,
            @Field("Nombre_Categoria") String Nombre_Categoria,
            @Field("Price_Min") float Price_Min,
            @Field("Price_Max") float Price_Max,
            @Field("Tipo") String Tipo
    );
}
