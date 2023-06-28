package com.etelhado.ace.erp.mappers;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioDto;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioListaDto;

@Mapper
public interface UsuarioMapper {
    public Optional<Usuario> buscarUsuarioDetalhe(@Param("id") Long id);

    public void inserirUsuario(UsuarioDto usuarioDto);

    public void atualizarUsuario(UsuarioDto usuarioDto);

    public Collection<UsuarioListaDto> listarUsuarios(@Param("id") Long id, @Param("login") String login, @Param("ativo") Boolean ativo);
}
