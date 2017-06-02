package Excepciones;

@SuppressWarnings("serial")
public class ProductoNoEncontradoException extends Exception{
	public ProductoNoEncontradoException(String msg) {
		super(msg);
	}
}
