package excepciones;

@SuppressWarnings("serial")
public class MesaNoOcupadaExcepcion extends Exception {

	public MesaNoOcupadaExcepcion(String msg) {
		super(msg);
	}

	public MesaNoOcupadaExcepcion() {
		super("La mesa ya se encuentra en ese estado");
	}

}
