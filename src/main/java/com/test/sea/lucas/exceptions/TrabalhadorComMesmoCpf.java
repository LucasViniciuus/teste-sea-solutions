package com.test.sea.lucas.exceptions;

import org.springframework.http.HttpStatus;

public class TrabalhadorComMesmoCpf extends Exception{
    public TrabalhadorComMesmoCpf(HttpStatus badRequest) {
        super();
    }
    public TrabalhadorComMesmoCpf(String mensagem) {
        super(mensagem);
    }

}
