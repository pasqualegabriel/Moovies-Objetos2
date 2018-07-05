package fileRider;

public class AmistadIdUsuarios {
	
	private int idUser;
	private int idFriend;
	
	public AmistadIdUsuarios(int idUser, int idFriend){
		
		this.idFriend=idFriend;
		this.idUser=idUser;
	}

	public int getIdUser() {
		return idUser;
	}

	public int getIdFriend() {
		return idFriend;
	}

}
