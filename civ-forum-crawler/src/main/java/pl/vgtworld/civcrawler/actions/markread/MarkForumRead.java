package pl.vgtworld.civcrawler.actions.markread;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.entities.ForumReadMarker.Executions;
import pl.vgtworld.civcrawler.services.ForumReadMarkersService;

@WebServlet("/mark-forum-read")
public class MarkForumRead extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	ForumReadMarkersService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = getLoggedUser(req);
		if (user == null) {
			//TODO Display proper message for visitor.
			return;
		}
		
		service.markForumRead(user.getId(), Executions.MANUAL);
		resp.sendRedirect(req.getContextPath() + "/view-unread?markRead=true");
	}
}