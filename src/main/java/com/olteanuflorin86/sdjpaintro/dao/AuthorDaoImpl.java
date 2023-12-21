package com.olteanuflorin86.sdjpaintro.dao;

import java.sql.SQLException; 
import java.util.List;

import org.springframework.stereotype.Component; 

import com.olteanuflorin86.sdjpaintro.domain.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final EntityManagerFactory emf;
	
	public AuthorDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Author> listAuthorByLastNameLike(String lastName) {
		EntityManager em = getEntityManager();
		
		try {
			Query query = em.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :last_name"); 
			query.setParameter("last_name", lastName + "%");
			List<Author> authors = query.getResultList();
			
			return authors;
		} finally {
			em.close();
		}
	}
	
    @Override
    public Author getById(Long id) {
//        return getEntityManager().find(Author.class, id);
        EntityManager em = getEntityManager();
        Author author = getEntityManager().find(Author.class, id);
        em.close();
        return author;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
//    	TypedQuery<Author> query = getEntityManager().createQuery("SELECT a FROM Author a " + "WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);
//        query.setParameter("first_name", firstName);
//        query.setParameter("last_name", lastName);
//        
//    	return query.getSingleResult();
        EntityManager em = getEntityManager();
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a " + "WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        Author author = query.getSingleResult();
        em.close();
        return author;
    }

    @Override
    public Author saveNewAuthor(Author author) {
//    	EntityManager em = getEntityManager();
//    	em.getTransaction().begin();
//    	em.persist(author);
//    	em.flush();
//    	em.getTransaction().commit();
//    	
//        return author;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
//    	EntityManager em = getEntityManager();
//    	em.joinTransaction();
//    	em.merge(author);
//    	em.flush();
//    	em.clear();
//    	
//        return em.find(Author.class, author.getId());
        EntityManager em = getEntityManager();

        try {
            em.joinTransaction();
            em.merge(author);
            em.flush();
            em.clear();
            Author saveAuthor = em.find(Author.class, author.getId());
            return saveAuthor;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
//    	EntityManager em = getEntityManager();
//    	em.getTransaction().begin();
//    	Author author = em.find(Author.class, id);
//    	em.remove(author);
//    	em.flush();
//    	em.getTransaction().commit();
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    private EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
}
