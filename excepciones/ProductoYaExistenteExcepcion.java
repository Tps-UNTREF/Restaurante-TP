package excepciones;

@SuppressWarnings("serial")
public class ProductoYaExistenteExcepcion extends Exception {

	public ProductoYaExistenteExcepcion(String msg) {
		super(msg);
	}

	public ProductoYaExistenteExcepcion() {
		super("El producto actualizado que se intenta agregar en la lista ya existe en el menú.");
	}
	
}
