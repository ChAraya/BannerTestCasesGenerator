package cl.api.banner.model;

import oracle.jdbc.internal.OracleTypes;

public class Saaadms {
	
	private String term_code;
	private String appl_no;
	private String admt_code;
	private String styp_code;
	private String site_code;
	private String resd_code;
	private String user;
	private String data_origin;
	private String rowid;
	private String program;
	private String camp_code;
	
	public Saaadms() {
		this.admt_code = null;
		this.styp_code = "N";
		this.site_code = null;
		this.resd_code = "R";
		this.user = null;
		this.data_origin = "JAVA";
	}

	public String getTerm_code() {
		return term_code;
	}

	public void setTerm_code(String term_code) {
		this.term_code = term_code;
	}

	public String getAppl_no() {
		return appl_no;
	}

	public void setAppl_no(String appl_no) {
		this.appl_no = appl_no;
	}

	public String getAdmt_code() {
		return admt_code;
	}

	public void setAdmt_code(String admt_code) {
		this.admt_code = admt_code;
	}

	public String getStyp_code() {
		return styp_code;
	}

	public void setStyp_code(String styp_code) {
		this.styp_code = styp_code;
	}

	public String getSite_code() {
		return site_code;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public String getResd_code() {
		return resd_code;
	}

	public void setResd_code(String resd_code) {
		this.resd_code = resd_code;
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

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getCamp_code() {
		return camp_code;
	}

	public void setCamp_code(String camp_code) {
		this.camp_code = camp_code;
	}

}
