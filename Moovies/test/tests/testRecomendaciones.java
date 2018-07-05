package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import generos.Genero;
import generos.GeneroGeneral;
import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;
import recomendaciones.Recomendacion;
import recomendaciones.RecomendacionPeliculasQueridasPorAmigos;
import recomendaciones.RecomendacionPeliculasVistasPorAmigos;
import recomendaciones.ReocomendacionMasQueridasPorAmigos;

public class testRecomendaciones {
	
	@Mock UsuarioRegular usuario1;
	@Mock UsuarioRegular usuario2;
	@Mock UsuarioRegular usuario3;
	@Mock UsuarioRegular usuario4;
	@Mock UsuarioRegular usuario5;
	@Mock Pelicula pelicula1;
	@Mock Pelicula pelicula2;
	@Mock Pelicula pelicula3;
	@Mock Pelicula pelicula4;
	@Mock Pelicula pelicula5;
	@Mock List<Genero> generosPelicula; 
	@Mock SistemaMoovies sistemaMoovies;
	
	@Before
	public void setUp() throws Exception {
		
		sistemaMoovies  = spy(new SistemaMoovies());
		
		generosPelicula = Arrays.asList(mock(GeneroGeneral.class));
		pelicula1 		= spy(new Pelicula("Matrix"  , "2002", "132", generosPelicula));
		pelicula2 		= spy(new Pelicula("StarWars", "1985", "fg2", generosPelicula));
		pelicula3 	  	= spy(new Pelicula("Batman1" , "1999", "f32", generosPelicula));
		pelicula4 	  	= spy(new Pelicula("Batman2" , "1999", "f32", generosPelicula));
		pelicula5 	  	= spy(new Pelicula("Batman3" , "1999", "f32", generosPelicula));
		sistemaMoovies.addPelicula(pelicula1);
		sistemaMoovies.addPelicula(pelicula2);
		sistemaMoovies.addPelicula(pelicula3); 
		sistemaMoovies.addPelicula(pelicula4);
		sistemaMoovies.addPelicula(pelicula5);
		
		usuario1 = spy(new UsuarioRegular("usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante",1872));
		usuario2 = spy(new UsuarioRegular("usuario2", "contrasenia2", "name2","surname2","M",20,"Comerciante",2024));
		usuario3 = spy(new UsuarioRegular("usuario3", "contrasenia3", "name3","surname3","M", 30,"Estudiante",1876));
		usuario4 = spy(new UsuarioRegular("usuario4", "contrasenia4", "name4","surname4","M", 40,"Estudiante",1877));
		usuario5 = spy(new UsuarioRegular("usuario5", "contrasenia5", "name5","surname5","M", 50,"Estudiante",1878));
		sistemaMoovies.addUsuario(usuario2);
		sistemaMoovies.addUsuario(usuario1);
		sistemaMoovies.addUsuario(usuario3);
		sistemaMoovies.addUsuario(usuario4);
		sistemaMoovies.addUsuario(usuario5);
		
		usuario1.enviarSolicitudDeAmistadA(usuario2);
		usuario1.enviarSolicitudDeAmistadA(usuario3);
		usuario1.enviarSolicitudDeAmistadA(usuario4);
		usuario1.enviarSolicitudDeAmistadA(usuario5);
		usuario2.aceptarSolicitudDeAmistad(usuario1);
		usuario3.aceptarSolicitudDeAmistad(usuario1);
		usuario4.aceptarSolicitudDeAmistad(usuario1);
		usuario5.aceptarSolicitudDeAmistad(usuario1);

	} 

	@Test
	public void testUnUsuarioNuevoIniciaConLaReocomendacionMasQueridasPorAmigos() {
		Recomendacion reocomendacionMasQueridasPorAmigos = new ReocomendacionMasQueridasPorAmigos();
		assertTrue(usuario1.getRecomendacion().getClass().equals(reocomendacionMasQueridasPorAmigos.getClass()));
	}

	@Test
	public void testElSistemaModificaElMetodoDeRecomendacionAPeliculasVistasPorAmigosDeUnUsuario() {
		Recomendacion recomendacionPeliculasVistasPorAmigos = new RecomendacionPeliculasVistasPorAmigos();
		sistemaMoovies.cambiarRecomendacion(usuario1, recomendacionPeliculasVistasPorAmigos);
		verify(usuario1).setRecomendacion(recomendacionPeliculasVistasPorAmigos);
		assertTrue(usuario1.getRecomendacion().equals(recomendacionPeliculasVistasPorAmigos));
	}
	
