package first;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Second extends HttpServlet{

	public void doGet(HttpServletRequest a, HttpServletResponse b) throws ServletException, IOException {
		PrintWriter pw = b.getWriter();
		pw.print("<a href=\"fu\">Hello World</fu>");
		RequestDispatcher rd = a.getRequestDispatcher("fu");
		rd.include(a, b);
		rd = a.getRequestDispatcher("second.html");
		rd.include(a, b);
	}
}
