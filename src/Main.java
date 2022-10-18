import controlador.*;
import vista.VistaConsola;

public class Main {
	
	public static void main(String[] args) {
		IVista vista = new VistaConsola();
		Controlador controlador = new Controlador(vista);
	}
}
