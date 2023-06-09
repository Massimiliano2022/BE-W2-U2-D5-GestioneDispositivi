package it.epicode.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/register")
	public ResponseEntity<Utente> register(@RequestBody UtenteRegistrationPayload body) {
		Utente utenteCreato = utenteService.create(body);
		return new ResponseEntity<>(utenteCreato, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body) {

		Utente u = utenteService.findByEmail(body.getEmail());
		if (!body.getPassword().matches(u.getPassword()))
			throw new UnauthorizedException("Le credenziali inserite non sono valide!");
		String token = JWTTools.createToken(u);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
