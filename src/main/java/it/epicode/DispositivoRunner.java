package it.epicode;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import it.epicode.dispositivo.DisponibilitaDispositivo;
import it.epicode.dispositivo.Dispositivo;
import it.epicode.dispositivo.DispositivoRepository;
import it.epicode.dispositivo.DispositivoService;
import it.epicode.dispositivo.TipoDispositivo;
import it.epicode.payloads.DispositivoPayload;

@Component
public class DispositivoRunner implements CommandLineRunner {

	@Autowired
	DispositivoService dispositivoService;

	@Autowired
	DispositivoRepository dispositivoRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		List<Dispositivo> dispositiviDb = dispositivoRepo.findAll();
		if (dispositiviDb.isEmpty()) {
			for (int i = 0; i < 10; i++) {
				String modelloDispositivo = faker.commerce().productName();
				TipoDispositivo tipoDispositvio = faker.options().option(TipoDispositivo.class);
				DisponibilitaDispositivo disponibilita = faker.options().option(DisponibilitaDispositivo.class);
				DispositivoPayload dispositivo = new DispositivoPayload(modelloDispositivo, tipoDispositvio,
						disponibilita);
				dispositivoService.create(dispositivo);
			}
		}
	}

}
