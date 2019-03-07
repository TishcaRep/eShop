package xyz.tishcadev.salesquick.api.Models;

import java.util.ArrayList;

public class MProducts {
    private Boolean error;
    private String mensaje;
    private ArrayList<MProduct> productos;

    public Boolean getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public ArrayList<MProduct> getProductos() {
        return productos;
    }
}
