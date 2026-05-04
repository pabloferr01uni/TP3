package com.example.demo.errores;

public class RatingInvalido extends RuntimeException {
    public RatingInvalido (double valorRecibido){
        super("El rating "+valorRecibido+" no es valido. Debe estar entre 0.0 y 5.0.");
    }
}
