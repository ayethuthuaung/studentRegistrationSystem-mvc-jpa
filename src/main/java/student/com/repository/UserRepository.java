package student.com.repository;

import java.util.List;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
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
			
			System.out.println(e.getMessage());

		} finally {
			em.close();
		}
		return userBean;
	}

	public long getUserCount() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(u) FROM UserBean u", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

	//selectAll
		public List<UserBean> selectAll() {
		      EntityManager em = null;
		      List<UserBean> lstuser = null;

		      try {
		          em = JPAUtil.getEntityManagerFactory().createEntityManager();
		          lstuser = em.createQuery("select u from UserBean u where u.deleted = 0", UserBean.class).getResultList();
		          System.out.println("User list size: " + lstuser.size()); // Add this line
		      } catch (Exception e) {
		          System.out.println("Error retrieving user data: " + e.getMessage()); // Add this line
		      } finally {
		          if (em != null && em.isOpen()) {
		              em.close();
		          }
		      }
		      return lstuser;
		  }
		  
		//selectone
		  public UserBean selectOne(int id) {
		      EntityManager em = null;
		      UserBean userBean = null;

		      try {
		          em = JPAUtil.getEntityManagerFactory().createEntityManager();

		          userBean = em.createQuery("SELECT u FROM UserBean u WHERE u.id = :id", UserBean.class)
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
		      return userBean;
		  }

		  //update
		   public int updateData(UserBean user) {
		        EntityManager em = null;
		        int i = 0;
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();
		            em.getTransaction().begin();
		            UserBean userbean = em.find(UserBean.class, user.getId());

		            if (userbean == null) {
		                // User not found in the database
		                System.out.println("User with ID " + user.getId() + " not found.");
		                return 0;
		            }

		            
		            userbean.setName(user.getName());
		            userbean.setEmail(user.getEmail());
		            userbean.setGender(user.getGender());
		            userbean.setPhone(user.getPhone());
		            userbean.setDob(user.getDob());
		            userbean.setAddress(user.getAddress());

		            // Merge the updated userbean
		            em.merge(userbean);
		            em.getTransaction().commit();
		            i = 1;

		        } catch (Exception e) {
		            if (em != null && em.getTransaction().isActive()) {
		                em.getTransaction().rollback();
		            }
		            System.out.println("Error occurred during update: " + e.getMessage());
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }
		        return i;
		    }


	//softdelete
		   public int softDeleteData(int id) {
		          EntityManager em = null;
		          try {
		              em = JPAUtil.getEntityManagerFactory().createEntityManager();
		              em.getTransaction().begin();

		              StoredProcedureQuery query = em.createStoredProcedureQuery("delete_user");
		              query.registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN);
		              query.setParameter("user_id", id);
		              query.execute();

		              em.getTransaction().commit();
		              return 1;
		          } catch (Exception e) {
		              if (em != null && em.getTransaction().isActive()) {
		                  em.getTransaction().rollback();
		              }
		              e.printStackTrace(); // Handle exception appropriately
		              return 0;
		          } finally {
		              if (em != null) {
		                  em.close();
		              }
		          }
		      }
		   
		   public List<UserBean> selectAllByRole(String role) {
			    EntityManager em = null;
			    List<UserBean> userList = null;

			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();
			        userList = em.createQuery("SELECT u FROM UserBean u WHERE u.role = :role", UserBean.class)
			                     .setParameter("role", role)
			                     .getResultList();
			        System.out.println("User list size for role " + role + ": " + userList.size()); // Optionally, print list size
			    } catch (Exception e) {
			        System.out.println("Error retrieving users by role: " + e.getMessage());
			    } finally {
			        if (em != null && em.isOpen()) {
			            em.close();
			        }
			    }
			    return userList;
			}


}
