import controlador.*;
import modelo.Juego;
import vista.VistaConsola;

public class Main {
	
	public static void main(String[] args) {
		IVista vista = new VistaConsola();
		Juego juego = new Juego();
		Controlador controlador = new Controlador(vista, juego);
		
		//vista.setControlador(controlador);
		controlador.iniciar();
	}
}
