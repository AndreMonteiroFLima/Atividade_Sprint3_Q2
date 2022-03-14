package br.com.compass.pb.model;

import javax.persistence.*;

@Entity
@Table(name="previsoesfuturas")
public class PrevisoesFuturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private int max;
    private int min;
    private String description;

    public PrevisoesFuturas() {
    }

    public PrevisoesFuturas(int id, String date, int max, int min, String description) {
        this.id=id;
        this.date = date;
        this.max = max;
        this.min = min;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PrevisoesFuturas{" +
                "date='" + date + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", description='" + description + '\'' +
                '}';
    }
}
