package student.com.repository;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import student.com.models.UserBean;
import student.com.service.JPAUtil;


@Repository
public class UserRepository {
	
	public int insertData(UserBean userbean) {
		int i = 0;
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			//em =JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(userbean);
			em.getTransaction().commit();
			i = 1;
		} catch (Exception e) {
			//em.getTransaction().rollback();
			System.out.println(e.getMessage());
			
		} finally {
			em.close();
		}
		return i;
	}
	
	public UserBean getLastAdminCode() {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            String jpql = "SELECT u FROM UserBean u ORDER BY u.id DESC";
            TypedQuery<UserBean> query = em.createQuery(jpql, UserBean.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
	
	public UserBean findByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();;
		UserBean userBean = null;
		try {
			//em = JPAUtil.getEntityManagerFactory().createEntityManager();
			System.out.println("Hi3");
			userBean = em.createQuery("SELECT u FROM UserBean u WHERE u.email=:email", UserBean.class)
					.setParameter("email", email).getSingleResult();
			System.out.println("Hi4");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());

		} finally {
			em.close();
		}
		return userBean;
	}

}
