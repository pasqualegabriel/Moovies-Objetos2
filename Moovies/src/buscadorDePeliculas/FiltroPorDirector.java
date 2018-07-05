package buscadorDePeliculas;

import moovies.Pelicula;

public class FiltroPorDirector extends Filtro {
	
	public FiltroPorDirector(Condicion condicion){
		super(condicion);
	}

	@Override
	public boolean filtrarPelicula(Pelicula pelicula) {

		return this.getCondicion().resolver(pelicula.getDirector());
	}

}
