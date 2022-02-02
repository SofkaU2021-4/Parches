package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ActualizarUsuarioUseCase implements Function<UsuarioDTO, Mono<UsuarioDTO>>
{
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;

    public ActualizarUsuarioUseCase(MapperUtils mapperUtils, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<UsuarioDTO> apply(UsuarioDTO usuarioDTO) {
       return usuarioRepository.findById(usuarioDTO.getId())
            .flatMap(usuario -> usuarioRepository.save(mapperUtils.mapperDTOaEntidadUsuario(usuarioDTO.getId()).apply(usuarioDTO))
                    .map(mapperUtils.mapperEntidadUsuarioaDTO()))
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "Usuario no Existe")));
}

}