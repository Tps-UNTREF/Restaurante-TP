package Clases;

import static org.junit.Assert.*;

import org.junit.Test;

import Clases.Mesa.Estados;
import Clases.Producto.Categorias;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.PrecioDeVentaInvalidoException;

public class TicketTest {

	@Test
	public void Ticket_CodigoDeMesa() {
		Ticket ticket = new Ticket(new Mesa(1,Estados.Disponible),20);
		assertEquals(1,ticket.getCodigoDeTicket());
	}
	
	@Test
	public void Ticket_FechaHora() throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoException, MesaNoOcupadaExepcion, MesaNoDisponibleExcepcion {
		ModuloCaja mc = ModuloCaja.getModuloCaja();
		ModuloPrecios mp = ModuloPrecios.getModuloPrecios();
		ModuloMesa mm = ModuloMesa.getModuloMesa();
		
		
		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categorias.Minutas);
		
		mm.generarMesas(2);
		mp.altaProducto(p1);
		mp.altaProducto(p3);
		
		mm.ocuparMesa(1);
		mm.getMesa(1).setConsumisiones(p1, 2);
		mm.getMesa(1).setConsumisiones(p3, 2);
		
		mc.generarTicket(mm.getMesa(1));
			
	}
	
	
	

}
