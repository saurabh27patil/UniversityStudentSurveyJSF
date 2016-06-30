package assign4;

import javax.ejb.Remote;

@Remote
public interface DeleteDataRemote {
	public void delete(String del);

}
