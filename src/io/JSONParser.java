/**
package io;

/**
 *
 * @author Willy Bazan
 * @date   05/12/2019
 * @time   02:31 pm
 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Constants.Constant;

public class JSONParser 
{
    //Este método lee los datos de los archivosque estan en el disco
    public static ArrayList<ScoreData> readFile() throws FileNotFoundException
    {
        //Lista donde se guardan todos los objetos
        ArrayList<ScoreData> dataList = new ArrayList<ScoreData>();

        //Se crea el archivo
        File file = new File(Constant.SCORE_PATH);

        //Si el archivo no existe o que no tiene ningun dato
        if(!file.exists() || file.length() == 0) {
            //Se retorna la lista vacia que se acaba de crear    
            return dataList;
        }
        
        //Esto se puede revisar en la API
        JSONTokener parser = new JSONTokener(new FileInputStream(file));
        
        //Obtener la lista
        JSONArray jsonList = new JSONArray(parser);

        for(int i = 0; i < jsonList.length(); i++) {
            JSONObject obj = (JSONObject)jsonList.get(i);
            ScoreData data = new ScoreData(); //Este es el motivo del constructor vacio en ScoreData
            data.setScore(obj.getInt("score")); //El score es la clave o llave 
            data.setDate(obj.getString("date")); //Al igual que date para leer los datos
            dataList.add(data);
        }

        return dataList;
    }
	
    //Para escribir en el archivo
    public static void writeFile(ArrayList<ScoreData> dataList) throws IOException 
    {
        //Archivo al que se va ha escribir
        File outputFile = new File(Constant.SCORE_PATH);

        //para crear la carpeta donde se va estar el archivo
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();

        //Como se tiene un arreglo entonces se necesita otro
        JSONArray jsonList = new JSONArray();

        for(ScoreData data: dataList)
        {
            //Se crea un objeto de formato JSON
            JSONObject obj = new JSONObject();
            obj.put("score", data.getScore()); //Para poner el puntaje
            obj.put("date", data.getDate());   //Para poner la fecha

            jsonList.put(obj); //Lista de objetos
        }

        //Revisar entrada y salida de datos
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile.toURI()));
        jsonList.write(writer);
        writer.close();

    }
	
}

*/