package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenServicet;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenServicet tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
//===================================================================================
//    @PostMapping
//    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
//        try {
//            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//            var authentication = manager.authenticate(authenticationToken);
//
//            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
//
//            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//==========================================================================================================
//    @PostMapping
//    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
//        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//        var authentication = manager.authenticate(authenticationToken);
//        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
//        return  ResponseEntity.ok(new DadosTokenJWT(tokenJwt));
//    }
//==========================================================================================================

