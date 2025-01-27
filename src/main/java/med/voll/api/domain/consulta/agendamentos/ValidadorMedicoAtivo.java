package med.voll.api.domain.consulta.agendamentos;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;
//.....;
//    @Autowired
//    public ValidadorMedicoAtivo(MedicoRepository repository) {
//        this.repository = repository;
//    }
//....;
    public  void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw  new ValidacaoException("Consulta não pode ser agendada com médico escluído!");
        }
    }


}
