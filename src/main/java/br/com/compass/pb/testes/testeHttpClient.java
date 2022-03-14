package br.com.compass.pb.testes;

import br.com.compass.pb.model.Endereco;
import com.google.gson.*;
import br.com.compass.pb.model.Previsao;
import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class testeHttpClient {
    private static final String CHAVEOpenWeather= "76747dc54c9ae88770d3d5b55b433736";
    private static final String ChaveVisualCrossing = "47DUJJEBT25DR5WSMVV2ZGUAP";
    private  static final  String ChaveHGBrasil = "632100aa";



    public static void main(String[] args) {
        Date dt = new Date();
        DateTime teste = new DateTime(dt);
        System.out.println(teste.toLocalDate());
        LocalDate dataLocal = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(dataLocal);
        LocalDate dataLocal5dias = LocalDate.from(dt.toInstant().atZone(ZoneId.systemDefault())).plusDays(5);
        System.out.println(dataLocal5dias);


        String lugadouro=null;
        lugadouro="Vitória ES";
        lugadouro=deAccent(lugadouro);
        System.out.println(lugadouro);
        //previsaoUtil(lugadouro);
        previsaoUtilHg(lugadouro);
        pegaEnderecoPeloCep();
    }



    public static void previsaoUtilVisualCrossing(String lugadouro){
        //Contador de requests
        String tipoResposta = "json";
        lugadouro="Vitória_ES";
        lugadouro=deAccent(lugadouro);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        +lugadouro+"?unitGroup=metric"
                        +"&key="+ChaveVisualCrossing+"&lang=pt&include=days,current"+"&elements=date,time,tempmax,tempmin,temp,feelslike,precipprob,conditions,description"+"&contentType="+tipoResposta))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());

    }

    public static void previsaoUtilHg(String lugadouro){
        //Contador de requests
        String tipoResposta = "json-cors";
        lugadouro="Vitória,ES";
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
        String testeJson = response.body().toString();
        parseJsonTeste(testeJson);
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }


    public static void parseJsonTeste(String jsonTest){
        Previsao previsao = (Previsao) new Gson().fromJson(jsonTest,Previsao.class);
        System.out.println(previsao);
    }


    public static void pegaEnderecoPeloCep(){
        //ApiCEP
        String CEP = "29018600";
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
        Endereco endereco = (Endereco) new Gson().fromJson(response.body().toString(),Endereco.class);
        System.out.println(endereco);
    }


}
