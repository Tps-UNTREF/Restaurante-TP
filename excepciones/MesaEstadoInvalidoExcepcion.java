package excepciones;

@SuppressWarnings("serial")
public class MesaEstadoInvalidoExcepcion extends Exception {

	public MesaEstadoInvalidoExcepcion(String msg) {
		super(msg);
	}

	public MesaEstadoInvalidoExcepcion() {
		super("La mesa tiene que estar ocupada");
	}

}
