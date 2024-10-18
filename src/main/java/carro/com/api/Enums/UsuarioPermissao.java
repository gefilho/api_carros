package carro.com.api.Enums;

public enum UsuarioPermissao {

	ADMIN("admin"),
	USER("user");
	
	private String permissao;
	
	UsuarioPermissao(String permissao){
		this.permissao = permissao;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
