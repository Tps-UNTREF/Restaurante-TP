package Excepciones;

@SuppressWarnings("serial")
public class ProductoNoEncontradoException extends Exception{
	
	public ProductoNoEncontradoException(String msg) {
		super(msg);
	}

	public ProductoNoEncontradoException() {
		super("Producto no encontrado en la lista de precios");
	}
	
}
