package excepciones;

@SuppressWarnings("serial")
public class ProductoADarDeBajaNoExistenteExcepcion extends Exception {

	public ProductoADarDeBajaNoExistenteExcepcion(String msg) {
		super(msg);
	}

	public ProductoADarDeBajaNoExistenteExcepcion() {
		super("El producto a dar de baja no existe en el menú.");
	}
	
}
