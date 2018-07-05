package recomendaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class RecomendacionPeliculasVistasPorAmigos extends Recomendacion {

	// Recomienda aquellas peliculas que fueron evaluadas por 2 o
	//  más amigos con puntaje 3 o superior, ordenadas por el rating de IMDB.
	public List<Pelicula> recomendarPeliculas(UsuarioRegular usuario, SistemaMoovies sistemaMoovies) {
		List<Pelicula> peliculasRecomendadas = new ArrayList<Pelicula>();
		for(Pelicula p: sistemaMoovies.getPeliculas()){
			if(this.cumpleConLaRecomendacion(usuario, p)){
				peliculasRecomendadas.add(p);
			}				
		}
		this.ordenarPorRating(peliculasRecomendadas);
		return peliculasRecomendadas;
	}
	
	private Comparator<Pelicula> comparadorDePeliculas(){
		Comparator<Pelicula> comparador = new Comparator<Pelicula>() {
			public int compare(Pelicula p1, Pelicula p2) {
				return new Float(p2.promedioPuntajes()).compareTo(new Float(p1.promedioPuntajes()));
		    }
		};
		return comparador;
	}
	
	private void ordenarPorRating(List<Pelicula> peliculasRecomendadas) {
		Comparator<Pelicula> comparador = this.comparadorDePeliculas();
		Collections.sort(peliculasRecomendadas, comparador);
	}

	private boolean cumpleConLaRecomendacion(UsuarioRegular usuario, Pelicula pelicula) {
		int cantEvaluaciones = 0;
		for(UsuarioRegular u: usuario.getAmigos()){
			if(u.califico(pelicula) && pelicula.puntajeDe(u) >= 3){
				cantEvaluaciones++;
			}
		}
		return cantEvaluaciones >= 2;
	}


}
