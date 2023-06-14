package it.epicode.dispositivo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.epicode.utente.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "dispositivi")
@Data
@NoArgsConstructor
public class Dispositivo {

	@Id
	@GeneratedValue
	private UUID id;
	private String modelloDispositivo;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipoDispositivo;
	@Enumerated(EnumType.STRING)
	private DisponibilitaDispositivo disponibilitaDispositivo;

	@ManyToOne
	@JoinColumn(name = "utente_id", referencedColumnName = "id")
	@JsonBackReference
	private Utente utente;

	public Dispositivo(String modelloDispositivo, TipoDispositivo tipoDispositivo,
			DisponibilitaDispositivo disponibilitaDispositivo) {
		setModelloDispositivo(modelloDispositivo);
		setTipoDispositivo(tipoDispositivo);
		setDisponibilitaDispositivo(disponibilitaDispositivo);
	}
}
