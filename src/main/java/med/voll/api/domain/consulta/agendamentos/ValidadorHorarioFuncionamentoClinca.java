package med.voll.api.domain.consulta.agendamentos;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinca implements ValidadorAgendamentoDeConsulta{
    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaclinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() >18;
        if (domingo || antesDaAberturaDaclinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora de horário de funcionamento da clinica!");
        }

    }
}
