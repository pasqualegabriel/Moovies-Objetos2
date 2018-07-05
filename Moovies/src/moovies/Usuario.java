package moovies;

public class Usuario {

	private String usuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private int edad;
	private String ocupacion;
	private int codigoPostal;
	private String genero;
	
	public Usuario(int edad, String genero, String ocupacion, 
			int codigoPostal, String nombre, String apellido) {
		
		this.setNombre(nombre);
		this.setApellido(apellido);		
		this.setEdad(edad);
		this.setOcupacion(ocupacion);
		this.setCodigoPostal(codigoPostal); 
		this.setGenero(genero);
	}
	
	// retorna la edad del usuario
	public int getEdad() {
		return edad;
	}

	// cambia la edad del usuario
	public void setEdad(int edad) {
		this.edad = edad;
	}

	// retorna el nombre del usuario
	public String getNombre() {
		return nombre;
	}

	// cambia el nombre del usuario
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// retorna el apellido del usuario
	public String getApellido() {
		return apellido;
	}
	
	// cambia el apellido del usuario
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	// retorna la ocupacion del usuario
	public String getOcupacion() {
		return ocupacion;
	}

	// cambia la ocupacion del usuario
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	//retorna el codigo postal del usuario
	public int getCodigoPostal() {
		return codigoPostal;
	}

	//cambia el codigo postal del usuario
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	//retorna el genero del usuario
	public String getGenero() {
		return genero;
	}

	// cambia el genero del usuario
	public void setGenero(String genero) {
		this.genero = genero;
	}

	// retorna el nombre de usuario
	public String getUsuario() {
		return usuario;
	}

	// cambia el nombre de usuario
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// retorna la contrasenia del usuario
	public String getContrasenia() {
		return contrasenia;
	}

	// cambia la contrasenia del usuario
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
