package ejercicio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Persona {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("edad")
    private int edad;
    @SerializedName("direccion")
    private String direccion;


    public Persona(int id, String name, int edad, String direccion) {
        this.name = name;
        this.edad = edad;
        this.direccion = direccion;
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ id: " + String.valueOf(id) + ", name: " + name + ", edad: " + String.valueOf(edad) + ", direccion: " + String.valueOf(direccion) + " ]";
    }
}
