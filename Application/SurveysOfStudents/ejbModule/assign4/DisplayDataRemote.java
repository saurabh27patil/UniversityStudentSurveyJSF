package assign4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public interface DisplayDataRemote {
public ArrayList<ArrayList<String>> getAllData() throws SQLException;
public ArrayList<ArrayList<String>> getData(String query) throws SQLException;
List<Survey> retrieveAllProjects();
List<Survey> retrieveProjects(String query);
public ArrayList<ArrayList<String>> getData1(String query) throws SQLException;
}
