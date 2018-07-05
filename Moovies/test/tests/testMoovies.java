package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import generos.GeneroGeneral;
import moovies.Administrador;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class testMoovies {
	
	private SistemaMoovies sistemaMoovies;
	
	@Before
	public void setUp() throws Exception {
		
		sistemaMoovies   = new SistemaMoovies();

	} 
	
	@Test
	public void UnSistemaNuevoNoTieneANingunUsuarioRegular(){
		SistemaMoovies sistemaNuevo = new SistemaMoovies();
		assertTrue(sistemaNuevo.getUsuarios().isEmpty());
	}

	@Test
	public void UnSistemaNuevoNoTieneANingunAdministrador(){
	
		SistemaMoovies sistemaNuevo = new SistemaMoovies();
		assertTrue(sistemaNuevo.getAdministradores().isEmpty());
	}

	@Test
	public void UnSistemaNuevoNoTieneNingunaPelicula(){
		
		SistemaMoovies sistemaNuevo = new SistemaMoovies();
		assertTrue(sistemaNuevo.getPeliculas().isEmpty());
	}

	@Test
	public void UnSistemaAgregaAUnUsuarioRegular() {

		UsuarioRegular unUsuario = sistemaMoovies.registrarUsuario("usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante",1872);
		assertTrue(sistemaMoovies.getUsuarios().contains(unUsuario));
		assertEquals(sistemaMoovies.getUsuarios().size(), 1);
	}
		
	@Test
	public void UnSistemaAgregaAUnAdministrador(){

		Administrador admin = sistemaMoovies.registrarAdministrador("rogelio","contraseniaA","usuarioA", "contraseniaA", "M",40,"Estudiante",4056);
		assertTrue(sistemaMoovies.getAdministradores().contains(admin));
		assertEquals(sistemaMoovies.getAdministradores().size(), 1);
	}

	@Test
	public void UnSistemaTieneVariosUsuariosRegulares(){
		
		sistemaMoovies.registrarUsuario("pedroPro" ,"ordep","pedro" ,"perez","M",20,"Estudiante",1876);
		sistemaMoovies.registrarUsuario("carlosPro","solrc","carlos","perez","M",20,"Estudiante",1877);
		sistemaMoovies.registrarUsuario("juanPro" ,"juanii","juan"  ,"perez","M",20,"Estudiante",1876);
	
		assertEquals(sistemaMoovies.getUsuarios().size(), 3);
	}

	@Test
	public void UnSistemaTieneVariasPeliculas(){
		
		sistemaMoovies.agregarPelicula("Matrix"  ,"2002","132",Arrays.asList(new GeneroGeneral("accion")));
		sistemaMoovies.agregarPelicula("StarWars","1985","fg2",Arrays.asList(new GeneroGeneral("accion")));
		sistemaMoovies.agregarPelicula("Batman"  ,"1999","f32",Arrays.asList(new GeneroGeneral("accion")));
	
		assertEquals(sistemaMoovies.getPeliculas().size(), 3);
	}

	@Test(expected=RuntimeException.class)
	public void testErrorUsuarioYaExistente(){
		
		sistemaMoovies.registrarUsuario("usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante",1872);

		assertEquals("El usuario ya esta en uso", sistemaMoovies.registrarUsuario(
				"usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante",1872));
	}
	
}

