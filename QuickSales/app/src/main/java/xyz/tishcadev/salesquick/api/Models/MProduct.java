package xyz.tishcadev.salesquick.api.Models;

public class MProduct {
    private  int idProduct;
    private String CodeBar;
    private String Name;
    private float Cost;
    private float Price;
    private int Stock;
    private int Stock_Min;
    private String Nombre_Categoria;
    private float Price_Min;
    private float Price_Max;
    private String Tipo;

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return Name;
    }

    public float getCost() {
        return Cost;
    }

    public float getPrice() {
        return Price;
    }

    public int getStock() {
        return Stock;
    }

    public int getStock_Min() {
        return Stock_Min;
    }

    public String getNombre_Categoria() {
        return Nombre_Categoria;
    }

    public float getPrice_Min() {
        return Price_Min;
    }

    public String getCodeBar() {
        return CodeBar;
    }

    public float getPrice_Max() {
        return Price_Max;
    }

    public String getTipo() {
        return Tipo;
    }
}
