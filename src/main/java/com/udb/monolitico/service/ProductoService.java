package com.udb.monolitico.service;

import java.util.List;
import java.util.Objects;
import com.udb.monolitico.repository.ProductoRespository;
import com.udb.monolitico.entity.Producto;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRespository respository;

    public ProductoService(ProductoRespository respository) {
        this.respository = respository;
    }
    public List<Producto> listar(){
        return respository.findAll();
    }
    public Producto guardar(Producto producto){
        return respository.save(producto);
    }
    public Producto buscarPorId(Long id){
        return respository.findById(id).orElse(null);
    }
    public void  eliminar(Long id){
        Producto producto = buscarPorId(id);
    }
}
