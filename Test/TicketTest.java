package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Clases.Mesa;
import Clases.Mesa.Estados;
import Clases.ModuloMesa;

public class TicketTest {

	@Test
	public void Mesa_NumeroDeMesa() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(1,mesa.getNumeroDeMesa());
	}
	@Test
	public void Mesa_Estado() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(Estados.Cerrada,mesa.getEstado());
	}
	
	@Test
	public void ModuloMesa_CantidadDeMesas() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		assertEquals(10,moduloMesa.getCantidadMesas());
	}
	
	@Test
	public void ModuloMesa_OcuparMesa() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.ocuparMesa(5);
		assertEquals(Estados.Ocupada, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test
	public void ModuloMesa_OcuparMesaEnEstadoCerrada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.cerrarMesa(5);
		moduloMesa.ocuparMesa(5);
	}
	
	@Test
	public void ModuloMesa_CerrarMesa() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.cerrarMesa(5);
		assertEquals(Estados.Cerrada, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test
	public void ModuloMesa_CerrarMesaEnEstadoNoDisponible() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.ocuparMesa(5);
		moduloMesa.cerrarMesa(5);
	}
	
	@Test
	public void ModuloMesa_PasarADisponibleEstandoCerrada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.cerrarMesa(5);
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test
	public void ModuloMesa_PasarADisponibleEstandoOcupada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		moduloMesa.cerrarMesa(5);
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
