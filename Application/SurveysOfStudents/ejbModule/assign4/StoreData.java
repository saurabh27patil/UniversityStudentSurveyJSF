package assign4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
@Remote(StoreDataRemote.class)
public class StoreData implements StoreDataRemote {
	@PersistenceContext(unitName = "saurabh")
    private EntityManager entityManager;
     
 
 
	@Override
	public void StoreSurvey(String query) throws SQLException {
		DataSource ds = null;
		Connection con = null; 
		PreparedStatement pr = null; 
		InitialContext ic; 
		try {
			ic = new InitialContext();
			ds = (DataSource)ic.lookup( "java:/saurabhDs" );
			con = ds.getConnection(); 

        System.out.println("I am in storing data");
	
		pr = con.prepareStatement(query);
		ResultSet rs = pr.executeQuery();
	
		rs.close();
		pr.close();
		}catch(Exception e){
		System.out.println("Exception thrown " +e); 
		}finally{
		if(con != null){
		con.close();
		} 
		}
		
	}

	@Override
	public void saveSurvey(Survey student) {
        entityManager.persist(student);

	}

	@Override
	public String check(String id) {
		Survey survey=entityManager.find(Survey.class, id);
		// TODO Auto-generated method stub
		if(survey!= null){
			return "exists";
		}
		return "notexists";
		
		
	}

 

}
