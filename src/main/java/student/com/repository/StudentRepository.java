package student.com.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import student.com.models.StudentBean;
import student.com.service.JPAUtil;


@Service("studentDao")
@Component
public class StudentRepository {

	public int insertStudent(StudentBean studentBean) {
        int i = 0;
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            //em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            
            em.persist(studentBean);

            em.getTransaction().commit();
            i = 1;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return i;
    }

	public int updateStudent(StudentBean studentBean) {
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction transaction = em.getTransaction();
	      
	      int i = 0;
	      
	      try {
	    	  transaction.begin();
	    	  
	    	  Query query = em.createQuery(
	    			  "UPDATE StudentBean s SET s.studentId = :studentId,s.name = :name,s.dob = :dob,s.gender = :gender, s.phone = :phone,s.education = :education,s.courseId = :courseId,s.photoPath = :photoPath WHERE s.id = :id");
	    	  
	    	  
	    	  query.setParameter("studentId", studentBean.getStudentId());
	    	  query.setParameter("name", studentBean.getName());
	    	  query.setParameter("dob", studentBean.getDob());
	    	  query.setParameter("gender", studentBean.getGender());
	    	  query.setParameter("phone", studentBean.getPhone());
	    	  query.setParameter("education", studentBean.getEducation());
	    	  query.setParameter("photoPath", studentBean.getPhotoPath());
	    	  query.setParameter("id", studentBean.getId());
	    	  i = query.executeUpdate();
	    	  
	    	  transaction.commit();
	    	  
	      }catch(Exception e) {
	    	  if (transaction.isActive()) {
	              transaction.rollback(); 
	          }
	       
	      } finally {
	          em.close(); 
	      }
	      
	      return i;
	  }

	
	public int deleteStudent(int id) {
	      EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction transaction = em.getTransaction();
	      int i = 0;
	      try {
	          transaction.begin();

	          Query query = em.createQuery("DELETE FROM StudentBean s WHERE s.id = :id");
	          query.setParameter("id", id);

	          i = query.executeUpdate();

	          transaction.commit(); 
	      } catch (Exception e) {
	          if (transaction.isActive()) {
	              transaction.rollback(); 
	          }
	         
	      } finally {
	          em.close(); 
	      }

	      return i;
	}
	public int getCount() {
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	    int count = 0;

	    try {
	        System.out.println("Hi1");
	        //em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        
	        Query query = em.createQuery("SELECT s.studentId FROM StudentBean s ORDER BY s.studentId DESC");
	        query.setMaxResults(1);
	        Object result = query.getSingleResult();
	        System.out.println("Hi2");

	        if (result != null) {
	            String maxCourseId = result.toString();
	            System.out.println("Hi" + maxCourseId);
	            if (maxCourseId != null) {
	                count = Integer.parseInt(maxCourseId.replaceAll("\\D+", ""));
	            }
	        } else {
	            System.out.println("No records found in the table.");
	        }
	    } catch (NoResultException e) {
	        // Handle the case where no result is found
	        System.out.println("No records found in the table.");
	        System.out.println(count);
	        return count;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return count;
	}

	

	public StudentBean selectOneStudent(int id) {
		EntityManager em = null;
		StudentBean studentBean;
		
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			studentBean = em.createQuery("SELECT s FROM StudentBean s WHERE s.id = :id",StudentBean.class)
					.setParameter("id", id).getSingleResult();
		}finally {
			em.close();
		}
		return studentBean;
	}

	public List<StudentBean> selectAllStudent() {
		EntityManager em = null;
		List<StudentBean> studentList;
		
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			studentList = em.createQuery("SELECT s FROM StudentBean s",StudentBean.class).getResultList();
		}finally {
			if(em != null) {
				em.close();
			}
		}
		return studentList;
	}

	

	public List<StudentBean> searchStudent(String studentId, String studentName, String courseName) {    
	    EntityManager em = null;
	    List<StudentBean> studentList = new ArrayList<>();
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        String jpql = "SELECT s FROM Student s " + 
	                      "JOIN s.courses c " + 
	                      "WHERE s.studentId LIKE :studentId " + 
	                      "AND s.name LIKE :studentName " + 
	                      "AND c.cname LIKE :courseName";
	        TypedQuery<StudentBean> query = em.createQuery(jpql, StudentBean.class);
	        query.setParameter("studentId", "%" + studentId + "%");
	        query.setParameter("studentName", "%" + studentName + "%");
	        query.setParameter("courseName", "%" + courseName + "%");

	        studentList = query.getResultList();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return studentList;
	}
	
	public boolean updateStatus(int id, int delete) {
	    EntityManager em = null;
	    EntityTransaction transaction = null;
	    boolean success = false;
	    
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        transaction = em.getTransaction();
	        transaction.begin();
	        
	        Query query = em.createQuery("UPDATE StudentBean s SET s.status = :deleteStatus WHERE s.id = :studentId");
	        query.setParameter("deleteStatus", delete);
	        query.setParameter("studentId", id);
	        System.out.println(delete+id);
	        int rowsAffected = query.executeUpdate();
	        transaction.commit();

	        if (rowsAffected > 0) {
	            success = true;
	        } else {
	            System.err.println("No rows affected, update failed for student ID: " + id);
	        }
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        System.err.println("Update failed due to exception: " + e.getMessage());
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    
	    return success;
	}

	public long getStudentCount() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(s) FROM StudentBean s", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }
	
	//for report start
		public List<StudentBean> findAllStudents() {
	        List<StudentBean> students =new ArrayList<>();
	    	EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
	        EntityTransaction transaction = em.getTransaction();
	        try {
	        	transaction.begin();

	            Query query = em.createQuery("SELECT s FROM StudentBean s");
	            List<StudentBean> resultList = query.getResultList();
	            for (StudentBean student : resultList) {
	                String courses = findNamesByStudentId(student.getId());
	                student.setCoursesAsString(courses);
	                students.add(student);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return students;
	    }
		  public String findNamesByStudentId(int id) {
		        StringBuilder names = new StringBuilder();
				EntityManager em=null;

		        try {
					em=JPAUtil.getEntityManagerFactory().createEntityManager();
					em.getTransaction().begin();
		            Query query = em.createQuery("SELECT c.name FROM StudentBean s JOIN s.courses c WHERE s.id = :studentId");
		            query.setParameter("studentId", id);
		            List<String> resultList = query.getResultList();
		            for (String name : resultList) {
		                if (names.length() > 0) {
		                    names.append(",");
		                }
		                names.append(name);  
		            }
		        }catch(Exception e) {
					em.getTransaction().rollback();
					System.out.println(e.getMessage());
				}finally{
					em.close();
				}
		        return names.toString();
		    }
		  
		  //for report end
}
