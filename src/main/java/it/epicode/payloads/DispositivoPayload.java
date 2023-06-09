package it.epicode.payloads;

import it.epicode.dispositivo.DisponibilitaDispositivo;
import it.epicode.dispositivo.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DispositivoPayload {

	@NotNull(message = "Non hai inserito il modello del dispositivo")
	String modelloDispositivo;
	@NotNull(message = "Non hai inserito il tipo di dispositivo")
	TipoDispositivo tipoDispositivo;
	@NotNull(message = "Non hai inserito la disponibilità del dispositivo")
	DisponibilitaDispositivo disponibilitaDispositivo;

}
