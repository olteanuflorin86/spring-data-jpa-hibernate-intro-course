package com.olteanuflorin86.sdjpaintro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.olteanuflorin86.sdjpaintro.domain.Author;

public class AuthorExtractor implements ResultSetExtractor<Author> {
	
    @Override
    public Author extractData(ResultSet rs) throws SQLException, DataAccessException {
    	rs.next();
        
    	return new AuthorMapper().mapRow(rs, 0);
    }
    
}
