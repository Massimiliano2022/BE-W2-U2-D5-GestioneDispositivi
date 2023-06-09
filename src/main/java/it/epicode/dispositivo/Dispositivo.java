package it.epicode.dispositivo;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dispositivi")
@Data
@NoArgsConstructor
public class Dispositivo {

	@Id
	@GeneratedValue
	private UUID id;
	private String modelloDispositivo;
	private TipoDispositivo tipoDispositivo;
	private DisponibilitaDispositivo disponibilitaDispositivo;

	public Dispositivo(String modelloDispositivo, TipoDispositivo tipoDispositivo,
			DisponibilitaDispositivo disponibilitaDispositivo) {
		setModelloDispositivo(modelloDispositivo);
		setTipoDispositivo(tipoDispositivo);
		setDisponibilitaDispositivo(disponibilitaDispositivo);
	}
}
