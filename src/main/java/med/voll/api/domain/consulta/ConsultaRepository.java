package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository < Consulta, Long> {
    boolean existsByPacienteIdAndDataBetween(Long aLong);

    boolean existsByIMedicoIdAndData(Long aLong, LocalDateTime data);

}