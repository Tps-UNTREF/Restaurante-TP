package Excepciones;

@SuppressWarnings("serial")
public class PrecioDeVentaInvalidoException extends Exception {
	
	public PrecioDeVentaInvalidoException(String msg) {
		super(msg);
	}

	public PrecioDeVentaInvalidoException() {
		super("El precio de venta debe ser siempre mayor al precio de costo.");
	}

}
