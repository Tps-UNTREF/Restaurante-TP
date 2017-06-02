package Clases;
import java.util.Calendar;
import java.util.Date;


public class Ticket{

	private static int contadorid;
	private int codigoDeTicket;
	private Date fecha;
	private double montoTotal;
	private Mesa mesa;
	
	
	public Ticket(Mesa mesa,double montoTotal) {
		this.codigoDeTicket = contadorid++;
		this.mesa = mesa;
		setMontoTotal(montoTotal);
	}

	public int getCodigoDeTicket() {
		return codigoDeTicket;
	}

	public void setCodigoDeTicket(int codigoDeTicket) {
		this.codigoDeTicket = codigoDeTicket;
	}
	
	public double getMontoTotal(){
		return this.montoTotal;
	}
	
	private void setMontoTotal(double monto){
		this.montoTotal = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public String toString(){
		return "Numero de ticket: " + getCodigoDeTicket() + " MontoTicket: " + getMontoTotal();
	}

}
