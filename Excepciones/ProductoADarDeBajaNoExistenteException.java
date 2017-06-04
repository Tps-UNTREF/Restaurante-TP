package Excepciones;

@SuppressWarnings("serial")
public class ProductoADarDeBajaNoExistenteException extends Exception {

	@Override
	public String getMessage() {
		return "El producto a dar de baja no existe en el menú.";
	}
	
}
