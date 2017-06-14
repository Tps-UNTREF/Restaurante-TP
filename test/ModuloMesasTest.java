package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Combo;
import clases.Mesa;
import clases.ModuloMesa;
import clases.ModuloPrecios;
import clases.ProductoUnico;
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

public class ModuloMesasTest {

	ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();

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
	public void mesaNumeroDeMesa() {
		Mesa mesa = new Mesa(1, Estado.CERRADA);
		assertEquals(1, mesa.getNumeroDeMesa());
	}

	@Test
	public void mesaEstado() {
		Mesa mesa = new Mesa(1, Estado.CERRADA);
		assertEquals(Estado.CERRADA, mesa.getEstado());
	}

	@Test
	public void moduloMesaCantidadDeMesas() throws MesasYaGeneradasExcepcion {
		assertEquals(10, moduloMesa.getCantidadMesas());
	}

	@Test
	public void moduloMesaOcuparMesa() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.ocuparMesa(5);
		assertEquals(Estado.OCUPADA, moduloMesa.getMesa(5).getEstado());
	}

	@Test(expected = MesaNoDisponibleExcepcion.class)
	public void moduloMesaOcuparMesaEnEstadoCerrada() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.cerrarMesa(6);
		moduloMesa.ocuparMesa(6);
	}

	@Test
	public void moduloMesaCerrarMesa() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.cerrarMesa(7);
		assertEquals(Estado.CERRADA, moduloMesa.getMesa(7).getEstado());
	}

	@Test(expected = MesaNoDisponibleExcepcion.class)
	public void moduloMesaCerrarMesaEnEstadoNoDisponible()
			throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.ocuparMesa(5);
		moduloMesa.cerrarMesa(5);
	}

	@Test
	public void moduloMesaPasarADisponibleEstandoCerrada()
			throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		moduloMesa.cerrarMesa(7);
		moduloMesa.pasarMesaADisponible(7);
		assertEquals(Estado.DISPONIBLE, moduloMesa.getMesa(7).getEstado());
	}

	@Test
	public void moduloMesaPasarADisponibleEstandoOcupada()
			throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		moduloMesa.ocuparMesa(5);
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estado.DISPONIBLE, moduloMesa.getMesa(5).getEstado());
	}

	@Test
	public void mesaregistrarUnaConsumision()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoExcepcion, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categoria.BEBIDACONALCOHOL);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		moduloMesa.getMesa(1).setEstado(Estado.OCUPADA);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		assertTrue(moduloMesa.getMesa(1).getConsumisiones().containsKey(heineken));
	}

	@Test(expected = ProductoNoEncontradoExcepcion.class)
	public void mesaregistrarUnaConsumisionSinQueExistaLaConsumision()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoExcepcion, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoExcepcion {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categoria.BEBIDACONALCOHOL);
		moduloMesa.getMesa(1).setEstado(Estado.OCUPADA);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
	}

	@Test(expected = MesaNoOcupadaExcepcion.class)
	public void mesaregistrarUnaConsumisionConMesaCerrada()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoExcepcion, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoExcepcion {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categoria.BEBIDACONALCOHOL);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);

	}

	@Test
	public void mesaregistrarDosConsumision()
			throws PrecioDeVentaInvalidoExcepcion, MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion,
			ProductoNoEncontradoExcepcion, MesaNoOcupadaExcepcion, DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categoria.BEBIDACONALCOHOL);
		ProductoUnico mozzarella = new ProductoUnico("Mozzarella", 20, 55, Categoria.PIZZAS);
		Combo muzaHeineken = new Combo("Mozzarella + Heineken", 10);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		ModuloPrecios.getModuloPrecios().altaProducto(mozzarella);
		ModuloPrecios.getModuloPrecios().altaProducto(muzaHeineken);
		moduloMesa.getMesa(1).setEstado(Estado.OCUPADA);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		moduloMesa.getMesa(1).setConsumisiones(muzaHeineken, 1);
		assertTrue(moduloMesa.getMesa(1).getConsumisiones().containsKey(muzaHeineken));
	}

	@Test(expected = ProductoNoEncontradoExcepcion.class)
	public void mesaregistrarDosConsumisionSinQueExistaLaConsumision()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoExcepcion, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categoria.BEBIDACONALCOHOL);
		ProductoUnico mozzarella = new ProductoUnico("Mozzarella", 20, 55, Categoria.PIZZAS);
		Combo muzaHeineken = new Combo("Mozzarella + Heineken", 10);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		ModuloPrecios.getModuloPrecios().altaProducto(mozzarella);
		moduloMesa.getMesa(1).setEstado(Estado.OCUPADA);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		moduloMesa.getMesa(1).setConsumisiones(muzaHeineken, 1);
	}

}
