package logic.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspChangePage {

	public void forwardPage(String jspPage, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		try {
			rd.forward(request, response);
		}catch(ServletException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Servlet exception");
		} catch (IOException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "IOException");
		}
	}
}
