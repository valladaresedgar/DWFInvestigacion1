package com.udb.monolitico.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.udb.monolitico.service.ProductoService;
import com.udb.monolitico.entity.Producto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }
    @GetMapping
    public List<Producto> listar(){
        return service.listar();
    }
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.guardar(producto);
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "Producto eliminado correctamente";
    }
}
