package Excepciones;

@SuppressWarnings("serial")
public class CantidadDeProductosInvalidoException extends Exception {

	public CantidadDeProductosInvalidoException(String msg) {
		super(msg);
	}

	public CantidadDeProductosInvalidoException() {
		super("La cantidad de productos en el combo debe ser mayor que cero.");
	}

}
