package modelo;

public enum EfectosCartaUno {
	MAS_DOS(20),
	MAS_CUATRO(50),
	REVERSA(20),
	SALTO(20),
	CAMBIO_COLOR(50);
	
	private int puntos;
	
	private EfectosCartaUno(int puntos) {
		this.puntos = puntos;
	}
	
	public int getPuntos() {
		return puntos;
	}
}
