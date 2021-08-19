package cl.api.banner.utilities;

import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

	/**
	 *
	 * Devuelve el String Json mapeado como una lista de objetos del tipo de la
	 * clase pasado por parametro
	 * 
	 * @param json     String con el Json
	 * @param nodoBase Punto de partida para mapear el Json de entrada
	 * @param c        Tipo de objeto a mapear en la lista resultante
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToObject(String json, String nodoBase, Class<T> clazz) throws Exception {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			JsonNode nodeData = node.at(nodoBase);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			return objectMapper.readValue(nodeData.toString(), clazz);

		} catch (Exception e) {
			throw new Exception("Error al querer mapear el Json a una lista de valores. Detalle:" + e.getMessage());
		}

	}

	/**
	 *
	 * Devuelve el String Json mapeado como una lista de objetos del tipo de la
	 * clase pasado por parametro
	 * 
	 * @param json     String con el Json
	 * @param nodoBase Punto de partida para mapear el Json de entrada
	 * @param c        Tipo de objeto a mapear en la lista resultante
	 * @return
	 * @throws Exception
	 */
	public static List<?> jsonToList(String json, String nodoBase, Class c) throws Exception {
		List<?> navigation = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			JsonNode programasNode = node.at(nodoBase);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			navigation = objectMapper.readValue(programasNode.toString(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, c));

		} catch (Exception e) {
			throw new Exception("Error al querer mapear el Json a una lista de valores. Detalle:" + e.getMessage());
		}
		return navigation;
	}

	/**
	 * Dado el Json de entrada, se obtiene el String segun la ruta pasada por
	 * parametro
	 * 
	 * @param json
	 * @param nodoBase
	 * @return
	 * @throws Exception
	 */
	public static String jsonToString(String json, String nodoBase) throws Exception {

		String resultado = null;

		try {

			ObjectMapper mapper0 = new ObjectMapper();
			JsonNode node0 = mapper0.readTree(json.toLowerCase());
			JsonNode node01 = node0.at(nodoBase);
			resultado = node01.asText();

			return resultado;

		} catch (Exception e) {
			throw new Exception("Error al querer mapear el Json a un String. Detalle:" + e.getMessage());
		}
	}

}
