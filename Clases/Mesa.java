package Clases;

import java.util.HashMap;
import java.util.Map;

public class Mesa {

	private Estados estado;
	private int numeroDeMesa;
	private Map<Producto, Integer> consumiciones = new HashMap<Producto, Integer>();
	
	public Estados getEstado() {
		return estado;
	}
	
	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}
	
	public Map<Producto, Integer> getListaDeConsumiciones() {
		return consumiciones;
	}
	
	public enum Estados {
		Disponible,
		Cerrada,
		Ocupada
	}
}
