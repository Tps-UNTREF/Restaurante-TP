package excepciones;

@SuppressWarnings("serial")
public class PrecioDeVentaInvalidoExcepcion extends Exception {
	
	public PrecioDeVentaInvalidoExcepcion(String msg) {
		super(msg);
	}

	public PrecioDeVentaInvalidoExcepcion() {
		super("El precio de venta debe ser siempre mayor al precio de costo.");
	}

}
