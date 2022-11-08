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
			
		}while (estado != EstadosVista.TERMINADO);
	}
	
	private void mostrarMenu() {
		switch (estado) {
		case SETEANDO: 
			mostrarMenuSeteando();
			break;
		case TERMINADO:
			mostrarMenuSalir();
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
						// funcion que muestre la lista de jugadores
						mostrarJugadores();
						break;
					case 3:
						salir = true;
						salirJuego();
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
	
 
	private void mostrarMenuSalir() {
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.println("-             JUEGO UNO               -");
		System.out.println("---------------------------------------");
		System.out.println("-         SALISTE DEL JUEGO           -");
		System.out.println("---------------------------------------");
		System.out.println("");
		this.estado = EstadosVista.TERMINADO;
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
	/*private boolean validarOpcionIngresada(String option, String ... valoresPosibles ) {
		boolean respuesta = false;
		for(String valor:valoresPosibles) {
			if(valor.equals(option)) {
				respuesta = true;
			}
		}
		return respuesta;
	}*/

	@Override
	public void actualizarListaJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		mostrarMsj("\n SE HAN ACTUALIZADO LOS JUGADORES");
		
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

	@Override
	public void salirJuego() {
		mostrarMenuSalir();
		
	}
	
	public void mostrarJugadores() {
		jugadores = controlador.getJugadores();
		int nroJugador = 1;
		
		if (jugadores.isEmpty()){
			mostrarMsj("\n Todavia no se agregaron jugadores" );
		}else {
			
			System.out.println("LISTA DE JUGADORES: \n");
			
			for(Jugador jugador : jugadores) {
				System.out.println("Jugador [" + nroJugador + "] - " + jugador.getNombre() );
				nroJugador++;
			}
		}

	}

}
