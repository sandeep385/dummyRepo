

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();

		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setMobile(request.getParameter("mobile"));

		boolean isRegister = ferService.registration(user);

		PrintWriter out = response.getWriter();

		if (isRegister) {
			out.println("User registered successfully");

			request.getRequestDispatcher("Login.html").include(request, response);
		} else {
			out.println("User registration failed");
			request.getRequestDispatcher("Registration.html").include(request, response);
		}

	}

	@Override
	public void destroy() {
		ferService = null;
		super.destroy();
	}

}
