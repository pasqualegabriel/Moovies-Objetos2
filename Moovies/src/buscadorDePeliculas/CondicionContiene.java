package buscadorDePeliculas;

import java.util.List;

public class CondicionContiene extends Condicion {
	
	public CondicionContiene(String unCampo){
		super(unCampo);
	}

	@Override
	public boolean resolver(String campo){
		return campo.contains(this.getCampo());
	}

	@Override
	public boolean resolver(List<String> campos) {
		for(String campo: campos){
			if(campo.contains(this.getCampo())){
				return true;
			}
		}
		return false;
	}

}
