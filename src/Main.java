import dto.Jugador;
import dto.Pregunta;
import dto.Respuesta;
import model.Juego;
import utilities.Conexion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        iniciarJuego();
    }

    public static void iniciarJuego() {
        Juego juego = new Juego();
        Scanner scanner = new Scanner(System.in);
        Jugador jugador = new Jugador();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = dateFormat.format(date);
        Date fechaActual = new Date(fechaTexto);
        int puntos = 0;
        int nivel = 1;

        System.out.println("Bienvenido al juego");
        System.out.println("Ingrese número de identificación");
        jugador.setId(scanner.nextInt());
        System.out.println("Ingrese el nombre del jugador");
        jugador.setNombre(scanner.nextLine());
        jugador.setNombre(scanner.nextLine());
        jugador.setFecha(new java.sql.Date(fechaActual.getTime()));

        while (nivel <= 5) {
            Pregunta pregunta = juego.obtenerPreguntasPorNivel(nivel);
            System.out.println(pregunta.getDescripcion());
            List<Respuesta> respuestas = juego.obtenerRespuestasPorIdPregunta(pregunta.getIdPregunta());
            int secuenciaRespuestas = 1;
            int respuestaCorrecta = 0;
            for (Respuesta respuesta : respuestas) {
                System.out.println(secuenciaRespuestas + " " + respuesta.getDescripcion());
                if (respuesta.getRespuestaCorrecta() == 1) {
                    respuestaCorrecta = secuenciaRespuestas;
                }
                secuenciaRespuestas++;
            }
            System.out.println("Digite el número de la respuesta correcta: ");
            int respuestaUsuario = scanner.nextInt();
            if (respuestaUsuario == respuestaCorrecta) {
                puntos = puntos + 100 * nivel;
                System.out.println("Respuesta correcta, su puntaje ahora es de " + puntos);
                if (puntos < 1500) {
                    System.out.println("Digite 1 si desea continuar ó 0 para retirarse con el puntaje actual: ");
                    int opcionContinuar = scanner.nextInt();
                    if (opcionContinuar == 0) {
                        System.out.println("Has abandonado, el puntaje obtenido es " + puntos);
                        break;
                    }
                }
            } else {
                System.out.println("Respuesta incorrecta, el juego ha terminado");
                break;
            }
            nivel++;
        }
        if (nivel == 6) {
            System.out.println("Has llegado el final del juego, ha alcanzado el premio mayor");
        }
        jugador.setPuntaje(puntos);
        String respuestaInsercion = juego.insertarJugador(jugador);
        System.out.println(respuestaInsercion);
        List<Jugador> jugadorLista = juego.obtenerHistoricoJugadores();
        System.out.println("Este es el historico de jugadores");
        for (Jugador jugadorHistorico : jugadorLista ){
            System.out.println(jugadorHistorico.getNombre() + " | " + jugadorHistorico.getFecha() + " | " + jugadorHistorico.getPuntaje() );
        }
    }
}
