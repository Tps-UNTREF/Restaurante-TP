package Excepciones;

@SuppressWarnings("serial")
public class ProductoActualizadoYaExistenteException extends Exception {

	@Override
	public String getMessage() {
		return "El producto actualizado que se intenta agregar en la lista ya existe en el menú.";
	}
	
}
