package kr.ac.mjc.jacob.java.jdbc.raw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

import kr.ac.mjc.jacob.java.jdbc.Student;

/**
 * p.863 예제 16-3 수정<br>
 * 수정
 */
public class JdbcUpdateEx {

	/**
	 * 학생 1명 수정
	 */
	public int updateStudent(Student student) {
		int updatedRows = 0;
		DataSource ds = new MariaDbDataSource(
				"jdbc:mariadb://localhost:3306/jdbc?user=jdbc&password=javaprogramming");
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"update student set name=?, dept=? where id=?")) {
			ps.setString(1, student.getName());
			ps.setString(2, student.getDept());
			ps.setString(3, student.getId());
			updatedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}

	public static void main(String[] args) {
		JdbcUpdateEx ex = new JdbcUpdateEx();

		// 학생 한명을 수정한다.
		Student student = new Student("5", "안철수", "국민의당");
		int updatedRows = ex.updateStudent(student);
		if (updatedRows > 0)
			System.out.println("학생을 수정했습니다. " + student);
		else
			System.out.println("학생이 없어서 수정할 수 없습니다. " + student);
	}
}
