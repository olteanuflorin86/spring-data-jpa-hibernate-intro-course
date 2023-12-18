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
				return getAuthorFromRS(resultSet);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(connection, ps, resultSet);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	@Override
	public Author findAuthorByName(String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author where first_name = ? and last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromRS(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, ps, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
	
	@Override
	public Author saveNewAuthor(Author author) {
		Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet resultSet = null;

	    try {
	    	connection = dataSource.getConnection();
	        ps = connection.prepareStatement("INSERT INTO author (first_name, last_name) values (?, ?)");
	        ps.setString(1, author.getFirstName());
	        ps.setString(2, author.getLastName());
	        ps.execute();

	        Statement statement = connection.createStatement();

	        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

	        if (resultSet.next()) {
	        	Long savedId = resultSet.getLong(1);
	            return this.getById(savedId);
	        }

	        statement.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		closeAll(connection, ps, resultSet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	   }

	   return null;
	}
		
	@Override
	public Author updateAuthor(Author author) {
		Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet resultSet = null;

	    try {
	    	connection = dataSource.getConnection();
	        ps = connection.prepareStatement("UPDATE author set first_name = ?, last_name = ? where author.id = ?");
	        ps.setString(1, author.getFirstName());
	        ps.setString(2, author.getLastName());
	        ps.setLong(3, author.getId());
	        ps.execute();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            closeAll(connection, ps, resultSet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return this.getById(author.getId());
	}
	
    @Override
    public void deleteAuthorById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE from author where id = ?");
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try{
                closeAll(connection, ps, null);
            } catch (SQLException ex){

            }
        }
    }
	

	private Author getAuthorFromRS(ResultSet resultSet) throws SQLException {
		Author author = new Author();
		author.setId(resultSet.getLong("id"));
		author.setFirstName(resultSet.getString("first_name"));
		author.setLastName(resultSet.getString("last_name"));
		
		return author;
	}

	private void closeAll(Connection connection, PreparedStatement ps, ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
		    resultSet.close();
		}

		if (ps != null){
		    ps.close();
		}

		if (connection != null){
		    connection.close();
		}
	}

}
