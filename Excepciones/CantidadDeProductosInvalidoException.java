package Excepciones;

public class CantidadDeProductosInvalidoException extends Exception {

	@Override
	public String getMessage() {
		return "La cantidad de productos en el combo debe ser mayor que cero.";
	}
	
}
