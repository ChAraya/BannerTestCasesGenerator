package cl.api.banner.controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import cl.api.banner.model.Person;
import cl.api.banner.model.Saaadms;
import cl.api.banner.utilities.Mapper;
import oracle.jdbc.internal.OracleTypes;

/**
 * SAAADMS API controller
 *
 * @author Juan Chimaja
 * @author Christopher Araya
 */
public class SaaadmsController {

	public static void create(Connection conn, Person person, String termCode) throws Exception {
		Saaadms saaadms = new Saaadms();
		saaadms.setTerm_code(termCode);
		CallableStatement callst = null;
		try {
			String query = """
					{CALL szkutls.p_create_admission(
								      p_pidm 		=> :lv_pidm,
								      p_term_code   => :lv_term_code,
								      p_data_origin => :lv_data_origin,
								      p_json_out 	=> :lv_json_out
								      )}""";			
			callst = conn.prepareCall(query);			
			callst.setInt("lv_pidm", person.getPidm());
			callst.setString("lv_term_code", saaadms.getTerm_code());
			callst.setString("lv_data_origin", saaadms.getData_origin());
			callst.registerOutParameter("lv_json_out", OracleTypes.VARCHAR);
			callst.execute();

			saaadms = Mapper.jsonToObject(callst.getString("lv_json_out"), "", Saaadms.class);
			person.setAdmission(saaadms);
			System.out.println(person.getAdmission().getTerm_code());

		} catch (Exception e) {
			throw e;
		} finally {
			if (callst != null && !callst.isClosed())
				callst.close();
		}
	}

	public static void createOld(Connection conn, Person person) throws Exception {
		Saaadms saaadms = person.getAdmission();
		CallableStatement callst = null;
		try {
			String query = """
					{CALL sb_admissionsapplication
							.p_create(
								      p_pidm => :lv_pidm,
								      p_term_code_entry           =>:lv_term_code,
								      p_appl_no_inout     => :lv_appl_no_inout,
								      p_appl_date        => Sysdate,
								      p_apst_code        => null,
								      p_apst_date        => Sysdate,
								      p_maint_ind        =>null,
								      p_admt_code        =>:lv_admt_code,
								      p_styp_code         =>:lv_styp_code,
								      p_site_code         => :lv_site_code,
								      p_resd_code           =>:lv_resd_code,
								      p_data_origin         => :lv_data_origin,
								      p_user_id             => :lv_user,
								      p_rowid_out           => :lv_row_id,
								      p_appl_preference     => NULL)}""";

			callst = conn.prepareCall(query);
			callst.setInt("lv_pidm", person.getPidm());
			callst.setString("lv_term_code", saaadms.getTerm_code());
			callst.setString("lv_appl_no_inout", saaadms.getAppl_no());
			callst.setString("lv_admt_code", saaadms.getAdmt_code());
			callst.setString("lv_styp_code", saaadms.getStyp_code());
			callst.setString("lv_site_code", saaadms.getSite_code());
			callst.setString("lv_resd_code", saaadms.getResd_code());
			callst.setString("lv_user", saaadms.getUser());
			callst.setString("lv_data_origin", saaadms.getData_origin());

			callst.registerOutParameter("lv_appl_no_inout", OracleTypes.VARCHAR);
			callst.registerOutParameter("lv_rowid", OracleTypes.VARCHAR);
			callst.execute();

			saaadms.setAppl_no(callst.getString("lv_appl_no_inout"));
			saaadms.setRowid(callst.getString("lv_rowid"));

		} catch (Exception e) {
			throw e;
		} finally {
			if (callst != null && !callst.isClosed())
				callst.close();
		}
	}

}
