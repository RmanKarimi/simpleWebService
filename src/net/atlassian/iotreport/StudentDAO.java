package net.atlassian.iotreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
//	private static StudentDAO instance;
	private static List<Student> data = new ArrayList<>();
	private final String url = "jdbc:postgresql://localhost:5432/studentdb";
	private final String user= "student";
	private final String password= "123456789";

//	static {
//		data.add(new Student(1, "arman", "2001-10-10" ));
//		data.add(new Student(2,"david budd", "2009-10-11"));
//	}
	
//	private StudentDAO() {	}
//	public static StudentDAO getInstance() {
//		if (instance == null) {
//			instance = new StudentDAO();
//		}
//		return instance;
//	}
	
	public Connection getConnection() throws ClassNotFoundException {
		Connection con=null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
			if(con != null) {
				System.out.println("connected to postgresql successfully");
			}else {
				System.out.println("failed to connect to postgresql");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public List<Student> getAll() throws SQLException, ClassNotFoundException{
		List<Student> std = new ArrayList<Student>();
		ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM student;");
		while (rs.next()) {
			Student student= new Student();
			student.setId(rs.getInt(1));
			student.setName(rs.getString(2));
			student.setBirthdate(rs.getString(3));
			std.add(student);
		}
		return std;//new ArrayList<Student>(data);
	}
	
	public int add(Student student) throws ClassNotFoundException, SQLException {
		if(student.getName().contains("rman")) {
			return 222;
		}else {
		String insert_query="INSERT INTO student (name,birthdate)"
				+ " VALUES ("+ "'" +student.getName()+ "'"+","+"'" +student.getBirthdate()+ "'" +");";
		System.out.println(insert_query);
		getConnection().createStatement().executeUpdate(insert_query);

		return 200;
		}
	}
	
	public Student get(int id) {
		Student studentToFind = new Student(id);
		int index = data.indexOf(studentToFind);
		if (index >= 0) {
			return data.get(index);
		}
			return null;	
	}
	
	
	public boolean update(Student student) throws ClassNotFoundException, SQLException {

		String update_query = "UPDATE student SET name="+"'" +student.getName()+"'"+ 
				",birthdate="+"'"+student.getBirthdate()+"'"
				+" WHERE id=" +student.getId()+ ";";
		System.out.println(update_query);
		getConnection().createStatement().executeUpdate(update_query);
		return true;
	}
	
	public boolean delete(int id) throws ClassNotFoundException, SQLException {

		String delete_query="DELETE FROM student WHERE id="+id+";";
		getConnection().createStatement().executeUpdate(delete_query);
		return true;
		
	}
}
