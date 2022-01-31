package co.com.sofka.parches.mappers;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<Usuario, UsuarioDTO> mapperEntidadUsuarioaDTO(){
        return entidad -> new UsuarioDTO(
                entidad.getId(),
                entidad.getUid(),
                entidad.getNombres(),
                entidad.getEmail(),
                entidad.getImagenUrl()
        );
    }

    public Function<UsuarioDTO, Usuario> mapperDTOaEntidadUsuario(String id){
        return crearUsuario -> {
            var usuario = new Usuario();
            usuario.setId(id);
            usuario.setUid(crearUsuario.getUid());
            usuario.setNombres(crearUsuario.getNombres());
            usuario.setEmail(crearUsuario.getEmail());
            usuario.setImagenUrl(crearUsuario.getImagenUrl());
            return usuario;
        };
    }

}
