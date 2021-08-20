package cl.api.banner.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.api.banner.controller.*;
//import cl.api.banner.controller.SpaidenController;
import cl.api.banner.model.Person;
import cl.api.banner.model.Spaiden;
import cl.api.banner.utilities.Http;
import cl.api.banner.utilities.DataHandler;
import cl.api.banner.utilities.Mapper;
import cl.api.banner.utilities.PropertyReader;

/**
 * Java application that allow to generate multiple test cases in Banner ERP by
 * Ellucian.
 *
 * @author Luis Ponce
 * @author Christopher Araya
 * @author Juan Chimaja
 */
public class Main {
	Properties props = new Properties();
	private static HashMap<String, String> propertiesMap = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {

		DataHandler dataHandler = new DataHandler();
		Spaiden spaiden = new Spaiden();

		try {

			// Obtain configuration properties.
			// FileInputStream ip = new FileInputStream("config.properties");
			// Properties p = new Properties();
			// p.load(ip);

			PropertyReader pr = new PropertyReader("config");
			HashMap<String, String> hs = pr.getPropertyAsHashMap();

			Scanner sn = new Scanner(System.in);
			boolean salir = false;
			int opcion, cantidad; // Guardaremos la opcion del usuario
			String termCode = "";

			System.out.println("¿Cuantas personas desea crear?");
			cantidad = sn.nextInt();
			System.out.println("Espere... Generando casos");
			/* Creando personas */
			if (cantidad > 0) {
				for (int i = 0; i < cantidad; i++) {
					spaiden.addPerson(createPerson(dataHandler, hs));
				}
				// spaiden.printList();
				System.out.println("¿Que más desea realizar?");
				while (!salir) {

					System.out.println("1. Generar SAAADMS");
					System.out.println("2. Generar SAAADMS y SGASTDN");
					System.out.println("3. Generar SAAADMS, SGASTDN y DEUDA");
					System.out.println("4. Salir");
					System.out.println("Escribe una de las opciones");
					opcion = sn.nextInt();

					if (opcion != 4) {
						System.out.println("Ingrese Periodo:");
						termCode = sn.next();
					}

					switch (opcion) {
					case 1:
						createSaaadms(dataHandler, hs, spaiden, termCode);
						spaiden.printList();
						break;
					case 2:
						System.out.println("Has seleccionado la opcion 2");
						createSaaadms(dataHandler, hs, spaiden, termCode);
						createSgastdn(dataHandler, hs, spaiden);
						spaiden.printList();
						break;
					case 3:
						System.out.println("Has seleccionado la opcion 3");
						spaiden.printList();
						break;
					case 4:
						salir = true;
						spaiden.printList();
						System.out.println("Menu terminado");
						break;
					default:
						System.out.println("Solo números entre 1 y 4");
					}

				}
			}

			// Print the created person.
			// System.out.println((new
			// ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(person));

		} catch (Exception e) {

			throw e;

		} finally {
			if (dataHandler.conn != null && !dataHandler.conn.isClosed())
				dataHandler.conn.close();
		}

	}

	private static Person createPerson(DataHandler dataHandler, HashMap<String, String> hs) {
		// Obtain fake name from API.
		String resultado;
		try {
			Http http = new Http();
			resultado = http.get(hs.get("apiPersona"), null);
			// Map the json from API to an Object.
			Person person = new Person();
			person = Mapper.jsonToObject(resultado, "", Person.class);
			String[] names = person.getName().split(" ");
			person.setSpriden_first_name(names[0]);
			person.setSpriden_last_name(names[1]);

			// Open DB Connection
			dataHandler.jdbcUrl = hs.get("jdbcUrl");
			dataHandler.userid = hs.get("userid");
			dataHandler.password = hs.get("password");
			dataHandler.getDBConnection();

			// Create the person in Banner.
			SpaidenController.create(dataHandler.conn, person);
			return person;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static void createSaaadms(DataHandler dataHandler, HashMap<String, String> hs, Spaiden spaiden,
			String termCode) {

		try {
			// Open DB Connection
			dataHandler.jdbcUrl = hs.get("jdbcUrl");
			dataHandler.userid = hs.get("userid");
			dataHandler.password = hs.get("password");
			dataHandler.getDBConnection();

			spaiden.getPersonList().forEach((person) -> {

				try {
					SaaadmsController.create(dataHandler.conn, person, termCode);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createSgastdn(DataHandler dataHandler, HashMap<String, String> hs, Spaiden spaiden) {

		try {
			// Open DB Connection
			dataHandler.jdbcUrl = hs.get("jdbcUrl");
			dataHandler.userid = hs.get("userid");
			dataHandler.password = hs.get("password");
			dataHandler.getDBConnection();

			spaiden.getPersonList().forEach((person) -> {

				try {
					SgastdnController.create(dataHandler.conn, person);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
