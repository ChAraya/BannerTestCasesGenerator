package cl.api.banner.controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import cl.api.banner.model.Person;
import cl.api.banner.model.Saaadms;
import cl.api.banner.model.Sgastdn;
import cl.api.banner.utilities.Mapper;
import oracle.jdbc.internal.OracleTypes;

/**
 * SGASTDN API controller
 *
 * @author Juan Chimaja
 * @author Christopher Araya
 */

public class SgastdnController {
	
	public static void create(Connection conn, Person person) throws Exception {
		Sgastdn sgastdn = new Sgastdn();
		//saaadms.setTerm_code(termCode);
		CallableStatement callst = null;
		try {
			String query = """
					{CALL szkutls.p_create_student(
											      p_pidm 			=> :lv_pidm,
											      p_term_code   	=> :lv_term_code,
											      p_appl_no			=> :lv_appl_no,
											      p_program			=> :lv_program,
											      p_camp_code		=> :lv_camp_code,
											      p_data_origin 	=> :lv_data_origin,
											      p_key_seqno_out	=> :lv_key_seqno_out
											      )}""";			
			callst = conn.prepareCall(query);			
			callst.setInt("lv_pidm", person.getPidm());
			callst.setString("lv_term_code", person.getAdmission().getTerm_code());
			callst.setString("lv_appl_no", person.getAdmission().getAppl_no());
			callst.setString("lv_program", person.getAdmission().getProgram());
			callst.setString("lv_camp_code", person.getAdmission().getCamp_code());
			callst.setString("lv_data_origin", sgastdn.getData_origin());
			callst.registerOutParameter("lv_key_seqno_out", OracleTypes.VARCHAR);
			callst.execute();

			sgastdn.setKey_seqno(callst.getString("lv_key_seqno_out"));
			person.setSgastdn(sgastdn);

		} catch (Exception e) {
			throw e;
		} finally {
			if (callst != null && !callst.isClosed())
				callst.close();
		}
	}

}
