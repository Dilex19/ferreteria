package com.todocode.ferreteria.service;

import com.todocode.ferreteria.model.Producto;
import com.todocode.ferreteria.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productoService implements IProductoService {

    @Autowired
    private productoRepository productoRepository;

    @Override
    public Producto saveProducto(Producto producto) {
        if (!validarDatos(producto)) {
            return null;
        }
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Producto findProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto updateProducto(Long id_original, Producto producto) {
        if (!validarDatos(producto)) {
            return null;
        }
        if (productoRepository.existsById(id_original)) {
            producto.setId(id_original);
            return productoRepository.save(producto);
        } else {
            return null;
        }
    }

    private boolean validarDatos(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return false;
        }
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            return false;
        }
        if (producto.getMarca() == null || producto.getMarca().isEmpty()) {
            return false;
        }
        if (producto.getCategoria() == null || producto.getCategoria().isEmpty()) {
            return false;
        }
        if (producto.getStock() < 0) {
            return false;
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            return false;
        }
        return true;
    }
}
