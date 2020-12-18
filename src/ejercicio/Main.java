package ejercicio;
import com.google.gson.*;
import java.io.*;
import java.util.*;


public class Main {

    private Persona persona = new Persona(1, "Jose", 19, "Calle ter");
    private Persona persona2 = new Persona(2, "Dario", 19, "Calle t");
    private JsonParser jsonParser = new JsonParser();
    private File file = new File("src\\fichero\\ficheroJSON.json");
    FileReader lector = new FileReader(file);
    JsonElement element = jsonParser.parse(lector);

    public Main() throws IOException {
        readJSONFile(file);
    }

    private void writeJSONFile(List<?> objects, File fileJSON) throws IOException {
        /*1 - Pasamos los objetos a JSON (Metodo toJSON())
         * 2 - Lo metemos dentro de un archivo JSON*/

        FileWriter escritor = new FileWriter(fileJSON);
        Gson gson = new Gson();
        escritor.write("[");
        for (int i = 0; i < objects.size(); i++) {
            if (i == objects.size() - 1) {
                escritor.write(gson.toJson(objects.get(i)));
            } else {
                escritor.write(gson.toJson(objects.get(i)) + ",");
            }
        }
        escritor.write("]");
        escritor.close();
    }

    private void readJSONFile(JsonElement elemento) {
        /*Este ejemplo de comprobacion y lectura */

        if (elemento.isJsonObject()) {
            System.out.println("Es objeto");
            JsonObject obj = elemento.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
            Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                Map.Entry<String, JsonElement> entrada = iter.next();
                System.out.print("Clave: " + entrada.getKey() + ", ");
                readJSONFile(entrada.getValue());
            }
        } else if (elemento.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            System.out.println("Es array. Numero de elementos: " + array.size());
            Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                readJSONFile(entrada);
            }
        } else if (elemento.isJsonPrimitive()) {
            JsonPrimitive valor = elemento.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.println("Valor: " + valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.println("Valor: " + valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.println("Valor: " + valor.getAsString());
            }
        } else if (elemento.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }
    }


    private void readJSONFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Gson gson = new Gson();
        Persona[] personas=gson.fromJson(fileReader,Persona[].class);
        for (int i = 0; i < personas.length; i++) {
            System.out.println(personas[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();

    }


}