	@Test
	public void testElSistemaModificaElMetodoDeRecomendacionAPeliculasQueridasPorAmigosDeUnUsuario() {
		Recomendacion recomendacionPeliculasQueridasPorAmigos = new RecomendacionPeliculasQueridasPorAmigos();
		sistemaMoovies.cambiarRecomendacion(usuario1, recomendacionPeliculasQueridasPorAmigos);
		verify(usuario1).setRecomendacion(recomendacionPeliculasQueridasPorAmigos);
		assertTrue(usuario1.getRecomendacion().equals(recomendacionPeliculasQueridasPorAmigos));
	}
	
	@Test
	public void testLaRecomendacionMasQueridasPorAmigosDeUnUsuarioSonLasPeliculas2y3() {
		// Este mecanismo recomienda aquellas peliculas que mas de la mitad de sus
		//  amigos evaluaron con 4 o mas, y que el todavia no evaluo
		// Esta recomendacion se setea por defecto al crear un UsuarioRegular
		usuario1.calificarPelicula(pelicula1, 3, sistemaMoovies);
		usuario2.calificarPelicula(pelicula2, 4, sistemaMoovies);
		usuario2.calificarPelicula(pelicula3, 1, sistemaMoovies);
		usuario3.calificarPelicula(pelicula1, 1, sistemaMoovies);
		usuario3.calificarPelicula(pelicula2, 4, sistemaMoovies);
		usuario3.calificarPelicula(pelicula3, 4, sistemaMoovies);
		usuario4.calificarPelicula(pelicula2, 5, sistemaMoovies);
		usuario4.calificarPelicula(pelicula3, 5, sistemaMoovies);
		usuario4.calificarPelicula(pelicula4, 4, sistemaMoovies);
		usuario5.calificarPelicula(pelicula3, 5, sistemaMoovies);
		usuario5.calificarPelicula(pelicula4, 4, sistemaMoovies);
		
		List<Pelicula> peliculasRecomendadas = Arrays.asList(pelicula2, pelicula3);
		assertTrue(sistemaMoovies.recomendarPeliculas(usuario1).containsAll(peliculasRecomendadas));
	}
	
	@Test
	public void testLaRecomendacionPeliculasVistasPorAmigosDeUnUsuarioSonLasPeliculas3y4() {
		// Este mecanismo recomienda aquellas peliculas que fueron evaluadas por 2 o
		//  más amigos con puntaje 3 o superior, ordenadas por el rating de IMDB.
		
		Recomendacion recomendacionPeliculasVistasPorAmigos = new RecomendacionPeliculasVistasPorAmigos();
		sistemaMoovies.cambiarRecomendacion(usuario1, recomendacionPeliculasVistasPorAmigos);
		
		usuario2.calificarPelicula(pelicula4, 1, sistemaMoovies);
		usuario3.calificarPelicula(pelicula1, 5, sistemaMoovies);
		usuario3.calificarPelicula(pelicula3, 4, sistemaMoovies);
		usuario4.calificarPelicula(pelicula3, 5, sistemaMoovies);
		usuario4.calificarPelicula(pelicula4, 5, sistemaMoovies);
		usuario5.calificarPelicula(pelicula1, 1, sistemaMoovies);
		usuario5.calificarPelicula(pelicula4, 5, sistemaMoovies);
		
		List<Pelicula> peliculasRecomendadas = Arrays.asList(pelicula3, pelicula4);
		assertTrue(sistemaMoovies.recomendarPeliculas(usuario1).containsAll(peliculasRecomendadas));
		verify(usuario1).getRecomendacion();
	}
	
	@Test
	public void testLaRecomendacionPeliculasQueridasPorAmigosDeUnUsuarioSonLasPeliculas2y3() {
		// Este mecanismo recomienda aquellas películas con mayor rating promedio, 
		//  que al menos un amigo haya evaluado.
		
		Recomendacion recomendacionPeliculasQueridasPorAmigos = new RecomendacionPeliculasQueridasPorAmigos();
		sistemaMoovies.cambiarRecomendacion(usuario1, recomendacionPeliculasQueridasPorAmigos);
		
		usuario2.calificarPelicula(pelicula3, 4, sistemaMoovies);
		usuario3.calificarPelicula(pelicula2, 4, sistemaMoovies);
		usuario3.calificarPelicula(pelicula3, 4, sistemaMoovies);
		usuario4.calificarPelicula(pelicula3, 4, sistemaMoovies);
		usuario4.calificarPelicula(pelicula4, 2, sistemaMoovies);
		usuario5.calificarPelicula(pelicula1, 2, sistemaMoovies);
		usuario5.calificarPelicula(pelicula3, 4, sistemaMoovies);
		
		List<Pelicula> peliculasRecomendadas = Arrays.asList(pelicula2, pelicula3);
		assertTrue(sistemaMoovies.recomendarPeliculas(usuario1).containsAll(peliculasRecomendadas));
	}

}
