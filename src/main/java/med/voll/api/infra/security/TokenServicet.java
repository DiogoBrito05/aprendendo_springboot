package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServicet {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataexpiracacao())
                    .sign(algoritmo);
            }
        catch (JWTCreationException exception){
              throw  new RuntimeException("Erro ao geral token jwt", exception);
            }
    }

    public String getSubject(String tokenJWT){
            try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                        .withIssuer("API Voll.med")
                        .build()
                        .verify(tokenJWT)
                        .getSubject();
            } catch (JWTCreationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
            }
        }

        //Teste de outra forma de validação do token.
//     //========================================================================
//        Instant expiracao = dataexpiracacao();
//    private static Instant dataexpiracacao() {
//        // Obtém o horário atual no Brasil
//        LocalDateTime horarioAtualBrasil = LocalDateTime.now(ZoneOffset.ofHours(-8));
//
//        // Adiciona 5 horas ao horário atual no Brasil
//        LocalDateTime expiracaoLocal = horarioAtualBrasil.plusHours(5);
//
//        // Converte para Instant
//        return expiracaoLocal.toInstant(ZoneOffset.UTC);
//    }//========================================================================


    private Instant dataexpiracacao() {
       return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

}
