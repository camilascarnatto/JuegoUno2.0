package vista;

import controlador.Controlador;
import controlador.IVista;
import modelo.estadoJuego;
import modelo.EstadosVista;
import modelo.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola implements IVista {
	
	private Controlador controlador;
	private EstadosVista estado;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	
	public VistaConsola () {
		super();
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	
	public void comenzar() {
		do {
			mostrarMenu();
			
		}while (estado.name() != "TERMINADO");
	}
	
	private void mostrarMenu() {
		switch (estado) {
		case SETEANDO: 
			mostrarMenuSeteando();
			break;
		case TERMINADO:
			break;
		}
		
	}

	private void mostrarMenuSeteando() {
		Scanner scanner = new Scanner(System.in);
		String option = "";
		
		do {
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("-          JUEGO UNO           -");
			System.out.println("--------------------------------");
			System.out.println("- 1 -> Agregar jugador         -");
			System.out.println("- 2 -> Mostrar jugadores       -");
			System.out.println("- 3 -> Salir del juego         -");
			System.out.println("--------------------------------");
			System.out.println("- Su opcion es:    \n");
			option = scanner.nextLine();
		}while(!validarOpcionIngresada(option, "1", "2", "3"));
		
		switch (option) {
		case "1":
				if(controlador.cantidadJugadores() < 4) {
					controlador.agregarJugador(pedirNombre());
				}else
					System.out.println("\n Ya hay jugadores suficientes para iniciar el juego");
			break;
		case "2":
				jugadores = controlador.getJugadores();
				int nroJugador = 1;
				for(Jugador jugador : jugadores) {
					System.out.println("Jugador " + nroJugador + ": " + jugador.getNombre() );
					nroJugador++;
				}
			break;
		case "3":
				mostrarMenuFin();
			break;

		default:
				System.out.println("Ingrese una opcion correcta");
			break;
		}
	}
	
	
	private void mostrarMenuFin() {
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.println("-             JUEGO CHIN              -");
		System.out.println("---------------------------------------");
		System.out.println("-        FINALIZASTE EL JUEGO         -");
		System.out.println("---------------------------------------");
		System.out.println("");
		this.estado = estado.TERMINADO;
	}

	private String pedirNombre() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Ingrese su nombre: \n");
		String nombre = scanner.nextLine();
		return nombre;
	}

	/**
	 * Metodo que valida las opciones que ingresó el usuario 
	 */
	private boolean validarOpcionIngresada(String option, String ... valoresPosibles ) {
		boolean respuesta = false;
		for(String valor:valoresPosibles) {
			if(valor.equals(option)) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	@Override
	public void actualizarListaJugadores(Object jugadores) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void setEstadoVista(EstadosVista estado) {
		this.estado = estado;
	}

}
