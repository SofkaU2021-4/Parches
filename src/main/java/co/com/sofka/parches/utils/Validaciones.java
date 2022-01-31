package co.com.sofka.parches.utils;

import co.com.sofka.parches.personal.PruebaModel;
import co.com.sofka.parches.personal.Repositorio;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Validaciones {


    private final Repositorio repositorio;

    public Validaciones(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Mono<PruebaModel> verificar(String UID) {
        String uid = "M1PPeC5Ax5flEqSRMyzKpXAgOGD2";
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(UID);
            log.info("Successfully fetched user data: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
        }

        if (userRecord == null) {
            return Mono.error(new IllegalArgumentException("El usuario no existe"));
        }

        return repositorio.findByUid(UID)
               // .switchIfEmpty(Mono.error((new IllegalArgumentException("El usuario no existe"))));
                .switchIfEmpty(Mono.error(()->{
                    return new IllegalArgumentException("El usuario no existe");
                }));
    }
}
