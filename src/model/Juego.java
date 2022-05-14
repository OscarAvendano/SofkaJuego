package model;

import database.DataBase;
import dto.Pregunta;

import java.util.List;
import java.util.Random;

public class Juego {
    private DataBase dataBase;

    public Juego() {
        dataBase = new DataBase();
    }

    public Pregunta obtenerPreguntasPorNivel(int nivel){
        List<Pregunta> preguntas = dataBase.obtenerPreguntasPorNivel(nivel);
        Random random = new Random();
        int index = random.nextInt(4);
        return preguntas.get(index);
    }
}
