package modelo;

import java.util.ArrayList;

public class Jugador {
	
	private String nombre;
	private int puntaje;
	private estadoJugador estado;
	private ArrayList<CartaUno> cartas;
	private boolean enTurno = false;
	private boolean levanto = false;
	private boolean tiro = false;
	
	public Jugador(String nombre) {
		this(nombre, 0, new ArrayList<>(), estadoJugador.INICIAL, false, false, false);
	}
	
	public Jugador (String nombre, int puntaje, ArrayList<CartaUno> cartas, estadoJugador estado, boolean enTurno, boolean levanto, boolean tiro) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.cartas = cartas;
		this.estado = estado;
		this.enTurno = enTurno;
		this.levanto = levanto;
		this.tiro = tiro;
	}
	
	public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntaje;
    }
    
    public ArrayList<CartaUno> getCartas(){
    	ArrayList<CartaUno> arrayAux = new ArrayList<>();
    	for( CartaUno carta: cartas) {
    		arrayAux.add(carta);
    	}
    	return arrayAux;
    }
    
    public int cantidadCartas() {
    	return cartas.size();
    }

    public String getEstado() {
    	return estado.toString();
    }
    
    public void aumentarPuntos(int puntos) {
        this.puntaje += puntos;
    }
    
    public CartaUno getCarta(int posCarta) {
    	return this.cartas.get(posCarta);
    }
    
    public void removeCarta(int posCarta) {
    	this.cartas.remove(posCarta);
    }
   
    public boolean sinCartas() {
    	return this.cartas.isEmpty();
    }
    
    public int puntosDeLaMano() {
    	int puntos = 0;
    	for(CartaUno carta : this.cartas) {
    		if(carta.isEspecial()) {
    			puntos += carta.getEfecto().getPuntos();
    		}else
    			puntos += carta.getNumero();
    	}
    	return puntos;
    }

    /*
     * Agregar mas de una carta
     */    
    public void agregarCartas(ArrayList<CartaUno> cartas) {
    	for(CartaUno carta: cartas) {
    		this.cartas.add(carta);
    	}
    }
    
    /**
     * Agregar una sola carta
     */
    public void agregarCartas(CartaUno carta) {
    	this.cartas.add(carta);
    }

	public Jugador duplicar() {
		ArrayList<CartaUno> cartasDuplicadas = new ArrayList<>();
		for (CartaUno carta:cartas) {
			cartasDuplicadas.add(carta.duplicar());
		}
		Jugador jugadorDuplicado = new Jugador(this.nombre, this.puntaje, cartasDuplicadas, this.estado, this.enTurno, this.levanto, this.tiro);
		return jugadorDuplicado;
	}
	
	public boolean yaLevanto() {
		return levanto;
	}
	
	public void setLevanto(boolean levanto) {
		this.levanto = levanto;
	}
	
	public boolean yaTiro() {
		return tiro;
	}
	public void setTiro(boolean tiro) {
		this.tiro = tiro;
	}
	
	public void setEnTurno(boolean enTurno) {
		this.enTurno = enTurno;
	}
	
	public boolean enTurno() {
		return enTurno;
	}
	
	public boolean isJugando() {
		return estado == estadoJugador.JUGANDO;
	}
	
	
    
}
