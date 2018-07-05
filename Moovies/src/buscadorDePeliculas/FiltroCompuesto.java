package buscadorDePeliculas;

import moovies.Pelicula;

public class FiltroCompuesto extends Filtro {
	
	private Filtro primerFiltro;
	public OperadorLogico operadorLogico;
	private Filtro segundoFiltro;
	
	public FiltroCompuesto(Filtro f1, OperadorLogico op, Filtro f2){
		super();
		this.primerFiltro = f1;
		this.segundoFiltro = f2;
		this.operadorLogico = op;
	}

	@Override
	public boolean filtrarPelicula(Pelicula pelicula) {
		
		return this.operadorLogico.resolver(this.primerFiltro.filtrarPelicula(pelicula), 
				this.segundoFiltro.filtrarPelicula(pelicula));
	} 

}
