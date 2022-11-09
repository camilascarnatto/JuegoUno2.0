package modelo;

import java.util.ArrayList;
import java.util.Random;

public class MazoUno /*extends Mazo<CartaUno>*/ {

	//private boolean sentido; // true = sentido agujas del reloj, false = sentido contrario 
	private ArrayList<CartaUno> cartas =  new ArrayList<>();
	private ArrayList<CartaUno> cartasDescartadas = new ArrayList<>();
	private int numCartas;
	private int cartaDisponibleMazo; //posision del tope del mazo
	private int cartaDisponibleDescartadas = -1;
	private int cartasPorPalo;
	private CartaUno ultimaCarta;
	private ColoresCartaUno colorActual;
	
    public MazoUno() {
        this.numCartas = 108;
        this.cartasPorPalo = 13;

        crearMazo();
        mezclar();
        
        //this.ultimaCarta = siguienteCarta(true); // true porque le estoy añadiendo la carta al monton
        //this.actualizarColor();
    }
    
    /**
     * Mezcla todas las cartas
     */
    
    
    public void crearMazo() {

    	ColoresCartaUno[] colores = ColoresCartaUno.values();

        for (ColoresCartaUno color : colores) {

            if (color != ColoresCartaUno.NEGRO) {

                for (int i = 0; i < this.cartasPorPalo; i++) {
                    if (i > 9) {
                    	//Creacion de cartas especiales
                        switch (i) {
                            case 10:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.MAS_DOS ,false));
                                break;
                            case 11:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.SALTO ,false));
                                break;
                            case 12:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.REVERSA ,false));
                                break;
                        }
                    } else {
                        this.cartas.add(new CartaUno(i, color ,false));
                    }
                }

                for (int i = 1; i < this.cartasPorPalo; i++) {
                    if (i > 9) {
                        switch (i) {
                            case 10:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.MAS_DOS ,false));
                                break;
                            case 11:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.SALTO ,false));
                                break;
                            case 12:
                                this.cartas.add(new CartaUno(color, EfectosCartaUno.REVERSA ,false));
                                break;
                        }
                    } else {
                        this.cartas.add(new CartaUno(i, color ,false));
                    }
                }

            } else {
            	//8 cartas negras especiales (4 cartas +4 y 4 cartas de cambioColor
                for (int i = 0; i < 4; i++) {
                    this.cartas.add(new CartaUno(color, EfectosCartaUno.MAS_CUATRO ,false));
                    this.cartas.add(new CartaUno(color, EfectosCartaUno.CAMBIO_COLOR ,false));
                }

            }

        }

    }
    
    public void mezclar() {
    	for (int i = 0; i < cartas.size();i++) {
			Random ran = new Random();
			int x = ran.nextInt(cartas.size());
			CartaUno cartaAux;
			cartaAux = cartas.get(i);
			cartas.set(i, cartas.get(x));
			cartas.set(x,cartaAux);
		}

    }
    
    public CartaUno getCartaMazo(boolean visible) {
		CartaUno carta;
		if (cartaDisponibleMazo != cartas.size()) { //12 != 12
			carta = cartas.get(cartaDisponibleMazo++).duplicar();
			carta.setVisible(visible);
		} else 
			if (cartaDisponibleDescartadas != 0) {
			carta = cartasDescartadas.get(0).duplicar();
			cartasDescartadas.remove(carta);
			carta.setVisible(visible);
			cartaDisponibleDescartadas--;
			} else
				carta = null;				
		return carta;
	}
    
    public CartaUno getCartaDescartadas() {
		CartaUno carta = null;
		if (cartaDisponibleDescartadas >= 0) {
			CartaUno cartaLevantada = cartasDescartadas.get(cartaDisponibleDescartadas);
			carta = cartaLevantada.duplicar();
			cartaDisponibleDescartadas--;
			cartasDescartadas.remove(cartaLevantada);
			carta.setVisible(false);
		}
		return carta;
	}
    
    /*
     ES COSA DEL JUEGO EL SENTIDO, NO DEL MAZO
    public boolean isSentido() {
    	return sentido;
    }
    
    public void cambiarSentido() {
    	this.sentido = !this.sentido; //cambia de true a falso y viceversa
    }
    */

    public void descartarCarta(CartaUno cartaDescartada) {
		cartaDescartada.setVisible(true);
		cartasDescartadas.add(cartaDescartada);
		cartaDisponibleDescartadas++;
		
	}
    
    
    public CartaUno getUltimaCarta() {
    	return ultimaCarta;
    }
    
    public void setUltimaCarta(CartaUno ultimaCarta) {
    	this.ultimaCarta = ultimaCarta;
    }
    
    public ColoresCartaUno getColorActual() {
    	return colorActual;
    }
    
    public void setColorActual(ColoresCartaUno colorActual) {
        this.colorActual = colorActual;
    }
    
    public void actualizarColor() {
    	this.colorActual = this.ultimaCarta.getPalo();
    }
    
    public void mostrarMazo() {

        if (cartas.size() == 0) {
            System.out.println("No hay cartas que mostrar");
        } else {
            System.out.println(this.cartas.toString());
        }

    }
   
	

}