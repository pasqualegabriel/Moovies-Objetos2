package buscadorDePeliculas;

import moovies.Pelicula;

public abstract class Filtro {
	
	private Condicion condicion;

	public Filtro(){
		super();
	}
	
	public Filtro(Condicion condicion){
		super();
		this.condicion = condicion;
	} 

	public abstract boolean filtrarPelicula(Pelicula pelicula);
	
	public Condicion getCondicion(){
		return this.condicion;
	}

}
