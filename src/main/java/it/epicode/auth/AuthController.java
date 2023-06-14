package it.epicode.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.auth.payloads.AuthenticationSuccessfullPayload;
import it.epicode.exceptions.UnauthorizedException;
import it.epicode.payloads.UtenteLoginPayload;
import it.epicode.payloads.UtenteRegistrationPayload;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService utenteService;

	@Autowired
	private PasswordEncoder bcrypt;

	// OK
	@PostMapping("/register")
	public ResponseEntity<Utente> register(@RequestBody @Validated UtenteRegistrationPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente utenteCreato = utenteService.create(body);
		return new ResponseEntity<>(utenteCreato, HttpStatus.OK);
	}

	// OK
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body) {

		// VERIFICO CHE L'EMAIL DELL'UTENTE SIA PRESENTE SUL DB
		Utente u = utenteService.findByEmail(body.getEmail());
		// IN CASO AFFERMATIVO DEVO VERIFICARE CHE LA PW CORRISPONDA A QUELLA TROVATA
		// NEL DB
		/*
		 * if (!body.getPassword().matches(u.getPassword())) throw new
		 * UnauthorizedException("Le credenziali inserite non sono valide!");
		 */

		String plainPW = body.getPassword();
		String hashedPW = u.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");

		// SE TUTTO OK GENERO IL TOKEN
		String token = JWTTools.createToken(u);
		// ALTRIMENTI -> 401 CREDENZIALI NON VALIDE
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}
}
