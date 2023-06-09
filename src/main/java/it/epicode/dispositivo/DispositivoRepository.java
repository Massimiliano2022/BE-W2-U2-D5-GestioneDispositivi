package it.epicode.dispositivo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

}
