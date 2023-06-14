package it.epicode.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteInfoPayload {

	String username;
	String nome;
	String cognome;
	String email;

}
