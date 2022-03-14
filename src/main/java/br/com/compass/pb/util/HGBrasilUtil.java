package br.com.compass.pb.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class HGBrasilUtil {
    private  static final  String ChaveHGBrasil = "632100aa";

    public static String previsaoUtilHg(String lugadouro){
        //Contador de requests
        String tipoResposta = "json-cors";

        lugadouro=deAccent(lugadouro);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.hgbrasil.com/weather?"+"array_limit="+5+"&fields=only_results,temp,description,city_name,forecast,date,max,min,description"+"&format="+tipoResposta+"&city_name="+lugadouro+"&key="+ChaveHGBrasil))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response.body().toString();
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
