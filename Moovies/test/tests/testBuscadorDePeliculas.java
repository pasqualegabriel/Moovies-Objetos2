package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import buscadorDePeliculas.Condicion;
import buscadorDePeliculas.CondicionContiene;
import buscadorDePeliculas.CondicionEsDistinta;
import buscadorDePeliculas.CondicionEsIgual;
import buscadorDePeliculas.Filtro;
import buscadorDePeliculas.FiltroCompuesto;
import buscadorDePeliculas.FiltroPorDirector;
import buscadorDePeliculas.FiltroPorGenero;
import buscadorDePeliculas.FiltroPorTitulo;
import buscadorDePeliculas.OperadorLogicoAND;
import buscadorDePeliculas.OperadorLogicoOR;
import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class testBuscadorDePeliculas {

	@Mock UsuarioRegular usuario;
	@Mock SistemaMoovies sistemaMoovies;
		
	@Before
	public void setUp() throws Exception {
			
		sistemaMoovies = spy(new SistemaMoovies());
		usuario = spy(new UsuarioRegular("usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante",1872));
	} 
	
	@Test
	public void testUnaCondicionContieneStar() {
		
		Condicion condicionContieneStar = new CondicionContiene("Star");

		assertTrue( condicionContieneStar.resolver("The Star Wars"));
		assertFalse(condicionContieneStar.resolver("The World Wars"));
		assertTrue( condicionContieneStar.resolver(Arrays.asList("accion", "StarWars")));
		assertFalse(condicionContieneStar.resolver(Arrays.asList("accion", "ficcion")));
	}
	
	@Test
	public void testUnaCondicionEsDistintaAStarWars() {
		
		Condicion condicionEsDistintaAStarWars = new CondicionEsDistinta("StarWars");

		assertTrue( condicionEsDistintaAStarWars.resolver("The Star Wars"));
		assertFalse(condicionEsDistintaAStarWars.resolver("StarWars"));
		assertTrue( condicionEsDistintaAStarWars.resolver(Arrays.asList("accion", "ficcion")));
		assertFalse(condicionEsDistintaAStarWars.resolver(Arrays.asList("accion", "StarWars")));
	}
	
	@Test
	public void testUnaCondicionEsIgualAStarWars() {
		
		Condicion condicionEsIgualAStarWars = new CondicionEsIgual("StarWars");

		assertTrue( condicionEsIgualAStarWars.resolver("StarWars"));
		assertFalse(condicionEsIgualAStarWars.resolver("The Star Wars"));
		assertTrue( condicionEsIgualAStarWars.resolver(Arrays.asList("accion", "StarWars")));
		assertFalse(condicionEsIgualAStarWars.resolver(Arrays.asList("accion", "ficcion")));
	}
	
	@Test
	public void testOperadorLogicoAnd() {
		
		OperadorLogicoAND and = new OperadorLogicoAND();
		
		assertTrue( and.resolver(true , true));
		assertFalse(and.resolver(true , false));
		assertFalse(and.resolver(false, true));
		assertFalse(and.resolver(false, false));
	}
	
	@Test
	public void testOperadorLogicoOr() {
		
		OperadorLogicoOR or = new OperadorLogicoOR();
		
		assertTrue( or.resolver(true , true));
		assertTrue( or.resolver(true , false));
		assertTrue( or.resolver(false, true));
		assertFalse(or.resolver(false, false));
	}
	
	@Test
	public void testUnUsuarioBuscaPeliculasConUnFiltroPorTituloConCondicionEsIgualABatmanYRetornaLaPeliculaBatman() {
		
		Pelicula batman   = mock(Pelicula.class);
		Pelicula starWars = mock(Pelicula.class);
		
		sistemaMoovies.addPelicula(batman  );
		sistemaMoovies.addPelicula(starWars);
		
		when(  batman.getNombre()).thenReturn("Batman"   ); 
		when(starWars.getNombre()).thenReturn("Star Wars"); 
		
		Filtro filtroPorTitulo = new FiltroPorTitulo(new CondicionEsIgual("Batman"));
		
		List<Pelicula> busqueda = usuario.buscarPeliculas(sistemaMoovies, filtroPorTitulo);
		
		assertEquals(busqueda.size(), 1);
		assertTrue  (busqueda.contains(batman  ));
		assertFalse (busqueda.contains(starWars));
		verify(sistemaMoovies).filtrarPeliculas(filtroPorTitulo);
	}
	
	@Test
	public void testUnUsuarioBuscaPeliculasConUnFiltroPorDirectorConCondicionEsDistintaALucasYRetornaLaPeliculaBatman() {
		
		Pelicula batman   = mock(Pelicula.class);
		Pelicula starWars = mock(Pelicula.class);
		
		sistemaMoovies.addPelicula(batman  );
		sistemaMoovies.addPelicula(starWars);
		
		when(  batman.getDirector()).thenReturn("George");  
		when(starWars.getDirector()).thenReturn("Lucas" ); 
		
		Filtro filtroPorDirector = new FiltroPorDirector(new CondicionEsDistinta("Lucas"));
		
		List<Pelicula> busqueda = usuario.buscarPeliculas(sistemaMoovies, filtroPorDirector);
		
		assertEquals(busqueda.size(), 1);
		assertTrue  (busqueda.contains(batman  ));
		assertFalse (busqueda.contains(starWars));
		verify(sistemaMoovies).filtrarPeliculas(filtroPorDirector);
	}
	
	@Test
	public void testUnUsuarioBuscaPeliculasConUnFiltroPorGeneroConCondicionContieneAFiccionYRetornaLasPeliculasBatmanYStarWars() {
	
		Pelicula batman   = mock(Pelicula.class);
		Pelicula starWars = mock(Pelicula.class);
		
		sistemaMoovies.addPelicula(batman  );
		sistemaMoovies.addPelicula(starWars);
		
		when(  batman.getGenerosStrings()).thenReturn(Arrays.asList("Drama"  , "Ciencia Ficcion")); 
		when(starWars.getGenerosStrings()).thenReturn(Arrays.asList("Ficcion", "Infantil"       )); 
		
		Filtro filtroPorGeneros = new FiltroPorGenero(new CondicionContiene("Ficcion"));
		
		List<Pelicula> busqueda  = usuario.buscarPeliculas(sistemaMoovies, filtroPorGeneros);
		
		assertEquals(busqueda.size(), 2);
		assertTrue  (busqueda.contains(batman  ));
		assertTrue  (busqueda.contains(starWars));
	}

	@Test
	public void testUnUsuarioBuscaPeliculasConUnFiltroCompuestoPorUnOperadorLogicoANDUnFiltroPorTituloConCondicionContieneAManYUnFiltroPorGeneroConCondicionEsIgualAAccion() {
	
		Pelicula superman = mock(Pelicula.class);
		Pelicula starWars = mock(Pelicula.class);
		Pelicula batman   = mock(Pelicula.class);
		
		sistemaMoovies.addPelicula(superman);
		sistemaMoovies.addPelicula(starWars);
		sistemaMoovies.addPelicula(batman  );
		
		when(superman.getGenerosStrings()).thenReturn(Arrays.asList("accion", "ficcion" )); 
		when(  batman.getGenerosStrings()).thenReturn(Arrays.asList("accion", "infantil")); 
		when(starWars.getGenerosStrings()).thenReturn(Arrays.asList("terror", "comedia" )); 
		when(superman.getNombre()).thenReturn("Superman"); 
		when(  batman.getNombre()).thenReturn("Batman"  ); 
		when(starWars.getNombre()).thenReturn("StarWars"); 
		
		Filtro filtroPorTitulo = new FiltroPorTitulo(new CondicionContiene("man"   ));
		Filtro filtroPorGenero = new FiltroPorGenero(new CondicionEsIgual ("accion"));
		
		Filtro filtroCompuesto = new FiltroCompuesto(filtroPorTitulo, new OperadorLogicoAND(), filtroPorGenero);
		
		List<Pelicula> busqueda = usuario.buscarPeliculas(sistemaMoovies, filtroCompuesto);
		
		assertEquals(busqueda.size(), 2);
		assertTrue  (busqueda.contains(superman));
		assertTrue  (busqueda.contains(batman));
		verify(sistemaMoovies).filtrarPeliculas(filtroCompuesto);
	}
	
	@Test
	public void testUnUsuarioBuscaPeliculasConUnFiltroCompuestoCompuestoPorDosFiltrosCompuestos() {

		Pelicula matrix   = mock(Pelicula.class);
		Pelicula starWars = mock(Pelicula.class);
		Pelicula batman   = mock(Pelicula.class);
		
		sistemaMoovies.addPelicula(matrix  );
		sistemaMoovies.addPelicula(starWars);
		sistemaMoovies.addPelicula(batman  );
		
		when(  matrix.getGenerosStrings()).thenReturn(Arrays.asList("accion", "ficcion" )); 
		when(  batman.getGenerosStrings()).thenReturn(Arrays.asList("accion", "drama"   )); 
		when(starWars.getGenerosStrings()).thenReturn(Arrays.asList("terror", "comedia" )); 
		when(  matrix.getNombre()).thenReturn("Matrix"  ); 
		when(  batman.getNombre()).thenReturn("Batman"  ); 
		when(starWars.getNombre()).thenReturn("StarWars"); 
		when(  matrix.getNombre()).thenReturn("Matrix"  ); 
		when(  batman.getNombre()).thenReturn("Batman"  ); 
		when(starWars.getNombre()).thenReturn("StarWars"); 
		when(  matrix.getDirector()).thenReturn("George"); 
		when(starWars.getDirector()).thenReturn("Lucas" ); 
		when(  batman.getDirector()).thenReturn("George"); 
		
		Filtro filtro1 = new FiltroPorTitulo(new CondicionEsIgual(   "SW"    )); 
		Filtro filtro2 = new FiltroPorTitulo(new CondicionEsDistinta("Matrix"));
		Filtro filtro3 = new FiltroPorGenero(new CondicionEsDistinta("drama" ));
		Filtro filtro4 = new FiltroPorDirector(new CondicionContiene("Lucas"));
		Filtro filtroCompuesto1 = new FiltroCompuesto(filtro1, new OperadorLogicoOR(), filtro2);
		Filtro filtroCompuesto2 = new FiltroCompuesto(filtro3, new OperadorLogicoAND(), filtro4);
		Filtro filtroCompuesto  = new FiltroCompuesto(filtroCompuesto1, new OperadorLogicoAND(), filtroCompuesto2);
		
		List<Pelicula> busqueda = usuario.buscarPeliculas(sistemaMoovies, filtroCompuesto);
		
		assertEquals(busqueda.size(), 1);
		assertTrue  (busqueda.contains(starWars));
		assertFalse (busqueda.contains( matrix ));
		assertFalse (busqueda.contains( batman ));
	
	} 

}

