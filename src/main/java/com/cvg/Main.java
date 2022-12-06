package com.cvg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.cvg.BuildDVService.calcularDigitoVerificador;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        String[] ruc = { "155707757-2-2021", "318251-1-412645" };
        for(String r: ruc) {
            logger.error( String.format("El DV para el RUC %s es %s", r, calcularDigitoVerificador(r) ));
        }
    }
}