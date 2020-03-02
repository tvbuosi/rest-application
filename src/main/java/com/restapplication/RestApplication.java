package com.restapplication;

import com.restapplication.entity.Address;
import com.restapplication.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Address address1 = new Address(null, "Rua Abilio Jorge Cury", 50, null, "Jardim Nazareth",
				"São José do Rio Preto", "São Paulo", "Brasil", "15054130", null, null);

		Address address2 = new Address(null, "Rua da Imprensa", 100, null, "Monte Castelo",
				"Campo Grande", "Mato Grosso do Sul", "Brasil", "79002290", null, null);

		Address address3 = new Address(null, "Rua Paracatu", 150, null, "Parque Imperial",
				"São Paulo", "São Paulo", "Brasil", "04302021", null, null);

		Address address4 = new Address(null, "Avenida Almirante Maximiano Fonseca", 200, null, "Zona Portuária",
				"Rio Grande", "Rio Grande do Sul", "Brasil", "96204040", null, null);

		addressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));
	}
}
