package org.serratec.projetoapi.exception;

public class ErroResposta extends RuntimeException {
    public ErroResposta(String message) {
        super(message);
    }
}
