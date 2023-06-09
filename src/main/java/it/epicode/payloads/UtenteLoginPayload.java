package it.epicode.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UtenteLoginPayload {
	@Email(message = "Non hai inserito un indirizzo email valido")
	String email;
	@NotNull(message = "La password è obbligatoria")
	@Size(min = 3, max = 30, message = "Password min 3 caratteri, massimo 30")
	String password;
}
