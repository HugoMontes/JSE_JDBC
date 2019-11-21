package com.educomser.dao.impl;

import com.educomser.dao.ProductoDao;
import com.educomser.pojo.Producto;
import com.educomser.util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDaoImpl implements ProductoDao {

    @Override
    public int save(Producto producto) {
        int rows = 0;
        try {
            Conexion.openConnection();
            String sql = "INSERT INTO producto(nombre, unidad_medida,precio, stock_actual, stock_minimo, fecha_vencimiento, id_categoria) ";
            sql += "VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?)";
            PreparedStatement st = Conexion.getConnection().prepareStatement(sql);
            st.setString(1, producto.getNombre());
            st.setString(2, producto.getUnidadMedida());
            st.setDouble(3, producto.getPrecio());
            st.setInt(4, producto.getStockActual());
            st.setInt(5, producto.getStockMinimo());
            st.setDate(6, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            st.setInt(7, producto.getIdCategoria());
            Logger.getLogger(getClass().getName()).log(Level.INFO, st.toString());
            rows = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR DE CONSULTA : {0}", ex);
        } finally {
            Conexion.closeConnection();
        }
        return rows;
    }

    @Override
    public int update(Producto producto) {
        int rows = 0;
        try {
            Conexion.openConnection();
            String sql = "UPDATE producto SET nombre=?, unidad_medida=?, precio=?, stock_actual=?, stock_minimo=?, fecha_vencimiento=?, id_categoria=?, updated_at=? WHERE  id=?";
            PreparedStatement st = Conexion.getConnection().prepareStatement(sql);
            st.setString(1, producto.getNombre());
            st.setString(2, producto.getUnidadMedida());
            st.setDouble(3, producto.getPrecio());
            st.setInt(4, producto.getStockActual());
            st.setInt(5, producto.getStockMinimo());
            st.setDate(6, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            st.setInt(7, producto.getIdCategoria());
            st.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));
            st.setInt(9, producto.getId());
            Logger.getLogger(getClass().getName()).log(Level.INFO, st.toString());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR DE CONSULTA : {0}", ex);
        } finally {
            Conexion.closeConnection();
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        try {
            Conexion.openConnection();
            String sql = "DELETE FROM producto WHERE id=?";
            PreparedStatement st = Conexion.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            Logger.getLogger(getClass().getName()).log(Level.INFO, st.toString());
            st.executeUpdate();
        } catch (SQLException ex) {
            String msg = "Error al guardar: ";
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
        } finally {
            Conexion.closeConnection();
        }
        return rows;
    }

    @Override
    public List<Producto> findAllProductos() {
        List<Producto> list = new ArrayList<Producto>();
        try {
            Conexion.openConnection();
            String sql = "SELECT id, nombre, unidad_medida, precio, stock_actual, stock_minimo, fecha_vencimiento FROM producto";
            Logger.getLogger(getClass().getName()).log(Level.INFO, sql);
            Statement st = Conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setId(rs.getInt("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setUnidadMedida(rs.getString("unidad_medida"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setStockMinimo(rs.getInt("stock_minimo"));
                prod.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                list.add(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR DE CONSULTA : {0}", ex);
        } finally {
            Conexion.closeConnection();
        }
        return list;
    }

    @Override
    public Producto findByIdProducto(int id) {
        Producto prod = null;
        try {
            Conexion.openConnection();
            String sql = "SELECT id, nombre, unidad_medida, precio, stock_actual, stock_minimo, fecha_vencimiento, id_categoria FROM producto WHERE id= ?";
            PreparedStatement st = Conexion.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            Logger.getLogger(getClass().getName()).log(Level.INFO, st.toString());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                prod = new Producto();
                prod.setId(rs.getInt("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setUnidadMedida(rs.getString("unidad_medida"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setStockMinimo(rs.getInt("stock_minimo"));
                prod.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                prod.setIdCategoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR DE CONSULTA : {0}", ex);
        } finally {
            Conexion.closeConnection();
        }
        return prod;
    }
}
