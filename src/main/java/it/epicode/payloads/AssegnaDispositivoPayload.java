package it.epicode.payloads;

import java.util.UUID;

import it.epicode.dispositivo.DisponibilitaDispositivo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssegnaDispositivoPayload {

	@Email
	String emailUtente;
	@NotNull(message = "Non hai inserito l'id del dispositivo! DATO OBBLIGATORIO")
	UUID dispositivoId;
	DisponibilitaDispositivo disponibilita;

}
