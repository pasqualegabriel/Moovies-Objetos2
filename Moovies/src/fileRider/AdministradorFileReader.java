package fileRider;

import java.util.List;

import moovies.Calificacion;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class AdministradorFileReader {

	public UsuarioRegular buscarUsuario(List<UsuarioIdFileReader> usuariosReaders, int idUsuario) {
		
		for(UsuarioIdFileReader ur: usuariosReaders){
			
			if(ur.getIdUsuario() == idUsuario){
				
				return ur.getUsuario();
			}
		}
		return null;
	}
	
	public void importarFileReader(SistemaMoovies sistemaMoovies){
		
		CSVFileReader<UsuarioIdFileReader> readerU = new UsuarioFileReader("src\\data\\u.user.csv");
		List<UsuarioIdFileReader> usuariosReaders = readerU.readFile();
		
		CSVFileReader<AmistadIdUsuarios> readerA = new AmistadFileReader("src\\data\\u.connection.csv");
		List<AmistadIdUsuarios> amistades = readerA.readFile();
		
		CSVFileReader<PeliculaIdFileReader> readerP = new PeliculaFileReader("src\\data\\u.item.new.csv");
		List<PeliculaIdFileReader> peliculasReader = readerP.readFile();
		
		CSVFileReader<CalificacionIdPeliculaYUsuario> readerC = new CalificacionFileRider("src\\data\\u.data.new.csv");
		List<CalificacionIdPeliculaYUsuario> calificaciones = readerC.readFile();
			
		for(UsuarioIdFileReader u: usuariosReaders){
			
			for(AmistadIdUsuarios a: amistades){
				
				if(a.getIdFriend()==u.getIdUsuario()){
					
					UsuarioRegular usuarioAmigo1 = this.buscarUsuario(usuariosReaders, a.getIdFriend());
					u.getUsuario().agregarAmistad(usuarioAmigo1);
				}
				if(a.getIdUser()==u.getIdUsuario()){
					
					UsuarioRegular usuarioAmigo2 = this.buscarUsuario(usuariosReaders, a.getIdUser());
					u.getUsuario().agregarAmistad(usuarioAmigo2);
				}
			}
			sistemaMoovies.addUsuario(u.getUsuario());
		}
		
		for(PeliculaIdFileReader pr: peliculasReader){
			
			for(CalificacionIdPeliculaYUsuario c: calificaciones){
				
				if(c.getIdPelicula()==pr.getIdPelicula()){
					
					UsuarioRegular usuarioQueCalifico = this.buscarUsuario(usuariosReaders, c.getIdUsuario());
						Calificacion calificacion = new Calificacion(usuarioQueCalifico,
								pr.getPelicula(), c.getCalificacion());
						pr.getPelicula().agregarCalificacion(calificacion);
				}
			}
			sistemaMoovies.addPelicula(pr.getPelicula());
		}

	}

}
