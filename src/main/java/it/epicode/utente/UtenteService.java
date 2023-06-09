package it.epicode.utente;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.exceptions.BadRequestException;
import it.epicode.exceptions.NotFoundException;
import it.epicode.payloads.UtenteRegistrationPayload;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepo;

	public Utente create(UtenteRegistrationPayload body) {

		utenteRepo.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " già utilizzata!");
		});

		Utente nuovoUtente = new Utente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail(),
				body.getPassword());

		return utenteRepo.save(nuovoUtente);
	}

	public List<Utente> find() {
		return utenteRepo.findAll();
	}

	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato!"));
	}

	public Utente findByEmail(String email) throws NotFoundException {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con " + email + " non trovato!"));
	}

	public Utente findByIdAndUpdate(UUID id, Utente u) throws NotFoundException {
		Utente found = this.findById(id);

		found.setId(id);
		found.setNome(u.getNome());
		found.setCognome(u.getCognome());
		found.setEmail(u.getEmail());

		return utenteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}

}
