package Excepciones;

@SuppressWarnings("serial")
public class ProductoAActualizarNoExistenteException extends Exception {
	
	@Override
	public String getMessage() {
		return "El producto a actualizar no existe en el menú.";
	}

}
