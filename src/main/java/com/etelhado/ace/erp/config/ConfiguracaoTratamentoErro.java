package com.etelhado.ace.erp.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etelhado.ace.erp.compartilhado.excecoes.ErroAutenticacao;
import com.etelhado.ace.erp.compartilhado.modelos.ErroValidacaoDto;
import com.etelhado.ace.erp.compartilhado.modelos.RespostaErroPadraoDto;

@RestControllerAdvice
public class ConfiguracaoTratamentoErro {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErroPadraoDto> tratarValidacao(final MethodArgumentNotValidException erro) {
        final BindingResult resultado = erro.getBindingResult();
        final List<ErroValidacaoDto> erros = resultado.getFieldErrors().stream().map(e -> {
            final ErroValidacaoDto erroValidacaoDto = new ErroValidacaoDto();
            erroValidacaoDto.setCampo(e.getField());
            erroValidacaoDto.setMensagem(e.getDefaultMessage());
            return erroValidacaoDto;
        }).collect(Collectors.toList());
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        respostaErroPadrao.setMensagem("Um ou mais campos necessita de atenção");
        respostaErroPadrao.setValidacoes(erros);
        return ResponseEntity.status(erro.getStatusCode().value()).body(respostaErroPadrao);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RespostaErroPadraoDto> tratarErroGenerico(final Throwable erro) {
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        respostaErroPadrao.setMensagem("Erro interno de servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(respostaErroPadrao);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespostaErroPadraoDto> metodoNaoPermitido(final HttpRequestMethodNotSupportedException erro) {
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        respostaErroPadrao.setMensagem("Metodo não permitido");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(respostaErroPadrao);
    }

    @ExceptionHandler(ErroAutenticacao.class)
    public ResponseEntity<RespostaErroPadraoDto> tratarErroAutenticacao(final ErroAutenticacao erro) {
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        respostaErroPadrao.setMensagem(erro.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(respostaErroPadrao);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RespostaErroPadraoDto> tratarAutenticacaoNaoLocalizada(
            final UsernameNotFoundException erro) {
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        respostaErroPadrao.setMensagem(erro.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(respostaErroPadrao);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RespostaErroPadraoDto> tratarAutenticacao(
            final BadCredentialsException erro) {
        final RespostaErroPadraoDto respostaErroPadrao = new RespostaErroPadraoDto();
        respostaErroPadrao.setClasse(erro.getClass().getSimpleName());
        respostaErroPadrao.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        respostaErroPadrao.setMensagem("Credenciáis inválidas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(respostaErroPadrao);
    }

}
