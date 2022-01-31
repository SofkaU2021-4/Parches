package co.com.sofka.parches.utils;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.repositories.UsuarioRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class Validaciones {

    private final UsuarioRepository repositorio;

    public Validaciones(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Mono<Usuario> verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(String UID) {
        return verificarExistencia(UID);
    }

    public Mono<Usuario> verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(String UID) {
        return verificarExistencia(UID)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.CONFLICT)));
    }



    private Mono<Usuario> verificarExistencia(String UID) {
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(UID);
        } catch (FirebaseAuthException e) { }

        if (userRecord == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT));
        }

        return repositorio.findByUid(UID);
    }
}
