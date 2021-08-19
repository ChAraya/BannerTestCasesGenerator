package cl.api.banner.utilities;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Http {
	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public String get(String uri, String apikey) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri))
				.setHeader("User-Agent", "Java 11 HttpClient").build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// print status code
		//System.out.println(response.statusCode());

		// print response body
		// System.out.println(response.body());
		return response.body();
	}

}
