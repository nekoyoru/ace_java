package com.etelhado.ace.erp.mappers;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;

@Mapper
public interface LoginMapper {
    @Cacheable(key = "#login", value = "usuariosCache")
    public Optional<Usuario> buscarUsuarioPorLogin(@Param("login") String login);
}
