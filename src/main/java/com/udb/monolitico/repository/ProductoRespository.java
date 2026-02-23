package com.udb.monolitico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udb.monolitico.entity.Producto;

public interface ProductoRespository  extends JpaRepository<Producto, Long>{
}
