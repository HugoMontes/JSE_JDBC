package com.educomser.test;

import com.educomser.dao.ProductoDao;
import com.educomser.dao.impl.ProductoDaoImpl;
import com.educomser.pojo.Producto;

public class ProductoListar {

    public static void main(String[] args) {
        ProductoDao pdao = new ProductoDaoImpl();
        for(Producto prod : pdao.findAllProductos()){
            System.out.println(prod);
        }
    }    
}
