package cl.api.banner.controller;

import java.sql.CallableStatement;
import java.sql.Connection;

import cl.api.banner.model.Person;
import oracle.jdbc.internal.OracleTypes;

/**
 * SPAIDEN API controller
 *
 * @author Luis Ponce
 * @author Christopher Araya
 * @author Juan Chimaja
 */
public class SpaidenController {
	public static void create(Connection conn, Person person) throws Exception {
		CallableStatement callst = null;
		try {
			String query = """
					{CALL gb_identification
							.p_create(	p_id_inout      => :lv_id,
										p_last_name     => :lv_last_name,
										p_first_name    => :lv_first_name,
										p_entity_ind    => :lv_entity_ind,
										p_user          => :lv_user,
										p_origin        => :lv_origin,
										p_data_origin   => :lv_data_origin,
										p_pidm_inout    => :lv_pidm,
										p_rowid_out     => :lv_rowid)}""";

			callst = conn.prepareCall(query);
			callst.setString("lv_id", person.getSpriden_id());
			callst.setString("lv_last_name", person.getSpriden_last_name());
			callst.setString("lv_first_name", person.getSpriden_first_name());
			callst.setString("lv_entity_ind", person.getSpriden_entity_ind());
			callst.setString("lv_user", person.getSpriden_user());
			callst.setString("lv_origin", person.getSpriden_origin());
			callst.setString("lv_data_origin", person.getSpriden_data_origin());

			callst.registerOutParameter("lv_id", OracleTypes.VARCHAR);
			callst.registerOutParameter("lv_pidm", OracleTypes.INTEGER);
			callst.registerOutParameter("lv_rowid", OracleTypes.VARCHAR);
			callst.execute();

			person.setPidm(callst.getInt("lv_pidm"));
			person.setSpriden_id(callst.getString("lv_id"));

		} catch (Exception e) {
			throw e;
		} finally {
			if (callst != null && !callst.isClosed())
				callst.close();
		}
	}
}
