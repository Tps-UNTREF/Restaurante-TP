package Excepciones;

@SuppressWarnings("serial")
public class MesaNoDisponibleExcepcion extends Exception {
	
	public MesaNoDisponibleExcepcion(String msg) {
		super(msg);
	}

	public MesaNoDisponibleExcepcion() {
		super("La mesa tiene que estar disponible para cambiar de estado");
	}
}
