package first;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

public class First extends HttpServlet{

	public void doGet(HttpServletRequest a, HttpServletResponse b) {
		PrintWriter pw = null;
		try {
			System.out.println("HIIII");
			pw = b.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 pw.println(a.getCharacterEncoding());
		pw.print("<a href=\"fu\">Hello World</fu>");
	}
}
