package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import clases.Combo;
import clases.Mesa;
import clases.ModuloCaja;
import clases.ModuloMesa;
import clases.ModuloPrecios;
import clases.ProductoUnico;
import clases.Ticket;
import clases.Mesa.Estado;
import clases.Producto.Categoria;
import excepciones.DescuentoInvalidoExcepcion;
import excepciones.MesaEstadoInvalidoExcepcion;
import excepciones.MesaNoDisponibleExcepcion;
import excepciones.MesaNoOcupadaExcepcion;
import excepciones.MesasYaGeneradasExcepcion;
import excepciones.PrecioDeCostoInvalidoExcepcion;
import excepciones.PrecioDeVentaInvalidoExcepcion;
import excepciones.ProductoNoEncontradoExcepcion;
import excepciones.ProductoYaExistenteExcepcion;

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
			if (moduloMesa.getMesa(i).getEstado() != Estado.DISPONIBLE) {
				moduloMesa.getMesa(i).setEstado(Estado.DISPONIBLE);
			}
		}
	}

	@Test
	public void ticketCodigoDeMesa() throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		Ticket ticket = new Ticket(new Mesa(1, Estado.DISPONIBLE));
		assertNotEquals(0, ticket.getCodigoDeTicket());
	}

	@Test
	public void moduloCajaGenerarTicket()
			throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoExcepcion, MesaNoOcupadaExcepcion,
			MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion, ProductoNoEncontradoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {
		System.out.println("TEST GENERAR TICKET \n");

		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categoria.BEBIDASINALCOHOL);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categoria.MUNUTAS);

		moduloPrecios.altaProducto(p1);
		moduloPrecios.altaProducto(p3);

		moduloMesa.ocuparMesa(1);
		moduloMesa.getMesa(1).setConsumisiones(p1, 2);
		moduloMesa.getMesa(1).setConsumisiones(p3, 2);
		// prueba del metodo generarTicket
		moduloCaja.generarTicket(moduloMesa.getMesa(1));

	}

	@Test // falta limpiar todo
	public void moduloCajaListarTicket() throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoExcepcion,
			MesaNoOcupadaExcepcion, MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion, DescuentoInvalidoExcepcion,
			ProductoNoEncontradoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {
		System.out.println("TEST LISTAR TICKET \n");

		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categoria.BEBIDASINALCOHOL);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categoria.MUNUTAS);
		ProductoUnico p8 = new ProductoUnico("Hamburguesa completa", 25, 60, Categoria.HAMBURGUESAS);
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