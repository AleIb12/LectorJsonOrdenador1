package es.upgrade;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
class Ordenador {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("precio")
    private int precio;
    @SerializedName("procesador")
    private List<Procesador> procesador = new ArrayList<>();
    @SerializedName("PlacaBase")
    private List<PlacaBase> placaBase = new ArrayList<>();
    @SerializedName("Ram")
    private List<Ram> ram = new ArrayList<>();
    @SerializedName("DiscoDuro")
    private List<DiscoDuro> discoDuro = new ArrayList<>();
    @SerializedName("FuenteAlimentacion")
    private List<FuenteAlimentacion> fuenteAlimentacion = new ArrayList<>();
    @SerializedName("TarjetaGrafica")
    private List<TarjetaGrafica> tarjetaGrafica = new ArrayList<>();
    @SerializedName("Perifericos")
    private List<Periferico> perifericos = new ArrayList<>();
}

@Data
class Procesador {
    @SerializedName("marca")
    private String marca;
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("hz")
    private int hz;
}

@Data
class PlacaBase {
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("marca")
    private String marca;
    @SerializedName("slotsRam")
    private int slotsRam;
}

@Data
class Ram {
    @SerializedName("marca")
    private String marca;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("capacidad")
    private String capacidad;
    @SerializedName("hz")
    private int hz;
    @SerializedName("cl")
    private int cl;
}

@Data
class DiscoDuro {
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("capacidad")
    private String capacidad;
    @SerializedName("marca")
    private String marca;
}

@Data
class FuenteAlimentacion {
    @SerializedName("marca")
    private String marca;
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("Potencia")
    private int potencia;
}

@Data
class TarjetaGrafica {
    @SerializedName("marca")
    private String marca;
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("Cudas")    // Changed from "cudas" to "Cudas" to match JSON
    private int cudas;
}

@Data
class Periferico {
    @SerializedName("Teclado")    // Changed from "teclado" to "Teclado"
    private Teclado teclado;
    @SerializedName("Raton")      // Changed from "raton" to "Raton"
    private Raton raton;
    @SerializedName("Monitor")    // Changed from "monitor" to "Monitor"
    private Monitor monitor;
}

@Data
class Teclado {
    @SerializedName("marca")
    private String marca;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("tipo")
    private String tipo;
}

@Data
class Raton {
    @SerializedName("marca")
    private String marca;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("tipo")
    private String tipo;
}

@Data
class Monitor {
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("marca")
    private String marca;
    @SerializedName("nombre")
    private String nombre;
}
