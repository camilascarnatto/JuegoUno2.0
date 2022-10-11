package modelo;

import java.util.ArrayList;

public class Jugador {
	
	private String nombre;
	private int puntaje;
	private int nroJugador = -1;
	//private ArrayList<Carta> cartas;
	
	public Jugador (String nombre, int nroJugador) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.nroJugador = nroJugador;
		//this.puntaje = new ArrayList<>();
	}
	
	public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntaje;
    }

    public void aumentarPuntos(int puntos) {
        this.puntaje += puntos;
    }
    
   /* public int numCartas() {
        return this.cartas.size();
    }
   */

}
