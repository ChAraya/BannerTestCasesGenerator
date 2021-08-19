package cl.api.banner.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {

	/**
	 * Obtain json response from API url
	 * 
	 * @param uri
	 * @return json
	 * @throws Exception
	 */
	public static String get(String uri, String apikey) throws Exception {

		String salida = "";
		String stringbuffer;

		try {

			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (apikey != null && !apikey.isEmpty())
				conn.setRequestProperty("x-api-key", apikey);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException(
						"Falla acceso Servicio : HTTP error code : " + conn.getResponseCode() + ". URI:" + uri);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			while ((stringbuffer = br.readLine()) != null) {
				salida = salida + stringbuffer;
			}

			conn.disconnect();

		} catch (Exception e) {
			throw new RuntimeException("Problemas en el Servicio: " + e.getMessage());
		}

		return salida;

	}

}
