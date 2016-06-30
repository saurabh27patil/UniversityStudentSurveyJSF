package assign4;

import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "survey")
public class Survey implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String zip;
	private String city;
	private String usstate;
	private String telephone;
	@Id
	private String email;
	private String surveydate;
	private String startDate;
	private String surveyRadio;
	public String getSurveyRadio() {
		return surveyRadio;
	}

	public void setSurveyRadio(String surveyRadio) {
		this.surveyRadio = surveyRadio;
	}

	@Transient
	private String[] checkbox;
	private String checkBx;
	private String likely;
	private String raffle;
	private String text;
	private String ename;
	private String ephone;
	private String eemail;

	public Survey() {
	}

	public Survey(String firstname, String lastname, String streetaddress,
			String zip, String city, String state, String telephone,
			String email, String surveydate, String startDate, String radio,
			String checkBx, String likely, String raffle, String text,
			String ename, String ephone, String eemail) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.city = city;
		this.usstate = state;
		this.zip = zip;
		this.telephone = telephone;
		this.email = email;
		this.surveydate = surveydate;
		this.startDate = startDate;
		this.checkBx = checkBx;
		this.surveyRadio = radio;
		this.likely = likely;
		this.raffle = raffle;
		this.text = text;
		this.text = ename;
		this.text = ephone;
		this.text = eemail;

	}

	public String getUsstate() {
		return usstate;
	}

	public void setUsstate(String usstate) {
		this.usstate = usstate;
	}

	public String getSurveydate() {
		return surveydate;
	}

	public void setSurveydate(String surveydate) {
		this.surveydate = surveydate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		if (zip.length() == 5) {
			for (Map.Entry<String, String> entry : getMap().entrySet()) {
				if (entry.getKey().equals(zip)) {
					String[] cityState = entry.getValue().split("-");
					this.city = cityState[0];
					this.usstate = cityState[1];
					break;
				}
			}
		}
		this.zip = zip;
	}

	public Map<String, String> getMap() {
		Map<String, String> zipCityState = new HashMap<String, String>();
		zipCityState.put("22312", "Alexandria-VA");
		zipCityState.put("22030", "Fairfax-VA");
		zipCityState.put("22301", "Tysons Corner-MD");
		zipCityState.put("20148", "Ashburn-VA");

		return zipCityState;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public String getNumbers() {
		return Arrays.toString(checkbox);
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
		checkBx = getNumbers();
	}

	public String getLikely() {
		return likely;
	}

	public void setLikely(String likely) {
		this.likely = likely;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRaffle() {
		return raffle;
	}

	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCheckBx() {
		return checkBx;
	}

	public void setCheckBx(String checkBx) {
		this.checkBx = checkBx;
	}

	public String getEphone() {
		return ephone;
	}

	public void setEphone(String ephone) {
		this.ephone = ephone;
	}

	public String getEemail() {
		return eemail;
	}

	public void setEemail(String eemail) {
		this.eemail = eemail;
	}
}
