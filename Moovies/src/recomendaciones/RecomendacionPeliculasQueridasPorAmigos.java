package recomendaciones;

import java.util.ArrayList;
import java.util.List;

import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class RecomendacionPeliculasQueridasPorAmigos extends Recomendacion {

	// Recomienda aquellas películas con mayor rating promedio, que al menos un amigo haya evaluado.
	public List<Pelicula> recomendarPeliculas(UsuarioRegular usuario, SistemaMoovies sistemaMoovies) {
		List<Pelicula> peliculasRecomendadas = new ArrayList<Pelicula>();
		for(Pelicula p: sistemaMoovies.getPeliculas()){
			if(this.cumpleConLaRecomendacion(usuario, p, sistemaMoovies)){
				peliculasRecomendadas.add(p);
			}				
		}
		return peliculasRecomendadas;
	}
	
	private boolean cumpleConLaRecomendacion(UsuarioRegular usuario, Pelicula pelicula, SistemaMoovies sistemaMoovies) {
		for(UsuarioRegular u: usuario.getAmigos()){
			if(u.califico(pelicula) && sistemaMoovies.ratingPromedio() <= pelicula.promedioPuntajes()){
				return true;
			}
		}
		return false;
	}

}
