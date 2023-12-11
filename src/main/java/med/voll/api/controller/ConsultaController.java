package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name= "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto );
    }

//    @PostMapping
//    @Transactional
//    @Operation(summary = "Realiza a marcação de consultas médicas")
//    @ApiResponse(responseCode = "400", description = "Id do paciente informado não existe!")
//    @ApiResponse(responseCode = "403", description = "Id do médico informado não existe!")
//    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
//        var dadosDetalhamento = agenda.agendar(dados);
//        return ResponseEntity.ok(new DadosDetalhamentoConsulta(dadosDetalhamento, id(), dadosDetalhamento.idMedico(), dadosDetalhamento.idPaciente(), dadosDetalhamento.data()));
//    }


    //.........................................................
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
//.........................................................

//    public void cancelar(DadosCancelamentoConsulta dados) {
//        if (!consultaRepository.existsById(dados.idConsulta())) {
//            throw new ValidacaoException("Id da consulta informado não existe!");
//        }
//
//        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
//        consulta.cancelar(dados.motivo());
//    }
///...................................................

}
