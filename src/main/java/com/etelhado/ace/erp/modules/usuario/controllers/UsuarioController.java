package com.etelhado.ace.erp.modules.usuario.controllers;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;
import com.etelhado.ace.erp.compartilhado.modelos.RespostaGenerica;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioDto;
import com.etelhado.ace.erp.modules.usuario.dto.UsuarioListaDto;
import com.etelhado.ace.erp.modules.usuario.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(path = "")
    public ResponseEntity<Collection<UsuarioListaDto>> index(@RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "ativo", required = false) Boolean ativo) {
        return ResponseEntity.ok(usuarioService.buscarUsuarios(id, login, ativo));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> buscarUsuarioId(@PathVariable("id") Long id) throws Exception {
        var usuarioOp = usuarioService.buscarUsuarioDetalhe(id);
        if (usuarioOp.isPresent()) {
            return ResponseEntity.ok(usuarioOp.get());
        }

        throw new Exception("Usuário não cadastrado");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/")
    public ResponseEntity<RespostaGenerica> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.inserirUsuario(usuarioDto);
        return ResponseEntity.ok(new RespostaGenerica(200, "Usuário cadastrado com sucesso!"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/")
    public ResponseEntity<RespostaGenerica> atualizarUsuario(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.inserirUsuario(usuarioDto);
        return ResponseEntity.ok(new RespostaGenerica(200, "Usuário atualizado com sucesso!"));
    }
}
