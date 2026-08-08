package com.todocode.ferreteria.service;

import com.todocode.ferreteria.model.Producto;

import java.util.List;

public interface IProductoService {

    Producto saveProducto(Producto producto);

    List<Producto> findAllProductos();

    boolean deleteProducto(Long id);

    Producto findProducto(Long id);

    Producto updateProducto(Long id_original, Producto producto);
}
