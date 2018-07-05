package buscadorDePeliculas;

import moovies.Pelicula;

public class FiltroPorTitulo extends Filtro {
	
	public FiltroPorTitulo(Condicion condicion){
		super(condicion);
	}

	@Override
	public boolean filtrarPelicula(Pelicula pelicula) {

		return this.getCondicion().resolver(pelicula.getNombre());
	}

}
