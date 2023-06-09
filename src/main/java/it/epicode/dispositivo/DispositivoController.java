package it.epicode.dispositivo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.payloads.DispositivoPayload;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

	@Autowired
	private DispositivoService dispositivoService;

	@GetMapping("")
	public List<Dispositivo> getDispositivi() {
		return dispositivoService.find();
	}

	@PostMapping("")

	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody DispositivoPayload body) {
		return dispositivoService.create(body);
	}

	@GetMapping("/{dispositivoId}")
	public Dispositivo getDispositivio(@PathVariable UUID dispositivoId) throws Exception {
		return dispositivoService.findById(dispositivoId);
	}

	@PutMapping("/{dispositivoId}")
	public Dispositivo updateUser(@PathVariable UUID dispositivoId, @RequestBody Dispositivo body) throws Exception {
		return dispositivoService.findByIdAndUpdate(dispositivoId, body);
	}

	@DeleteMapping("/{dispositivoId}")

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositivo(@PathVariable UUID dispositivoId) throws Exception {
		dispositivoService.findByIdAndDelete(dispositivoId);

	}

}
