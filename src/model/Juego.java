package model;

import database.DataBase;
import dto.Jugador;
import dto.Pregunta;
import dto.Respuesta;

import java.util.List;
import java.util.Random;

public class Juego {
    private DataBase dataBase;

    public Juego() {
        dataBase = new DataBase();
    }

    public Pregunta obtenerPreguntasPorNivel(int nivel) {
        List<Pregunta> preguntas = dataBase.obtenerPreguntasPorNivel(nivel);
        Random random = new Random();
        int index = random.nextInt(4);
        return preguntas.get(index);
    }

    public List<Respuesta> obtenerRespuestasPorIdPregunta(int idPregunta) {
        return dataBase.obtenerRespuestaPorIdPregunta(idPregunta);
    }

    public String insertarJugador(Jugador jugador) {
        int respuesta = dataBase.insertarJugador(jugador);
        String respuestaInsercion;
        if (respuesta == 1) {
            respuestaInsercion = "Los datos del jugador han sido almacenados";
        } else {
            respuestaInsercion = "No fue posible almacenar los datos del jugador";
        }
        return respuestaInsercion;
    }
    public List<Jugador> obtenerHistoricoJugadores (){
        return dataBase.obtenerHistoricoJugadores();
    }
}
