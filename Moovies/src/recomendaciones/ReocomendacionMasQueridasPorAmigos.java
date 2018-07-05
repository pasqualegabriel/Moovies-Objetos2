package recomendaciones;

import java.util.ArrayList;
import java.util.List;

import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class ReocomendacionMasQueridasPorAmigos extends Recomendacion {
	
	// Recomienda aquellas peliculas que mas de la mitad de sus
	//  amigos evaluaron con 4 o mas, y que el todavia no evaluo
	public List<Pelicula> recomendarPeliculas(UsuarioRegular usuario, SistemaMoovies sistemaMoovies) {
		List<Pelicula> peliculasRecomendadas = new ArrayList<Pelicula>();
		for(Pelicula p: sistemaMoovies.getPeliculas()){
			if(this.cumpleConLaRecomendacion(usuario, p)){
				peliculasRecomendadas.add(p);
			}				
		}
		return peliculasRecomendadas;
	}
	
	private boolean cumpleConLaRecomendacion(UsuarioRegular usuario, Pelicula pelicula) {
		float res = 0;
		for(UsuarioRegular u: usuario.getAmigos()){
			if(u.califico(pelicula) && pelicula.puntajeDe(u) >= 4){
				res++;
			}
		}
		return !usuario.califico(pelicula) && res > (usuario.getAmigos().size() / 2);
	}

}
