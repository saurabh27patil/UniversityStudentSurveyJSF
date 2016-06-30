package assign4;

import java.sql.SQLException;


import javax.ejb.Remote;

@Remote
public interface StoreDataRemote {
	void saveSurvey(Survey student);
	public String check(String id); 
	public void StoreSurvey(String query) throws SQLException;
}