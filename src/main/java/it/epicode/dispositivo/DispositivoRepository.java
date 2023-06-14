package it.epicode.dispositivo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

	Optional<Dispositivo> findByIdAndDisponibilitaDispositivo(UUID idDispositivo,
			DisponibilitaDispositivo disponibilitaDispositivo);

}
