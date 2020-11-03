package net.atlassian.iotreport;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/students")
public class StudentService {
	
	private StudentDAO dao = new StudentDAO();//StudentDAO.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> list() throws SQLException, ClassNotFoundException{
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int create(Student student) throws ClassNotFoundException, SQLException, URISyntaxException {
//		int newStdId = 
	    int status = dao.add(student);
//		URI uri = new URI("/students/"+ name);
		return status;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response get(@PathParam("id") int id) {
		Student student = dao.get(id);
		if (student != null) {
			return Response.ok(student, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();

	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("{id}")
	public int update(Student student) throws ClassNotFoundException, SQLException {
//		student.setId(id);
		if(dao.update( student)) {
			return 200;//Response.ok().build();
		}
		return 500;//Response.notModified().build();
	}
	
	
	@DELETE
	@Path("{id}")
	public int delete(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		if(dao.delete(id)) {
			return 200;//Response.ok().build();
		}
		return 500;//Response.notModified().build();
	}
}
