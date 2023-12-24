package com.olteanuflorin86.sdjpaintro.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.olteanuflorin86.sdjpaintro.domain.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AuthorDaoHibernate implements AuthorDao {
	
    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

	@Override
	public Author getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author saveNewAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthorById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
        EntityManager em = getEntityManager();

        try {
            String hql = "SELECT a FROM Author a where a.lastName = :lastName ";

            if (pageable.getSort().getOrderFor("firstname") != null) {
                hql = hql + " order by a.firstName " + pageable.getSort().getOrderFor("firstname")
                        .getDirection().name();
            }

            TypedQuery<Author> query = em.createQuery(hql, Author.class);

            query.setParameter("lastName", lastname);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());

            return query.getResultList();
        } finally {
            em.close();
        }
	}
	
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

}
