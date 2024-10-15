package carro.com.api.Model;

public enum UsuarioPermissao {
	
	ADMIN("admin"),
	
	USER("user");
	
	private String permissao;
	
	UsuarioPermissao(String permissao){
		this.permissao = permissao;
	}
	
	public String getRole() {
		return permissao;
	}
}
