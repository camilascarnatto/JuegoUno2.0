package modelo_controlador;

import controlador.Controlador;

public interface IObservador {
	/* void actualizar(IObservadorRemoto observble, Object cambio) throws RemoteException; Asi es la de RMI*/
	void actualizar(posiblesCambios cambio);
}
