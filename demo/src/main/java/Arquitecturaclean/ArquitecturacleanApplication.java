package Arquitecturaclean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquitecturacleanApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArquitecturacleanApplication.class, args);
		System.out.println("\n=========================================");
		System.out.println("✅ ARQUITECTURA LIMPIA - H2 DATABASE");
		System.out.println("📊 Consola H2: http://localhost:8080/h2-console");
		System.out.println("🔗 JDBC URL: jdbc:h2:mem:orderdb");
		System.out.println("👤 Usuario: sa");
		System.out.println("🔑 Password: (vacío)");
		System.out.println("=========================================\n");
	}
}