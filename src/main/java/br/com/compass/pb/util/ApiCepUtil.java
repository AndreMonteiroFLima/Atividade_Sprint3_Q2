package br.com.compass.pb.util;

import br.com.compass.pb.model.Endereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCepUtil {

    public static String pegaEnderecoPeloCep(String CEP){
        final String FORMATO = ".json";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ws.apicep.com/cep/"+CEP+FORMATO))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response.body().toString());
        return response.body().toString();
    }
}
