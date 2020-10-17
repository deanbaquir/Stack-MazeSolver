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

import webForumn.models.Topic;



@WebServlet("/DisplayTopics")
public class DisplayTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayTopics() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
    	// pre-create data
    	// hard coding two topics just for testing
    	ArrayList<Topic> webList = new ArrayList<Topic>();
    	Topic testTopic = new Topic(1, "Eclipse problem", "John", 1, "09/19/2020 8:34PM");
    	Topic testTopic2 = new Topic(2, "HW1 HELP!", "Jane", 0, "09/30/2020 9:01AM");
    	webList.add(testTopic);
    	webList.add(testTopic2);
    	
    	ArrayList<Topic> genList = new ArrayList<Topic>();
    	
    	// store data in application scope
    	config.getServletContext().setAttribute("webList", webList);
    	config.getServletContext().setAttribute("genList", genList);
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// set response headers
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
        // create HTML
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html> <html lang='en'>");
    	out.println("<head> <title>Topic List</title> </head>");
		out.println("<body>");
		out.println("<h3><a href='DisplayForum'>All Forums</a> >");
		out.println(request.getParameter("forum"));		
		out.println("</h3><table border='1' style='width:500px;'>");
		out.println("<tr> <th colspan='2'>Topic</th> <th>Author</th>");
		out.println("<th>Replies</th> <th colspan='2'>Last Post</th> </tr>");
		
		try {
			// set topicList based on query string
			ArrayList<Topic> topicList = null;
			if (request.getParameter("forum").contains("3")) {
				topicList = (ArrayList<Topic>) this.getServletContext().getAttribute("webList");
			}
			else {
				topicList = (ArrayList<Topic>) this.getServletContext().getAttribute("genList");
			}
		
			// loop through and display data
			for (Topic topic : topicList) {
				out.println("<tr> <td colspan='2'> <a href='#'>" + topic.getSubject() + "</a></td>");
				out.println("<td>" + topic.getAuthor() + "</td>");
				out.println("<td>" + topic.getReplies() + "</td>");
				out.println("<td colspan='2'>" + topic.getLastPost() + "</td></tr>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("DisplayForum");
			return;
		}
		out.println("</table>");
		out.println("<p><a href='CreateTopic?forum='" + request.getParameter("forum")  + "'> Create Topic </a></p>");
		out.println("</body></html>");
	}
	
}
