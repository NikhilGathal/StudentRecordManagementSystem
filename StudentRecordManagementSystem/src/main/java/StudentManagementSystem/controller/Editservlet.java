package StudentManagementSystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StudentManagementSystem.dao.StudentDao;
import StudentManagementSystem.dto.Student;

@WebServlet("/edit")
public class Editservlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String course = req.getParameter("course");
		long phone = Long.parseLong(req.getParameter("phone"));
		int fees = Integer.parseInt(req.getParameter("fees"));

		int remainfees = Integer.parseInt(req.getParameter("remainfees"));
		int paidfees = Integer.parseInt(req.getParameter("paidfees"));

		StudentDao dao = new StudentDao();
		List<Student> students = dao.getAllStudents();
		boolean value = true;
		// System.out.println(email);
		for (Student student : students) {
			
			
			if (email.equals(student.getEmail())) {
//				email is already present
				if (student.getId() == id) {
//					means it is id of the person im updating
					break;
				} else {
//					others email
					value = false;
					break;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// int remainfees = 0;
		ServletContext context = getServletContext();
		Student student = new Student();
		int f =0;

		if (course.equals("Java") ) {
			f = Integer.parseInt(context.getInitParameter("Java"));
			student.setFees(f);
			System.out.println("Java fees is " + f);
			System.out.println("Paid fees" + paidfees);
			remainfees = f - paidfees;
			System.out.println("Remain fees is " + remainfees);
		} else if (course.equals("Python")) {
			f = Integer.parseInt(context.getInitParameter("Python"));
			student.setFees(f);
			System.out.println("Python fees is " + f);
			System.out.println("Paid fees" + paidfees);
			remainfees = f - paidfees;
			System.out.println("Remain fees is " + remainfees);
		} else if (course.equals("MERN")) {
			f = Integer.parseInt(context.getInitParameter("MERN"));
			student.setFees(f);
			System.out.println("MERN fees is " + f);
			System.out.println("Paid fees" + paidfees);
			remainfees = f - paidfees;
			System.out.println("Remain fees is " + remainfees);
		} else if (course.equals("Devops")) {
			f = Integer.parseInt(context.getInitParameter("Devops"));
			student.setFees(f);
			System.out.println("Devops fees is " + f);
			System.out.println("Paid fees" + paidfees);
			remainfees = f - paidfees;
			System.out.println("Remain fees is " + remainfees);
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
//		Student student = new Student();
		student.setAddress(address);
		student.setEmail(email);
		student.setFees(f);
		student.setId(id);
		student.setName(name);
		student.setCourse(course);
		student.setPhone(phone);
		student.setRemainfees(remainfees);
		student.setPaidfees(paidfees);
		
		System.out.println("course setting is" + course);
		System.out.println("total fees is " + f);

		if (value) {

			dao.updateStudent(student);
			req.setAttribute("list", students);
			RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("message", "Email already exist");
			req.setAttribute("student", dao.findStudentById(id));
			RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
			dispatcher.include(req, resp);
		}

	}

}
