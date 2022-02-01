package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.UsuarioDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface CrearUsuario {
    Mono<UsuarioDTO> apply(@Valid UsuarioDTO usuarioDTO);
}
