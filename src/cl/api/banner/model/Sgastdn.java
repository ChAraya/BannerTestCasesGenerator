package cl.api.banner.model;

public class Sgastdn {
	private String key_seqno;
	private String user;
	private String data_origin;
	
	public Sgastdn() {
		this.data_origin = "JAVA";
	}

	public String getKey_seqno() {
		return key_seqno;
	}

	public void setKey_seqno(String key_seqno) {
		this.key_seqno = key_seqno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getData_origin() {
		return data_origin;
	}

	public void setData_origin(String data_origin) {
		this.data_origin = data_origin;
	}
	
	

}
