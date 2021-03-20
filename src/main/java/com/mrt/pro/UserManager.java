package com.mrt.pro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.mapping.PrimaryKey;

public class UserManager {

	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;

	public static void main(String[] args) {

		begin();

//		create();
//		update();
//		find();
//		query();
		 remove();
		end();
	}
	private static void remove() {
		Integer primaryKey = 15;
		Users useReference = entityManager.getReference(Users.class, primaryKey);
		entityManager.remove(useReference);
	}
	private static void query() {
		String jpql = "Select use from Users use where use.uid<10";
		Query query = entityManager.createQuery(jpql); // ;den sonra -->CTRL+1 ile "Query query" statement dan getir.

		@SuppressWarnings("unchecked")  //CTRL+1 (Userlist)
		List<Users> usersList = query.getResultList();

		for (Users us : usersList) {
			System.out.println(us.getUname() + "," + us.getUsurname() + "," + us.getUmail() + "," + us.getUmail() + "," + us.getUpassword());
		}
	}

	private static void update() {
		Users existUser = new Users();
		existUser.setUid(20);
		existUser.setUname("Merat");
		existUser.setUsurname("artur");
		existUser.setUmail("m@gmail.com");
		existUser.setUpassword("6000");

		entityManager.merge(existUser);
	}

	private static void create() {
		Users newUser = new Users();
		newUser.setUname("Mesut");
		newUser.setUsurname("Çelik");
		newUser.setUmail("m@gmail.com");
		newUser.setUpassword("000");

		entityManager.persist(newUser);
	}

	private static void find() {
		Integer primaryKey = 20;
		Users users = entityManager.find(Users.class, primaryKey);

		System.out.println(users.getUname());
		System.out.println(users.getUsurname());
		System.out.println(users.getUmail());
		System.out.println(users.getUpassword());
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	private static void begin() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ProjeDB"); // Persistence den "ProjeDB" alýrýz.
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
	}

}
