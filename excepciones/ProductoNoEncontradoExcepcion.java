package excepciones;

@SuppressWarnings("serial")
public class ProductoNoEncontradoExcepcion extends Exception{
	
	public ProductoNoEncontradoExcepcion(String msg) {
		super(msg);
	}

	public ProductoNoEncontradoExcepcion() {
		super("Producto no encontrado en la lista de precios");
	}
	
}
