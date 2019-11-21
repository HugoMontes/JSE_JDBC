package com.educomser.test;

import com.educomser.dao.ProductoDao;
import com.educomser.dao.impl.ProductoDaoImpl;
import com.educomser.pojo.Producto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoGuardar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("-- GUARDAR PRODUCTO --");
        Producto prod = new Producto();
        System.out.println("Nombre: ");
        prod.setNombre(in.next());
        System.out.println("Unidad de medida: ");
        prod.setUnidadMedida(in.next());
        System.out.println("Precio: ");
        prod.setPrecio(in.nextDouble());
        System.out.println("Stock actual: ");
        prod.setStockActual(in.nextInt());
        System.out.println("Stock minimo: ");
        prod.setStockMinimo(in.nextInt());
        System.out.println("Fecha de vencimiento (dd/mm/aaaa): ");
        String fechaVenc = in.next();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            prod.setFechaVencimiento(df.parse(fechaVenc));
        } catch (ParseException ex) {
            Logger.getLogger(ProductoGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Id categoria: ");
        prod.setIdCategoria(in.nextInt());
        ProductoDao pdao = new ProductoDaoImpl();
        pdao.save(prod);
        System.out.println("El producto fue guardado!");
    }

}
