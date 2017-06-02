package Excepciones;

@SuppressWarnings("serial")
public class DescuentoInvalidoException extends Exception {

	@Override
	public String getMessage() {
		return "El descuento debe estar entre 0 y 100.";
	}
	
}
