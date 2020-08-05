package gameModel.requestAndREsponse;

public class LoginAndSingUpRequest {
	private String name;
	private String password;
	public LoginAndSingUpRequest(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public LoginAndSingUpRequest() {
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
}
