package modelo;

public abstract class Carta<T> {

    //Atributos
    protected int numero;
    protected T palo;
    protected boolean visible;

    public int getNumero() {
        return numero;
    }

    public T getPalo() {
        return palo;
    }

    //Constructor
    public Carta(int numero, T palo, boolean visible) {
        this.numero = numero;
        this.palo = palo;
        this.visible = visible;
    }

    public Carta() {
    
    }
    
    
    public void setVisible(boolean visible) {
		this.visible = visible;
	}
}