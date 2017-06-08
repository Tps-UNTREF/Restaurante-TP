package Clases;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Ticket {

	private static int contadorid;
	private int codigoDeTicket;
	private Date fechaYHora;
	private double montoTotal;
	private int numeroDeMesa;
	private HashMap<Producto, Integer> listaDeConsumiciones = new HashMap<Producto, Integer>();
	
	/**
	 * pre: Se le pasa una mesa, un montoTotal y una fecha. post: genera el
	 * ticket
	 */
	public Ticket(Mesa mesa) {
		this.codigoDeTicket = contadorid++;
		this.numeroDeMesa = mesa.getNumeroDeMesa();
		this.fechaYHora = new Date();
		this.listaDeConsumiciones.putAll(mesa.getConsumisiones());
		this.montoTotal = obtenerMontoTotal(mesa.getConsumisiones());
	}
	/**
	 * pre: Recibe una diccionario donde la clave es el producto y el valor la cantidad.
	 * post: Devuelve la descripcion del ticket.
	 */
	private double obtenerMontoTotal(Map<Producto, Integer> listaDeConsumiciones) {
		double montoTotal = 0;
		for (Map.Entry<Producto, Integer> entry : listaDeConsumiciones.entrySet()) {
			montoTotal += (entry.getKey()).getPrecioDeVenta() * entry.getValue();
		}
		return montoTotal;
	}
	/**
	 * post: Devuelve el codigo del ticket
	 */
	public int getCodigoDeTicket() {
		return codigoDeTicket;
	}

	/**
	 * post: Devuelve el montoTotal.
	 */
	public double getMontoTotal() {
		return this.montoTotal;
	}

	/**
	 * post: Devuelve la fecha en formate Date.
	 */
	public Date getFecha() {
		return this.fechaYHora;
	}

	/**
	 * post: Devuelve el numeroDeMesa del ticket.
	 */
	public int getNumeroDeMesa() {
		return this.numeroDeMesa;
	}
	
	/**
	 * post: Devuelve la mesa del ticket.
	 */
	public Map<Producto, Integer> getListaDeConsumiciones() {
		return this.listaDeConsumiciones;
	}

	/**
	 * post: Devuelve la descripcion del ticket.
	 */
	public String toString() {
		String fullString = fechaYHora.toString() + "\n";
		for (Map.Entry<Producto, Integer> entry : listaDeConsumiciones.entrySet()) {
			montoTotal += (entry.getKey()).getPrecioDeVenta() * entry.getValue();
			fullString += "Producto: " + entry.getKey().toStringMenu() + "x" + entry.getValue() + "\n";
		}
		fullString += "Numero de ticket: " + getCodigoDeTicket() + " Monto Total: " + getMontoTotal();
		return fullString;
	}
}
