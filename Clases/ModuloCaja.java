package Clases;

import java.util.ArrayList;
import java.util.Date;

import Clases.Mesa.Estados;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExcepcion;

public class ModuloCaja {
	private static ModuloCaja moduloCaja;
	private ArrayList<Ticket> tickets;

	private ModuloCaja() {
		this.tickets = new ArrayList<Ticket>();
	}

	public static ModuloCaja getModuloCaja() {
		if (null == moduloCaja) {
			moduloCaja = new ModuloCaja();
		}
		return moduloCaja;
	}

	/**
	 * pre: Genera el ticket de una mesa post: La mesa tiene que estar ocupada,
	 * si eso es correcto calcula el monto total de la lista generando el ticket
	 * para la mesa y almacenandola en lista. Luego pone la mesa en disponible y
	 * limpia las consumisiones de la mesa.
	 */
	public void generarTicket(Mesa mesa)
			throws MesaNoOcupadaExcepcion, MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		if (mesa.getEstado() == Estados.Ocupada) {
			Ticket t = new Ticket(mesa);
			tickets.add(t);
			mesa.setEstado(Estados.Disponible); // Pasa la mesa a disponible
			System.out.println("La mesa paso a " + mesa.getEstado());
			mesa.getConsumisiones().clear(); // borra la lista de consumisiones
			System.out.println("Se limpiaron las consumisiones de la mesa");
			System.out.println(t.toString());
		} else {
			throw new MesaNoOcupadaExcepcion("La mesa tiene que estar ocupada para poder generar ticket");
		}
	}
	
	/**
	 * pre: Se le pasa 2 fechas post: Busca todos los tickets entre las 2 fechas
	 * dadas y calcula los ingresos totales entre esas 2 fechas. Tambien muestra
	 * por consola el codigo del ticket y su valor.
	 */
	public void listarTickets(Date fecha1, Date fecha2) {
		double ingresos = 0;
		for (Ticket t : tickets) {
			if (t.getFecha().after(fecha1) && t.getFecha().before(fecha2) || t.getFecha().equals(fecha1)
					|| t.getFecha().equals(fecha2)) {
				ingresos += t.getMontoTotal();
				System.out.println("Codigo de ticket: " + t.getCodigoDeTicket() + " montoTotal: " + t.getMontoTotal());
			}
		}
		System.out.println("La suma total de los ingresos desde " + fecha1 + " hasta " + fecha2 + "es: " + ingresos);

	}

}
