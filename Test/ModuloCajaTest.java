package Test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Clases.Combo;
import Clases.Mesa;
import Clases.ModuloCaja;
import Clases.ModuloMesa;
import Clases.ModuloPrecios;
import Clases.ProductoUnico;
import Clases.Ticket;
import Clases.Mesa.Estados;
import Clases.Producto.Categorias;
import Excepciones.DescuentoInvalidoException;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;
import Excepciones.ProductoYaExistenteException;
import Excepciones.ProductoNoEncontradoException;

public class ModuloCajaTest {

	ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
	ModuloCaja moduloCaja = ModuloCaja.getModuloCaja();
	ModuloPrecios moduloPrecios = ModuloPrecios.getModuloPrecios();

	@Before
	public void inicializacion()
			throws MesasYaGeneradasExcepcion, MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		// Si es la primera vez, genera las 10 mesas
		if (moduloMesa.getCantidadMesas() == 0) {
			ModuloMesa.generarMesas(10);
		}
		// Me encargo de pasar todas las mesas a disponible
		for (int i = 0; i < moduloMesa.getCantidadMesas(); i++) {
			if (moduloMesa.getMesa(i).getEstado() != Estados.Disponible) {
				moduloMesa.getMesa(i).setEstado(Estados.Disponible);
			}
		}
	}

	@Test
	public void Ticket_CodigoDeMesa() throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		Ticket ticket = new Ticket(new Mesa(1, Estados.Disponible));
		assertEquals(0, ticket.getCodigoDeTicket());
	}

	@Test
	public void ModuloCaja_generarTicket()
			throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoException, MesaNoOcupadaExcepcion,
			MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion, ProductoNoEncontradoException, PrecioDeCostoInvalidoException, ProductoYaExistenteException {
		System.out.println("TEST GENERAR TICKET \n");

		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categorias.Minutas);

		moduloPrecios.altaProducto(p1);
		moduloPrecios.altaProducto(p3);

		moduloMesa.ocuparMesa(1);
		moduloMesa.getMesa(1).setConsumisiones(p1, 2);
		moduloMesa.getMesa(1).setConsumisiones(p3, 2);
		// prueba del metodo generarTicket
		moduloCaja.generarTicket(moduloMesa.getMesa(1));

	}

	@Test // falta limpiar todo
	public void ModuloCaja_listarTicket() throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoException,
			MesaNoOcupadaExcepcion, MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion, DescuentoInvalidoException,
			ProductoNoEncontradoException, PrecioDeCostoInvalidoException, ProductoYaExistenteException {
		System.out.println("TEST LISTAR TICKET \n");

		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categorias.Minutas);
		ProductoUnico p8 = new ProductoUnico("Hamburguesa completa", 25, 60, Categorias.Hamburguesas);
		Combo c1 = new Combo("Combo hamburguesa y gaseosa", 10);

		moduloPrecios.altaProducto(p1);
		moduloPrecios.altaProducto(p3);
		moduloPrecios.altaProducto(p8);
		moduloPrecios.altaProducto(c1);

		moduloMesa.ocuparMesa(1);
		moduloMesa.getMesa(1).setConsumisiones(p1, 2);
		moduloMesa.getMesa(1).setConsumisiones(p3, 2);
		// prueba del metodo generarTicket

		moduloMesa.ocuparMesa(2);
		moduloMesa.getMesa(2).setConsumisiones(p8, 1);
		moduloMesa.getMesa(2).setConsumisiones(p3, 2);
		// prueba del metodo generarTicket

		moduloMesa.ocuparMesa(3);
		moduloMesa.getMesa(3).setConsumisiones(c1, 1);
		moduloMesa.getMesa(3).setConsumisiones(p3, 2);
		moduloMesa.getMesa(2).setConsumisiones(p8, 1);
		System.out.println("TICKET 1 \n");
		moduloCaja.generarTicket(moduloMesa.getMesa(1));
		System.out.println("TICKET 2 \n");
		moduloCaja.generarTicket(moduloMesa.getMesa(2));
		System.out.println("TICKET 3 \n");
		moduloCaja.generarTicket(moduloMesa.getMesa(3));
		System.out.println("LISTAR TICKET \n");
		moduloCaja.listarTickets(new Date(), new Date());

		System.out.println("------------------------------");

	}

}