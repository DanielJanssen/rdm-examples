package de.th_koeln.example.servlet;

import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StartServlet extends GenericServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	public void service(ServletRequest aReq, ServletResponse aRes) throws ServletException, IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init(ServletConfig aConfig) throws ServletException {
		super.init(aConfig);
		synchronized (PropertyEditorManager.class) {
			String[] searchPath = PropertyEditorManager.getEditorSearchPath();
			HashSet<String> searchPathAsSet = new HashSet<String>(Arrays.asList(searchPath));
			searchPathAsSet.add("de.th_koeln.example.propertyeditor");

			PropertyEditorManager.setEditorSearchPath(searchPathAsSet.toArray(new String[0]));

		}
	}
}
