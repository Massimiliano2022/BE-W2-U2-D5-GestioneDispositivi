package it.epicode.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UtenteRegistrationPayload {
	@NotNull(message = "L'username è obbligatorio")
	@Size(min = 3, max = 30, message = "Username min 3 caratteri, massimo 30")
	String username;
	@NotNull(message = "Il nome è obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	String nome;
	@NotNull(message = "Il cognome è obbligatorio")
	String cognome;
	@Email(message = "Non hai inserito un indirizzo email valido")
	String email;

	@NotNull(message = "La password è obbligatoria")
	String password;
}