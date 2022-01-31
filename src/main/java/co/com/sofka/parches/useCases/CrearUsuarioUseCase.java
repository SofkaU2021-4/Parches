package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearUsuarioUseCase implements CrearUsuario{
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;

    public CrearUsuarioUseCase(UsuarioRepository usuarioRepository, MapperUtils mapperUtils) {
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findByUid(usuarioDTO.getUid()).flatMap(
                usuario -> Mono.error(new ResponseStatusException(HttpStatus.CONFLICT)))
                .switchIfEmpty(usuarioRepository.save(mapperUtils.mapperDTOaEntidadUsuario(null)
                        .apply(usuarioDTO)))
                .thenReturn("save");
    }
}
