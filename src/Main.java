import controlador.*;
import modelo.Juego;
import vista.VistaConsola;
import vista.VentanaPrincipal;

public class Main {
	
	public static void main(String[] args) {
		IVista vistaConsola = new VistaConsola();
		IVista ventanaPrincipal = new VentanaPrincipal();
		Juego juego = new Juego();
		Controlador controlador = new Controlador(ventanaPrincipal, juego);
		
		controlador.iniciar();
	}
}
