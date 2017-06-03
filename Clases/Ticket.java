package Clases;

import java.util.Date;

public class Ticket {

	private static int contadorid;
	private int codigoDeTicket;
	private Date fechaYHora;
	private double montoTotal;
	private Mesa mesa;

	/**
	 * pre: Se le pasa una mesa, un montoTotal y una fecha. post: genera el
	 * ticket
	 */
	public Ticket(Mesa mesa, double montoTotal, Date fechaYHora) {
		this.codigoDeTicket = contadorid++;
		this.mesa = mesa;
		this.montoTotal = montoTotal;
		this.fechaYHora = fechaYHora;
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
	 * post: Devuelve la mesa del ticket.
	 */
	public Mesa getMesa() {
		return mesa;
	}

	/**
	 * post: Devuelve la descripcion del ticket.
	 */
	public String toString() {
		return "Numero de ticket: " + getCodigoDeTicket() + " MontoTicket: " + getMontoTotal();
	}

}
