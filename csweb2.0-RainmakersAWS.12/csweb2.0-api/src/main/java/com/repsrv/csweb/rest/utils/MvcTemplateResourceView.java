package com.repsrv.csweb.rest.utils;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.InternalResourceView;

public class MvcTemplateResourceView extends InternalResourceView {
	public static String template = "viewTemplate.jsp";

	private static final String WEB_JSP_PATH = "/WEB-INF/jsp/";

	private static final Logger logger = LoggerFactory.getLogger(MvcTemplateResourceView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dispatcherPath = prepareForRendering(request, response);
		String jspToRender = dispatcherPath.replace("/WEB-INF/jsp/", "");
		System.out.println("IN THE WAY!!!!!!!!!!");
		if (!"login.jsp".equals(jspToRender)) {
			exposeModelAsRequestAttributes(model, request);
			// set original view being asked for as a request parameter
			request.setAttribute("partial", "../" + jspToRender);
			response.setContentType(DEFAULT_CONTENT_TYPE);
			// force everything to be template.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/template/viewTemplate.jsp");
			rd.forward(request, response);
		} else {
			super.renderMergedOutputModel(model, request, response);
		}
	}

	public static String getTemplate() {
		return template;
	}

	public static void setTemplate(String template) {
		MvcTemplateResourceView.template = template;
	}

}
