package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Combo;
import Clases.Mesa;
import Clases.Mesa.Estados;
import Clases.ModuloMesa;
import Clases.ModuloPrecios;
import Clases.Producto.Categorias;
import Clases.ProductoUnico;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExcepcion;
import Excepciones.DescuentoInvalidoException;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;
import Excepciones.ProductoNoEncontradoException;

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
			if (moduloMesa.getMesa(i).getEstado() != Estados.Disponible) {
				moduloMesa.getMesa(i).setEstado(Estados.Disponible);
			}
		}
	}

	@Test
	public void Mesa_NumeroDeMesa() {
		Mesa mesa = new Mesa(1, Estados.Cerrada);
		assertEquals(1, mesa.getNumeroDeMesa());
	}

	@Test
	public void Mesa_Estado() {
		Mesa mesa = new Mesa(1, Estados.Cerrada);
		assertEquals(Estados.Cerrada, mesa.getEstado());
	}

	@Test
	public void ModuloMesa_CantidadDeMesas() throws MesasYaGeneradasExcepcion {
		assertEquals(10, moduloMesa.getCantidadMesas());
	}

	@Test
	public void ModuloMesa_OcuparMesa() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.ocuparMesa(5);
		assertEquals(Estados.Ocupada, moduloMesa.getMesa(5).getEstado());
	}

	@Test(expected = MesaNoDisponibleExcepcion.class)
	public void ModuloMesa_OcuparMesaEnEstadoCerrada() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.cerrarMesa(6);
		moduloMesa.ocuparMesa(6);
	}

	@Test
	public void ModuloMesa_CerrarMesa() throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.cerrarMesa(7);
		assertEquals(Estados.Cerrada, moduloMesa.getMesa(7).getEstado());
	}

	@Test(expected = MesaNoDisponibleExcepcion.class)
	public void ModuloMesa_CerrarMesaEnEstadoNoDisponible()
			throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		moduloMesa.ocuparMesa(5);
		moduloMesa.cerrarMesa(5);
	}

	@Test
	public void ModuloMesa_PasarADisponibleEstandoCerrada()
			throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		moduloMesa.cerrarMesa(7);
		moduloMesa.pasarMesaADisponible(7);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(7).getEstado());
	}

	@Test
	public void ModuloMesa_PasarADisponibleEstandoOcupada()
			throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		moduloMesa.ocuparMesa(5);
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}

	@Test
	public void Mesa_registrarUnaConsumision()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoException, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoException {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		moduloMesa.getMesa(1).setEstado(Estados.Ocupada);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		assertTrue(moduloMesa.getMesa(1).getConsumisiones().containsKey(heineken));
	}

	@Test(expected = ProductoNoEncontradoException.class)
	public void Mesa_registrarUnaConsumisionSinQueExistaLaConsumision()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoException, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoException {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		moduloMesa.getMesa(1).setEstado(Estados.Ocupada);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
	}

	@Test(expected = MesaNoOcupadaExcepcion.class)
	public void Mesa_registrarUnaConsumisionConMesaCerrada()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoException, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoException {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);

	}

	@Test
	public void Mesa_registrarDosConsumision()
			throws PrecioDeVentaInvalidoException, MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion,
			ProductoNoEncontradoException, MesaNoOcupadaExcepcion, DescuentoInvalidoException, PrecioDeCostoInvalidoException {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		ProductoUnico mozzarella = new ProductoUnico("Mozzarella", 20, 55, Categorias.Pizzas);
		Combo muzaHeineken = new Combo("Mozzarella + Heineken", 10);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		ModuloPrecios.getModuloPrecios().altaProducto(mozzarella);
		ModuloPrecios.getModuloPrecios().altaProducto(muzaHeineken);
		moduloMesa.getMesa(1).setEstado(Estados.Ocupada);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		moduloMesa.getMesa(1).setConsumisiones(muzaHeineken, 1);
		assertTrue(moduloMesa.getMesa(1).getConsumisiones().containsKey(muzaHeineken));
	}

	@Test(expected = ProductoNoEncontradoException.class)
	public void Mesa_registrarDosConsumisionSinQueExistaLaConsumision()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, MesaEstadoInvalidoExcepcion,
			MesaNoDisponibleExcepcion, ProductoNoEncontradoException, MesaNoOcupadaExcepcion, PrecioDeCostoInvalidoException {
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		ProductoUnico mozzarella = new ProductoUnico("Mozzarella", 20, 55, Categorias.Pizzas);
		Combo muzaHeineken = new Combo("Mozzarella + Heineken", 10);
		ModuloPrecios.getModuloPrecios().altaProducto(heineken);
		ModuloPrecios.getModuloPrecios().altaProducto(mozzarella);
		moduloMesa.getMesa(1).setEstado(Estados.Ocupada);
		moduloMesa.getMesa(1).setConsumisiones(heineken, 1);
		moduloMesa.getMesa(1).setConsumisiones(muzaHeineken, 1);
	}

}
