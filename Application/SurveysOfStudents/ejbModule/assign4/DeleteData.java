package assign4;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class DeleteData
 */
@Stateless
public class DeleteData implements DeleteDataRemote {
	@PersistenceContext(unitName = "saurabh")
    private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public DeleteData() {
      
    }

	@Override
	public void delete(String del) {
		Survey survey1 = entityManager.find(Survey.class, del);
		 
		entityManager.remove(survey1);		
		
	}
}
