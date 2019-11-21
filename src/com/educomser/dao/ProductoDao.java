package com.educomser.dao;

import com.educomser.pojo.Producto;
import java.util.List;

public interface ProductoDao {

    public int save(Producto producto);

    public int update(Producto producto);

    public int delete(int id);

    public List<Producto> findAllProductos();

    public Producto findByIdProducto(int id);
}
