package moovies;

import java.util.List;

public class Administrador extends Usuario {

	public Administrador(String usuario, String genero, String contrasenia, String nombre,
			String apellido, int edad, String ocupacion, int codigoPostal) {
		
		super(edad,genero,ocupacion,codigoPostal,nombre,apellido);
    	this.setUsuario(usuario);
    	this.setContrasenia(contrasenia);
	} 
	
	// retorna una lista de los usuarios mas acrivos
	public List<UsuarioRegular> usuariosMasActivos(SistemaMoovies sistemaMoovies) {
		
		return sistemaMoovies.usuariosMasActivos();
	}

}