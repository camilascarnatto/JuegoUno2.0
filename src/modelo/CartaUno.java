package modelo;


public class CartaUno extends Carta<ColoresCartaUno> {
	
    private EfectosCartaUno efecto;

    public CartaUno(int numero, ColoresCartaUno color) {
        super(numero, color);
    }

    public CartaUno() {
    }

    public CartaUno(ColoresCartaUno color, EfectosCartaUno efecto) {
        super(-1, color); // -1 para las que ya tienen un efecto
        this.efecto = efecto;
    }

    public EfectosCartaUno getEfecto() {
        return efecto;
    }

    public boolean isEspecial() {
        return this.efecto != null;
    }
    
    /**
     * saber si la carta a agregar (c) es compatible con la que està en el tope (this)
     * @param c
     * @return
     */
    public boolean compatible(CartaUno c) {
    	return this.getPalo() == ColoresCartaUno.NEGRO
    			|| this.getPalo() == c.getPalo()
    			|| (this.getNumero() == c.getNumero() && !this.isEspecial() && !c.isEspecial())
    			|| (this.isEspecial() && c.isEspecial() && this.efecto == c.efecto);
    }
    
    @Override
    public String toString() {

        String estado = "";

        if (this.isEspecial()) {
            switch (this.efecto) {
                case MAS_DOS:
                    estado = "+2 " + palo;
                    break;
                case MAS_CUATRO:
                    estado = "+4"; // no tiene color porque es negra
                    break;
                case SALTO:
                    estado = "SALTEAR " + palo;
                    break;
                case REVERSA:
                    estado = "REVERSO " + palo;
                    break;
                case CAMBIO_COLOR:
                    estado = "CAMBIO COLOR";
                    break;
            }
        } else {
            estado = numero + " " + palo;
        }

        return estado;
    }

	public CartaUno duplicar() {
		CartaUno cartaDuplicada = new CartaUno(super.numero, super.palo);
		return cartaDuplicada;
	}

}
