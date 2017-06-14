package excepciones;

@SuppressWarnings("serial")
public class PrecioDeCostoInvalidoExcepcion extends Exception {
	
	public PrecioDeCostoInvalidoExcepcion(String msg) {
		super(msg);
	}
	
	public PrecioDeCostoInvalidoExcepcion() {
		super("El precio de costo debe ser mayor a cero.");
	}
	
}
