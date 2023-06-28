package com.etelhado.ace.erp.modules.usuario.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;
import com.etelhado.ace.erp.compartilhado.excecoes.ValidationViolationException;
import com.etelhado.ace.erp.compartilhado.modelos.ErroValidacaoDto;
import com.etelhado.ace.erp.mappers.UsuarioMapper;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioDto;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioFormCadDto;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioListaDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public void inserirUsuario(UsuarioFormCadDto usuarioDto) throws ValidationViolationException {

        if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmarSenha())) {
            throw new ValidationViolationException("Falha ao validar usuário", null, new ArrayList<>() {
                {
                    add(new ErroValidacaoDto("senha", "Os campos são diferentes"));
                    add(new ErroValidacaoDto("confirmarSenha", "Os campos são diferentes"));
                }
            });
        }
        UsuarioDto dto = new UsuarioDto();
        dto.setLogin(usuarioDto.getLogin());
        dto.setSetores(usuarioDto.getSetores());
        dto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        dto.setAtivo(true);
        usuarioMapper.inserirUsuario(dto);
    }

    public void atualizarUsuario(UsuarioDto usuarioDto) {
        if (Objects.nonNull(usuarioDto.getSenha())) {
            usuarioDto.setSenha(usuarioDto.getSenha());
        }
        usuarioMapper.atualizarUsuario(usuarioDto);
    }

    public Collection<UsuarioListaDto> buscarUsuarios(Long id, String login, Boolean ativo) {
        return usuarioMapper.listarUsuarios(id, login, ativo);
    }

    public Optional<Usuario> buscarUsuarioDetalhe(Long id) {
        return usuarioMapper.buscarUsuarioDetalhe(id);
    }
}
