package excepciones;

@SuppressWarnings("serial")
public class CantidadDeProductosInvalidoExcepcion extends Exception {

	public CantidadDeProductosInvalidoExcepcion(String msg) {
		super(msg);
	}

	public CantidadDeProductosInvalidoExcepcion() {
		super("La cantidad de productos en el combo debe ser mayor que cero.");
	}

}
