package com.todocode.ferreteria.controller;

import com.todocode.ferreteria.model.Producto;
import com.todocode.ferreteria.service.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class productoWebController {

    @Autowired
    private productoService productoService;

    @GetMapping
    public String traerProductos(Model model) {
        model.addAttribute(
                "productos",
                productoService.findAllProductos());
        return "lista";
    }

    @GetMapping("/productos")
    public String traerProductosX2(Model model) {
        model.addAttribute(
                "productos",
                productoService.findAllProductos());
        return "lista";
    }


    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Registrar producto");
        return "formulario";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto, Model model) {

        Producto resultado;

        if(producto.getId()==null){
            resultado = productoService.saveProducto(producto);
        } else {
            resultado = productoService.updateProducto(
                    producto.getId(),
                    producto);
        }

        if(resultado==null){
            model.addAttribute("producto", resultado);
            model.addAttribute("titulo",
                    producto.getId()==null
                            ? "Registrar producto"
                            : "Editar producto"
            );

            model.addAttribute(
                    "error",
                    "Revisar los datos. Nombre, marca y categoria son obligatorios. " +
                            "El precio el precio debe ser mayor a cero y el stock no puede ser negativo"
            );

            return "formulario";
        }
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(
            Model model,
            @PathVariable Long id) {

        Producto producto = productoService.findProducto(id);

        if(producto==null){
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar producto");

        return "formulario";
    }


    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {

        productoService.deleteProducto(id);

        return "redirect:/productos";
    }
}
