package Excepciones;

@SuppressWarnings("serial")
public class MesasYaGeneradasExcepcion extends Exception {
	
	public MesasYaGeneradasExcepcion(String msg) {
		super(msg);
	}

	public MesasYaGeneradasExcepcion() {
		super("Ya se generaron las mesas");
	}

}
