package database;

import dto.Jugador;
import dto.Pregunta;
import dto.Respuesta;
import utilities.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public Connection conectarSQL() {
        Conexion conexion = new Conexion();
        return conexion.getConnection();
    }

    public List<Pregunta> obtenerPreguntasPorNivel(int nivel) {
        List<Pregunta> listaPreguntas = new ArrayList<>();
        String query = "SELECT * FROM PREGUNTA WHERE nivel = ?";
        Connection connection = conectarSQL();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, nivel);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Pregunta pregunta = new Pregunta();
                    pregunta.setIdPregunta(resultSet.getInt(1));
                    pregunta.setNivel(resultSet.getInt(2));
                    pregunta.setDescripcion(resultSet.getString(3));
                    listaPreguntas.add(pregunta);
                }
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaPreguntas;
    }

    public List<Respuesta> obtenerRespuestaPorIdPregunta(int idPregunta) {
        List<Respuesta> listaRespuestas = new ArrayList<>();
        String query = "SELECT * FROM RESPUESTA WHERE idPreg = ?";
        Connection connection = conectarSQL();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idPregunta);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setIdRespuesta(resultSet.getInt(1));
                    respuesta.setDescripcion(resultSet.getString(2));
                    respuesta.setIdPregunta(resultSet.getInt(3));
                    respuesta.setRespuestaCorrecta(resultSet.getInt(4));
                    listaRespuestas.add(respuesta);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaRespuestas;
    }

    public int insertarJugador(Jugador jugador) {
        String query = "INSERT INTO JUGADOR (id, nombre, puntaje, fecha) VALUES (?,?,?,?)";
        Connection connection = conectarSQL();
        int respuesta = 0;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, jugador.getId());
                preparedStatement.setString(2, jugador.getNombre());
                preparedStatement.setInt(3, jugador.getPuntaje());
                preparedStatement.setDate(4, jugador.getFecha());
                respuesta = preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    public List<Jugador> obtenerHistoricoJugadores() {
        List<Jugador> listaJugadores = new ArrayList<>();
        String query = "SELECT * FROM JUGADOR ORDER BY puntaje DESC";
        Connection connection = conectarSQL();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Jugador jugador = new Jugador();
                    jugador.setId(resultSet.getInt(1));
                    jugador.setNombre(resultSet.getString(2));
                    jugador.setPuntaje(resultSet.getInt(3));
                    jugador.setFecha(resultSet.getDate(4));
                    listaJugadores.add(jugador);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaJugadores;
    }
}
