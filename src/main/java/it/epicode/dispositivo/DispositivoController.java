package it.epicode.dispositivo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.payloads.AssegnaDispositivoPayload;
import it.epicode.payloads.DispositivoPayload;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteService;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

	@Autowired
	private DispositivoService dispositivoService;

	@Autowired
	private UtenteService utenteService;

	// DISPOSITIVI
	// OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody DispositivoPayload body) {
		return dispositivoService.create(body);
	}

	// OK
	@GetMapping("")
	public List<Dispositivo> getDispositivi() {
		return dispositivoService.find();
	}

	// OK
	@GetMapping("/{dispositivoId}")
	public Dispositivo getDispositivio(@PathVariable UUID dispositivoId) throws Exception {
		return dispositivoService.findById(dispositivoId);
	}

	// OK
	@PutMapping("/{dispositivoId}")
	public Dispositivo updateDispositivo(@PathVariable UUID dispositivoId, @RequestBody Dispositivo body)
			throws Exception {
		return dispositivoService.findByIdAndUpdate(dispositivoId, body);
	}

	// OK
	@DeleteMapping("/{dispositivoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositivo(@PathVariable UUID dispositivoId) throws Exception {
		dispositivoService.findByIdAndDelete(dispositivoId);
	}

	// ASSEGNA DISPOSITIVO
	@PostMapping("/assegnaDispositivo")
	public Dispositivo assegnaDispositivo(@RequestBody @Validated AssegnaDispositivoPayload body) {

		// VERIFICO SE ESISTE L'UTENTE
		Utente u = utenteService.findByEmail(body.getEmailUtente());

		// VERIFICO ID DISPOSITIVO E DISPONIBILITA
		Dispositivo d = dispositivoService.findByIdAndDisponibilitaDispositivo(body.getDispositivoId(),
				body.getDisponibilita());

		// AGGIUNGO IL DISPOSITIVO ALLA LISTA DISPOSITIVI UTENTE
		List<Dispositivo> listaDispositiviUtente = u.getDipositiviAssegnati();
		listaDispositiviUtente.add(d);

		// ASSEGNO LA LISTA DISPOSITIVI AGGIORNATA ALL'UTENTE
		u.setDipositiviAssegnati(listaDispositiviUtente);

		// ESEGUO UPDATE UTENTE
		utenteService.findByIdAndUpdate(u.getId(), u);

		// ASSEGNO L'UTENTE AL DISPOSITIVO E CAMBIO DISPONIBILITA
		d.setUtente(u);
		d.setDisponibilitaDispositivo(DisponibilitaDispositivo.ASSEGNATO);

		// ESEGUO UPDATE DISPOSITIVO
		return dispositivoService.findByIdAndUpdate(d.getId(), d);
	}
}
