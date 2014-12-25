package com.vivek.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/json")
@Path("students")
@Singleton
public class StudentResource {
	ConnectionFactory cf = null;

	public StudentResource() {
		cf = new ConnectionFactory();
	}

	@GET
	public List<StudentDTO> getStudents() {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		cf = new ConnectionFactory();
		java.sql.ResultSet rs = cf.getResultSet("select * from students");
		try {
			while (rs.next()) {
				StudentDTO student = new StudentDTO();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setMaths(rs.getInt("maths"));
				student.setPhysics(rs.getInt("physics"));
				student.setChemistry(rs.getInt("chemistry"));
				
				students.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnections();

		return students;
	}

	public void closeConnections() {
		try {
			if (cf != null) {
			}
		} finally {
			try {
				cf.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
