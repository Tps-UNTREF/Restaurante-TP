package Excepciones;

@SuppressWarnings("serial")
public class PrecioDeCostoInvalidoException extends Exception {

	@Override
	public String getMessage() {
		return "El precio de costo debe ser mayor a cero.";
	}
	
}
