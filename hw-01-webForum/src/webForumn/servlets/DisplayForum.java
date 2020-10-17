package webForumn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webForumn.models.Forum;
import webForumn.models.Topic;



@WebServlet("/DisplayForum")
public class DisplayForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DisplayForum() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		// must call
		super.init(config);
		
		// pre-create data & store in application scope
		ArrayList<Forum> forums = new ArrayList<Forum>();
		Forum genForum = new Forum("General Discussion");
		Forum webForum = new Forum("CS3220 Web Programming");
		
		ArrayList<Topic> topics = new ArrayList<Topic>();
		
		
		config.getServletContext().setAttribute("forums", forums);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data from application scope
		Forum genForum = (Forum) this.getServletContext().getAttribute("genForum");
		Forum webForum = (Forum) this.getServletContext().getAttribute("webForum");
		
        // set response header & encoding
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        // create HTML
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html> <html lang='en'>");
    	out.println("<head> <title>All Forums</title> </head>");
		out.println("<body>");
		out.println("<h3> All Forums </h3>");
		out.println("<table border='1' style='width:300px;'>");
		out.println("<tr> <th colspan='2'>Forum</th> <th>Topics</th> </tr>");
		out.println("<tr> <td colspan='2'> <a href='DisplayTopics?forum=" + genForum.getTitle() + "'>" + genForum.getTitle() + "</a></td>");
		out.println("<td>" + genForum.getNumTopics() + "</td>");
		out.println("<tr> <td colspan='2'> <a href='DisplayTopics?forum=" + webForum.getTitle() + "'>" + webForum.getTitle() + "</a></td>");
		out.println("<td>" + webForum.getNumTopics() + "</td>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
