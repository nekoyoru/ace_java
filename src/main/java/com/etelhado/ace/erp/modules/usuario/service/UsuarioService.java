package com.etelhado.ace.erp.modules.usuario.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;
import com.etelhado.ace.erp.mappers.UsuarioMapper;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioDto;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioListaDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public void inserirUsuario(UsuarioDto usuarioDto) {
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        usuarioMapper.inserirUsuario(usuarioDto);
    }

    public void atualizarUsuario(UsuarioDto usuarioDto) {
        if (Objects.nonNull(usuarioDto.getSenha())) {
            usuarioDto.setSenha(usuarioDto.getSenha());
        }
        usuarioMapper.atualizarUsuario(usuarioDto);
    }

    public Collection<UsuarioListaDto> buscarUsuarios(Long id, String login, Boolean ativo) {
        return usuarioMapper.listarUsuarios(id, login,ativo);
    }

    public Optional<Usuario> buscarUsuarioDetalhe(Long id) {
        return usuarioMapper.buscarUsuarioDetalhe(id);
    }
}
