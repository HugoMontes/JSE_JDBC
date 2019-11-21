package com.educomser.test;

import com.educomser.dao.ProductoDao;
import com.educomser.dao.impl.ProductoDaoImpl;
import com.educomser.pojo.Producto;
import java.util.Scanner;

public class ProductoEditar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ProductoDao pdao = new ProductoDaoImpl();
        System.out.println("Ingreser id del producto a editar: ");
        Producto prod = pdao.findByIdProducto(in.nextInt());
        if (prod == null) {
            System.out.println("No se encuentra el id del producto");
            System.exit(0);
        }
        System.out.println("Producto encontrado!");
        System.out.println(prod);
        System.out.println("Ingrese nuevo nombre:");
        prod.setNombre(in.next());
        pdao.update(prod);
        System.out.println("Producto editado correctamente!");
    }
}
