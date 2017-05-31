package Clases;

import java.util.HashMap;
import java.util.Map;

public class Mesa {

	private Estados estado;
	private int numeroDeMesa;
	private Map<Producto, Integer> consumiciones;
	
	public Mesa(int numeroDeMesa,Estados estado){
		setEstado(estado);
		this.numeroDeMesa = numeroDeMesa;
		this.consumiciones = new HashMap<Producto, Integer>();
	}
	
	public Estados getEstado() {
		return estado;
	}
	
	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}
	
	public void setConsumisiones(Producto producto,Integer cantidad){
		this.consumiciones.put(producto, cantidad);
	}
	
	public void setEstado(Estados estado){
		this.estado = estado;
	}
	
	
	
	public enum Estados {
		Disponible,
		Cerrada,
		Ocupada
	}
}
