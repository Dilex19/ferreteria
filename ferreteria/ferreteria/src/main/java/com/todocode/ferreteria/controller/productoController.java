package com.todocode.ferreteria.controller;

import com.todocode.ferreteria.model.Producto;
import com.todocode.ferreteria.service.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class productoController {

    @Autowired
    private productoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return ResponseEntity.ok(productoService.findAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id){
        Producto producto = productoService.findProducto(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra un producto con el codigo " + id);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){

        Producto newProducto = productoService.saveProducto(producto);

        if (newProducto == null) {
            return ResponseEntity.badRequest().body("Error al crear el producto");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        boolean eliminado = productoService.deleteProducto(id);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra un producto con el codigo " + id);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto editado = productoService.updateProducto(id, producto);
        if (editado != null) {
            return ResponseEntity.ok(editado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al editar el producto");
    }
}


