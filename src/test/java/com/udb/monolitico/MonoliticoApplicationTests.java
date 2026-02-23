package com.udb.monolitico;

import com.udb.monolitico.entity.Producto;
import com.udb.monolitico.repository.ProductoRespository;
import com.udb.monolitico.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonoliticoApplicationTests {

	@Autowired
	private ProductoRespository repository;

	@Test
	void guardarYListarProducto() {

		// Crear producto
		Producto producto = new Producto(null, "Mouse", 25.50);
		repository.save(producto);

		// Obtener lista
		List<Producto> lista = repository.findAll();

		// Verificar que no esté vacía
		assertFalse(lista.isEmpty());
	}

	@Autowired
	private ProductoService service;

	@Test
	void testGuardarProducto() {
		Producto producto = new Producto(null, "Teclado", 45.00);
		Producto guardado = service.guardar(producto);

		assertNotNull(guardado.getId());
	}
}
