package database;

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

}
