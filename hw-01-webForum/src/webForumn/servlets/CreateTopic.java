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

import webForumn.models.Post;
import webForumn.models.Topic;

@WebServlet("/CreateTopic")
public class CreateTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateTopic() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// must call
		super.init(config);
		
		// pre-create data 
		ArrayList<Post> webPostings = new ArrayList<Post>();
		Post testPost = new Post("John", "Eclipse Problem", "I couldnt find Dynamic Web");
		webPostings.add(testPost);
		
		ArrayList<Post> genPostings = new ArrayList<Post>();
		
	 	// store data in application scope
    	config.getServletContext().setAttribute("webPostings", webPostings);
    	config.getServletContext().setAttribute("genPostings", genPostings);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set response headers
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// create HTML
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html> <html lang='en'>");
		out.println("<head> <title>Create Topic</title> </head>");
		out.println("<body>");
		out.println("<h3><a href='DisplayForum'>All Forums</a> >");
		out.println(request.getParameter("forum") + " > Create Topic</h3>");
		out.println("<form action='CreateTopic' method='post'>");
		out.println("<table border='1' cellpadding='2' cellspacing='2' style='text-align:left'>");
		out.println("<tbody> <tr> <th>Your Name</th> <td><input name='name' type='text' /></td> </tr>");
		out.println("<tr> <th>Subject</th> <td><input name='subject' type='text' /></td> </tr>");
		out.println("<tr> <th>Content</th> <td><textarea cols='40' rows='4' name='content'></textarea></td> </tr>");
		out.println("<tr> <td colspan='2' rowspan='1'><input type='submit' value='Post' /></td> </tr> </tbody>");
		out.println("</table></form></body></html>");

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: FIX DESIGN ISSUES / FIGURE OUT HOW TO CREATE TOPIC
		// get data from form
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// set topicList based on query string
		ArrayList<Post> postList = null;
		ArrayList<Topic> topicList = null;
		
		if (request.getParameter("forum").contains("3")) {
			postList = (ArrayList<Post>) this.getServletContext().getAttribute("webPostings");
			topicList = (ArrayList<Topic>) this.getServletContext().getAttribute("webList");
		}
		else {
			postList = (ArrayList<Post>) this.getServletContext().getAttribute("genPostings");
			topicList = (ArrayList<Topic>) this.getServletContext().getAttribute("genList");
		}
		postList.add(new Post(name, subject, content));
		//topicList.add(new Topic())
		
		
		
		
	}

}
