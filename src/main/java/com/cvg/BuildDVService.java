package com.cvg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BuildDVService {
    private static final Logger logger = LogManager.getLogger(BuildDVService.class);
    private static Map<String, String> table;

    private BuildDVService(){}

    static {
        table = new HashMap<>();
        table.put("00", "00");

        int c = 1;
        for (int i = 10; i <= 49; i++) {
            if (i == 26) c = 2;
            if (i == 23) c = 7;
            table.put( String.valueOf(i),  "0"+c);
            c++;
            if ( c == 10 ) c = 1;
        }
        logger.error("Building table");
    }

    private static Integer digitDV(boolean sw, String ructb){
        Integer j = 2;
        Integer suma = 0;

        for (char c : reversed(ructb).toCharArray()) {
            if ( sw && j == 12 ){
                sw = false;
                j -= 1;
            }

            suma += j * ( c - '0' );
            j += 1;
        }
        Integer resultado = suma % 11;
        if (resultado > 1) return 11 - resultado;
        return 0;
    }

    public static String calcularDigitoVerificador(String ruc) {
        String ructb = "";
        String[] split = ruc.split("-");

        boolean sw = false;
        ructb = appendZero(10 - split[0].length()) + split[0] +
                appendZero(4 - split[1].length()) + split[1] +
                appendZero(6 - split[2].length()) + split[2];
        sw = ructb.charAt(0) == '0' && ructb.charAt(4) == '0' && ructb.charAt(5) < '5';

        Integer dv1 = digitDV(sw, ructb);
        Integer dv2 = digitDV(sw, ( ructb + ( (char)(48 + dv1)) ));

        return dv1 + "" + dv2;
    }

    private static String reversed(String ruc){
        StringBuilder sb = new StringBuilder();
        sb.append(ruc);
        return sb.reverse().toString();
    }

    private static String appendZero(int operation){
        StringBuilder zero = new StringBuilder();
        for(int i = 0; i < operation; i++) zero.append("0");
        return zero.toString();
    }
}
