package com.olteanuflorin86.sdjpaintro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final DataSource dataSource;
	
	public AuthorDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Author getById(Long id) {

		Connection connection = null;
//		Statement statement = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
			ps = connection.prepareStatement("SELECT * FROM author WHERE id = ?");
			ps.setLong(1, id);
			resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				Author author = new Author();
				author.setId(id);
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				
				return author;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
//				if(statement != null) {
//					statement.close();
//				}
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
