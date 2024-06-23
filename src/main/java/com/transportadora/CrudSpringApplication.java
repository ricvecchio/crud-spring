package com.transportadora;

import com.transportadora.model.Pedido;
import com.transportadora.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatagase(PedidoRepository pedidoRepository){
		return args -> {
			pedidoRepository.deleteAll();

			Pedido p = new Pedido();
			p.setNome("Ricardo Teste inclusão JPA");
			p.setEndereco("Rua Quinze de Setembro, 50");
			p.setMangueira("30 metros");
			p.setValor("R$ 150,00");
			p.setStatus("Em Aberto");


			pedidoRepository.save(p);

		};
	}
}
