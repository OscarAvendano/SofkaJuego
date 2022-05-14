import dto.Jugador;
import dto.Pregunta;
import model.Juego;
import utilities.Conexion;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        jugador.setFecha(fechaActual);

        while (nivel <= 5){
            Pregunta pregunta = juego.obtenerPreguntasPorNivel(nivel);
            System.out.println(pregunta.getDescripcion());
            nivel++;
        }

    }
}
