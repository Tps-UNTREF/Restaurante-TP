package Excepciones;

@SuppressWarnings("serial")
public class DescuentoInvalidoException extends Exception {

	public DescuentoInvalidoException(String msg) {
		super(msg);
	}

	public DescuentoInvalidoException() {
		super("El descuento debe estar entre 0 y 100.");
	}

}
