package com.denizturkmen.Client;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.denizturkmen.Entity.Person;
import com.denizturkmen.Entity.Phone;
import com.denizturkmen.Entity.PhoneType;
import com.denizturkmen.Util.HibernateUtil;

public class SaveDataClient {
	public static void main(String[] args) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		try {
			session = sf.openSession();
			session.beginTransaction();
			
			Person person1 = new Person();
			person1.setName("Deniz Türkmen");
			person1.setNickName("denizm");
			person1.setAddress("Ankara");
			person1.setCreatedOn(new Date());
			person1.setVersion(1);
			
			Phone phone1 = new Phone();
			phone1.setNumber("05327485465");
			phone1.setType(PhoneType.MOBILE);
			phone1.setPerson(person1);
			
			person1.getPhones().add(phone1);
			
			
			Person person2 = new Person();
			person2.setName("Eren Türkmen");
			person2.setNickName("eeren");
			person2.setAddress("Corum");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);
			
			Phone phone2 = new Phone();
			phone2.setNumber("05325445445");
			phone2.setType(PhoneType.MOBILE);
			phone2.setPerson(person2);
			
			Phone phone3 = new Phone();
			phone3.setNumber("4435565");
			phone3.setType(PhoneType.LAND_LINE);
			phone3.setPerson(person2);
			
			person2.getPhones().add(phone2);
			person2.getPhones().add(phone3);
			
			session.save(person1);
			session.save(person2);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}

	}
}
