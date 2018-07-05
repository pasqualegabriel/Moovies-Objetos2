package recomendaciones;

import java.util.List;

import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public abstract class Recomendacion {
	
	public abstract List<Pelicula> recomendarPeliculas(UsuarioRegular usuario, SistemaMoovies sistemaMoovies);
}
