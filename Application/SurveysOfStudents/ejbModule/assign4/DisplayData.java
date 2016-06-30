package assign4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;


@Stateless
@Remote(DisplayDataRemote.class)
//(endpointInterface="src.DisplayDataRemote")
public class DisplayData implements DisplayDataRemote {

	 @PersistenceContext(unitName = "saurabh")
	    private EntityManager entityManager;
	
  

	@Override
	public ArrayList<ArrayList<String>> getAllData() throws SQLException {
		ArrayList<ArrayList<String>> allresults = new ArrayList<ArrayList<String>>();
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> temp= new ArrayList<String>();
		DataSource ds = null;
		Connection con = null; 
		PreparedStatement pr = null; 
		InitialContext ic; 
		try {
		ic = new InitialContext();
		ds = (DataSource)ic.lookup( "java:/saurabhDs" );
		con = ds.getConnection(); 
		System.out.println("Remote method");
		pr = con.prepareStatement("SELECT * from SURVEY");
		System.out.println(pr.toString());
		ResultSet rs = pr.executeQuery();
		
		while (rs.next()) {
			
			results.add(rs.getString(1));
			results.add(rs.getString(2));
			results.add(rs.getString(3));
			results.add(rs.getString(4));
			results.add(rs.getString(5));
			results.add(rs.getString(6));
			results.add(rs.getString(7));
			results.add(rs.getString(8));
			results.add(rs.getString(9));			
			results.add(rs.getString(10));
			results.add(rs.getString(11));
			results.add(rs.getString(12));
			results.add(rs.getString(13));
			results.add(rs.getString(14));
			results.add(rs.getString(15));
			results.add(rs.getString(16));
			
			allresults.add(results);
			results= new ArrayList<String>();
		}
		rs.close();
		pr.close();
		}
		catch(Exception ex){
			System.out.println("Exception thrown " +ex); 
			return allresults;
		}finally{
			if(con != null){
				con.close();
				} 
				}
		return allresults;
	}

	
	public ArrayList<ArrayList<String>> getData1(String query)throws SQLException {
		ArrayList<ArrayList<String>> allresults = new ArrayList<ArrayList<String>>();
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> temp= new ArrayList<String>();
		DataSource ds = null;
		Connection con = null; 
		PreparedStatement pr = null; 
		InitialContext ic; 
		try {
		ic = new InitialContext();
		ds = (DataSource)ic.lookup( "java:/saurabhDs" );
		con = ds.getConnection(); 
		System.out.println("This is a remote method");
		pr = con.prepareStatement(query);
		System.out.println(pr.toString());
		ResultSet rs = pr.executeQuery();
		
		while (rs.next()) {
			
			results.add(rs.getString(1));
			results.add(rs.getString(2));
			results.add(rs.getString(3));
			results.add(rs.getString(4));
			results.add(rs.getString(5));
			results.add(rs.getString(6));
			results.add(rs.getString(7));
			results.add(rs.getString(8));
			results.add(rs.getString(9));			
			results.add(rs.getString(10));
			results.add(rs.getString(11));
			results.add(rs.getString(12));
			results.add(rs.getString(13));
			results.add(rs.getString(14));
			results.add(rs.getString(15));
			results.add(rs.getString(16));
			
			allresults.add(results);
			results= new ArrayList<String>();
		}
		rs.close();
		pr.close();
		}
		catch(Exception ex){
			System.out.println("Exception thrown " +ex); 
			return allresults;
		}finally{
			if(con != null){
				con.close();
				} 
				}
		return allresults;
	}
	public ArrayList<ArrayList<String>> getData(String query)
			throws SQLException {
		ArrayList<ArrayList<String>> allresults = new ArrayList<ArrayList<String>>();
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> temp= new ArrayList<String>();
		DataSource ds = null;
		Connection con = null; 
		PreparedStatement pr = null; 
		InitialContext ic; 
		try {
		ic = new InitialContext();
		ds = (DataSource)ic.lookup( "java:/saurabhDs" );
		con = ds.getConnection(); 
		System.out.println("I am in remote method");
		pr = con.prepareStatement(query);
		System.out.println(pr.toString());
		ResultSet rs = pr.executeQuery();
		
		while (rs.next()) {
			
			results.add(rs.getString(1));
			results.add(rs.getString(2));
			results.add(rs.getString(3));
			results.add(rs.getString(4));
			results.add(rs.getString(5));
			results.add(rs.getString(6));
			results.add(rs.getString(7));
			results.add(rs.getString(8));
			results.add(rs.getString(10));
			results.add(rs.getString(11));
			results.add(rs.getString(12));
			results.add(rs.getString(13));
			results.add(rs.getString(14));
			results.add(rs.getString(15));
			results.add(rs.getString(16));
			
			allresults.add(results);
			results= new ArrayList<String>();
		}
		rs.close();
		pr.close();
		}
		catch(Exception ex){
			System.out.println("Exception thrown " +ex); 
			return allresults;
		}finally{
			if(con != null){
				con.close();
				} 
				}
		return allresults;
	}

	@Override
	public List<Survey> retrieveAllProjects() {
		//   
    	System.out.println(Survey.class.getSimpleName());
        String q = "SELECT s from "+ Survey.class.getName() + " s";
        Query query = entityManager.createQuery(q);
        List<Survey> projects = query.getResultList();
       
        return projects;
		
	}


	public List<Survey> retrieveProjects(String query) {
		
		if (query.equals("asc")){
        String q = "SELECT s from "+ Survey.class.getName() + " s ORDER BY s.lastname ASC" ;
        Query query1 = entityManager.createQuery(q);
        List<Survey> survey = query1.getResultList();
       
        return survey;
		}
		else{
			 String q = "SELECT s from "+ Survey.class.getName() + " s where "+query ;	 
		      
		        Query query1 = entityManager.createQuery(q);
		     
		        List<Survey> survey = query1.getResultList();
		       return survey;
			
		}
		
	}

}
