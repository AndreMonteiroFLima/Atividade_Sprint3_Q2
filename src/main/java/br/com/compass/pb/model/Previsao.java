package br.com.compass.pb.model;

import com.google.gson.Gson;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="previsao")
public class Previsao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int temp;
    private LocalDate data = LocalDate.now();
    private String time;
    private String description;
    private String city_name;
    @OneToMany
    List<PrevisoesFuturas> forecast = new ArrayList<>();

    public Previsao() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data.format(dateTimeFormatter);
    }

    public Previsao(int id, int temp, LocalDate date,String time, String description, String city_name, List<PrevisoesFuturas> previsoes) {
        this.id=id;
        this.temp = temp;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data.format(dateTimeFormatter);
        this.data = date;
        this.time=time;
        this.description = description;
        this.city_name = city_name;
        this.forecast = previsoes;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate date) {
        this.data = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<PrevisoesFuturas> getForecast() {
        return forecast;
    }

    public void setForecast(List<PrevisoesFuturas> forecast) {
        this.forecast = forecast;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return "Previsao{" +
                "temp=" + temp +
                ", date='" + data + '\'' +
                ", description='" + description + '\'' +
                ", city_name='" + city_name + '\'' +
                ", previsoes=" + forecast +
                '}';
    }
}
