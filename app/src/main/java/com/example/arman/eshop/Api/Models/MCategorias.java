package com.example.arman.eshop.Api.Models;

import java.util.ArrayList;

public class MCategorias {
    private Boolean error;
    private String mensaje;
    private ArrayList<MCategoria> categorias;

    public Boolean getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public ArrayList<MCategoria> getCategorias() {
        return categorias;
    }
}
