package vista;

import controlador.Controlador;
import controlador.IVista;
import modelo.estadoJuego;
import modelo.EstadosVista;
import modelo.Jugador;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion; // se guarda la opcion del usuario
		
		while(!salir) {
			System.out.println(" ");
			System.out.println("1. Agregar jugador");
			System.out.println("2. Mostrar jugadores");
			System.out.println("3. Salir");
			System.out.println(" ");
			
			try {
				System.out.println("Ingrese una opcion");
				opcion = sn.nextInt();
				
				switch(opcion) {
					case 1: 
						controlador.agregarJugador(pedirNombre());
						break;
					case 2:
						jugadores = controlador.getJugadores();
						int nroJugador = 1;
						System.out.println("LISTA DE JUGADORES:");
						for(Jugador jugador : jugadores) {
							System.out.println("Jugador [" + nroJugador + "] - " + jugador.getNombre() );
							nroJugador++;
						}
						break;
					case 3:
						salir = true;
						mostrarMenuFin();
						break;
					default:
							System.out.println("Solo numeros entre 1 y 3");
						
				}
			} catch(InputMismatchException e) {
				System.out.println("Debe insertar un numero");
				sn.next();
			}
			
		}
	}
	
 /*
	private void mostrarMenuSeteando() {
		String option = "";
		Scanner scanner = null;
		try {
			 scanner = new Scanner(System.in);
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
			}while(scanner.hasNextLine() && !validarOpcionIngresada(option, "1", "2", "3"));
			
			switch (option) {
			case "1":
					controlador.agregarJugador(pedirNombre());
					
					
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
					mostrarMsj("Ingrese una opcion correcta");
				break;
			}
		}
		finally {
			if(scanner != null)
				scanner.close();
		}
		
		
	}
	
 */	
	
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
	public void actualizarListaJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		mostrarMsj("[Se han actualizado los jugadores]");
		
	}

	
	@Override
	public void setEstadoVista(EstadosVista estado) {
		this.estado = estado;
	}
	
	public void mostrarMsj(String message) {
		System.out.println(message);
	}

	@Override
	public void error(String error) {
		System.out.println(error);
		
	}

}
