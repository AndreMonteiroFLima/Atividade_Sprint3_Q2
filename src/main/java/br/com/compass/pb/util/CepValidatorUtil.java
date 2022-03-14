package br.com.compass.pb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepValidatorUtil {

    public static boolean isValid(String cep) {
        boolean result = false;
        if ( cep == null || "".equals(cep) ) {
            result = true;
        } else {
            Pattern pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");
            Matcher matcher = pattern.matcher(cep);
            result = matcher.find();
        }
        return result;
    }

}