package Excepciones;

public class PrecioDeVentaInvalidoException extends Exception {
	
	@Override
	public String getMessage() {
		return "El precio de venta debe ser siempre mayor al precio de costo.";
	}

}
