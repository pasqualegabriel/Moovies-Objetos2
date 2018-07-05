package buscadorDePeliculas;

import moovies.Pelicula;

public class FiltroPorGenero extends Filtro {
	
	public FiltroPorGenero(Condicion condicion){
		super(condicion);
	}

	@Override
	public boolean filtrarPelicula(Pelicula pelicula) {

		return this.getCondicion().resolver(pelicula.getGenerosStrings());
	}
 
}
