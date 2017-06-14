package excepciones;

@SuppressWarnings("serial")
public class DescuentoInvalidoExcepcion extends Exception {

	public DescuentoInvalidoExcepcion(String msg) {
		super(msg);
	}

	public DescuentoInvalidoExcepcion() {
		super("El descuento debe estar entre 0 y 100.");
	}

}
