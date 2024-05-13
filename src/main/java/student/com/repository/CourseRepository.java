package student.com.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import student.com.models.CourseBean;
import student.com.models.UserBean;
import student.com.service.JPAUtil;


@Service("courseDao")
@Component
public class CourseRepository {

	
	public int insertCourse(CourseBean courseBean) {
		int i = 0;
		EntityManager em = null;
		
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(courseBean);
			em.getTransaction().commit();
			i = 1;
			
		}finally {
			em.close();
		}
		return i;
	}


	public int updateCourse(CourseBean courseBean) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		int i =0;
		
		try {
			transaction.begin();
			
			Query query = em.createQuery(
					"UPDATE CourseBean c SET c.courseId = :courseId, c.name = :name WHERE c.id = :id");
				query.setParameter("courseId", courseBean.getCourseId());
				query.setParameter("name", courseBean.getName());
				query.setParameter("id", courseBean.getId());
				
				i = query.executeUpdate();
				
				transaction.commit();
		
		}catch(Exception e){
			if(transaction.isActive() ) {
				transaction.rollback();
			}
		}finally {
			em.close();
		}
		return i;
	}


	public int deleteCourse(int id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		int i = 0;
		
		try {
			transaction.begin();
			
			Query query = em.createQuery("DELETE FROM Course c  WHERE c.id = :id");
			query.setParameter("id", id);
			
			i = query.executeUpdate();
			
		}catch(Exception e){
			if(transaction.isActive() ) {
				transaction.rollback();
			}
		}finally {
			em.close();
		}
		
		return i;
	}


	public CourseBean selectOneCourse(int id) {
	    EntityManager em = null;
	    CourseBean courseBean = null;

	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();

	        courseBean = em.createQuery("SELECT c FROM CourseBean c WHERE c.id = :id", CourseBean.class)
	                  .setParameter("id", id)
	                  .getSingleResult();

	    } catch (NonUniqueResultException e) {
	        System.out.println(e);
	    }
	    finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return courseBean;
	}

	public List<CourseBean> selectAllCourse() {
		 EntityManager em = null; 
	      List<CourseBean> courseList;
	      
	      try {
	    	  em = JPAUtil.getEntityManagerFactory().createEntityManager(); 
	    	  courseList = em.createQuery("SELECT c FROM CourseBean c",CourseBean.class).getResultList();
	      }finally {
	    	  if(em != null) {
	    		  em.close();
	    	  }
	      }
		return courseList;
	}


	public int getCount() {
	    EntityManager em = null;
	    int count = 0;

	    try {
	    	System.out.println("Hi1");
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        
	        Query query = em.createQuery("SELECT c.courseId FROM CourseBean c ORDER BY c.courseId DESC");
	        query.setMaxResults(1);
	        Object result = query.getSingleResult();
	        System.out.println("Hi2");

	        if (result != null) {
	            String maxCourseId = result.toString();
	            System.out.println("Hi"+maxCourseId);
	            if (maxCourseId != null) {
	                count = Integer.parseInt(maxCourseId.replaceAll("\\D+", ""));
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (em != null) {
	            em.close(); 		        }
	    }

	    return count;
	}
	
	
	
	public CourseBean selectByCourseId(String courseId) {
		EntityManager em = null;
		CourseBean courseBean;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			courseBean = em.createQuery("SELECT c FROM CourseBean c WHERE c.courseId = :courseId", CourseBean.class)
					.setParameter("courseId", courseId).getSingleResult();
		} finally {
			em.close();
		}
		return courseBean;
	}


	public long getCourseCount() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(c) FROM CourseBean c", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }
}
