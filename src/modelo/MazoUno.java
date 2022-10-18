package modelo;

public class MazoUno extends Mazo<CartaUno> {

	private boolean sentido; // true = sentido agujas del reloj, false = sentido contrario 
	private CartaUno ultimaCarta;
	private ColoresCartaUno colorActual;
	
    public MazoUno() {
        this.numCartas = 108;
        this.cartasPorPalo = 13;
        this.sentido = true;

        this.crearMazo();
        super.mezclar();
        
        this.ultimaCarta = super.siguienteCarta(true); // true porque le estoy añadiendo la carta al monton
        this.actualizarColor();
    }

    @Override
    public void crearMazo() {

    	ColoresCartaUno[] colores = ColoresCartaUno.values();

        for (ColoresCartaUno color : colores) {

            if (color != ColoresCartaUno.NEGRO) {

                for (int i = 0; i < this.cartasPorPalo; i++) {
                    if (i > 9) {
                    	//Creacion de cartas especiales
                        switch (i) {
                            case 10:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.MAS_DOS));
                                break;
                            case 11:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.SALTO));
                                break;
                            case 12:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.REVERSA));
                                break;
                        }
                    } else {
                        this.cartas.push(new CartaUno(i, color));
                    }
                }

                for (int i = 1; i < this.cartasPorPalo; i++) {
                    if (i > 9) {
                        switch (i) {
                            case 10:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.MAS_DOS));
                                break;
                            case 11:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.SALTO));
                                break;
                            case 12:
                                this.cartas.push(new CartaUno(color, EfectosCartaUno.REVERSA));
                                break;
                        }
                    } else {
                        this.cartas.push(new CartaUno(i, color));
                    }
                }

            } else {
            	//8 cartas negras especiales (4 cartas +4 y 4 cartas de cambioColor
                for (int i = 0; i < 4; i++) {
                    this.cartas.push(new CartaUno(color, EfectosCartaUno.MAS_CUATRO));
                    this.cartas.push(new CartaUno(color, EfectosCartaUno.CAMBIO_COLOR));
                }

            }

        }

    }
    
    public boolean isSentido() {
    	return sentido;
    }
    
    public void cambiarSentido() {
    	this.sentido = !this.sentido; //cambia de true a falso y viceversa
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

	

}