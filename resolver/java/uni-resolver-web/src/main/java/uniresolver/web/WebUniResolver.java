package uniresolver.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import uniresolver.ResolutionException;
import uniresolver.UniResolver;
import uniresolver.result.ResolveResult;

public abstract class WebUniResolver extends HttpServlet implements HttpRequestHandler, UniResolver {

	private static final long serialVersionUID = -7802449707979028536L;

	private UniResolver uniResolver;

	protected WebUniResolver() {

		super();
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("GET".equals(request.getMethod())) this.doGet(request, response);
		if ("POST".equals(request.getMethod())) this.doPost(request, response);
		if ("PUT".equals(request.getMethod())) this.doPut(request, response);
		if ("DELETE".equals(request.getMethod())) this.doDelete(request, response);
		if ("OPTIONS".equals(request.getMethod())) this.doOptions(request, response);
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	public ResolveResult resolve(String identifier) throws ResolutionException {

		return this.getUniResolver() == null ? null : this.getUniResolver().resolve(identifier);
	}

	@Override
	public ResolveResult resolve(String identifier, String selectServiceType) throws ResolutionException {

		return this.getUniResolver() == null ? null : this.getUniResolver().resolve(identifier, selectServiceType);
	}

	@Override
	public Map<String, Map<String, Object>> properties() throws ResolutionException {

		return this.getUniResolver() == null ? null : this.getUniResolver().properties();
	}

	/*
	 * Helper methods
	 */

	protected static void sendResponse(HttpServletResponse response, int status, String contentType, String body) throws IOException {

		response.setStatus(status);

		if (contentType != null) response.setContentType(contentType);

		response.setHeader("Access-Control-Allow-Origin", "*");

		if (body != null) {

			PrintWriter writer = response.getWriter();
			writer.write(body);
			writer.flush();
			writer.close();
		}
	}

	/*
	 * Getters and setters
	 */

	public UniResolver getUniResolver() {

		return this.uniResolver;
	}

	public void setUniResolver(UniResolver uniResolver) {

		this.uniResolver = uniResolver;
	}
}
