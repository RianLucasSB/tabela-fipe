package com.boas.rian.tabela.fipe;

import com.boas.rian.tabela.fipe.application.Application;
import com.boas.rian.tabela.fipe.service.ConvertData;
import com.boas.rian.tabela.fipe.service.FetchApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConvertData conversor = new ConvertData();
		FetchApi fetchApi = new FetchApi();

		Application application = new Application(conversor, fetchApi);

		application.showMenu();
	}
}
