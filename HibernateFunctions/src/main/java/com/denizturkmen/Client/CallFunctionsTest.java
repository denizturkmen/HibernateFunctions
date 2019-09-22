package com.denizturkmen.Client;

import java.sql.Types;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;

import com.denizturkmen.Util.HibernateUtil;
import com.mysql.jdbc.CallableStatement;

public class CallFunctionsTest {

	public static void main(String[] args) {
		
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			int personId = 1;
			final AtomicReference<Integer> phoneCount = new AtomicReference<>();
			session.doWork( connection -> {
			    try (CallableStatement callableStatement = (CallableStatement) connection.prepareCall(
			            "{ ? = call fn_count_phones(?) }" )) {
			        callableStatement.registerOutParameter( 1, Types.INTEGER );
			        callableStatement.setInt( 2, personId);
			        callableStatement.execute();
			        phoneCount.set(callableStatement.getInt(1) );
			    }
			} );
			if(phoneCount != null){
				System.out.println("Phone Count:"+phoneCount.get());
			}
			
		}
	}
}