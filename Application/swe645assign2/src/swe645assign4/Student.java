package swe645assign4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String zip;
	private String city;
	private String state;
	private String telephone;
	private String email;
	private Date date;
	private Date startDate;
	private String radio;
	private String[] checkbox;
	private String check;
	private String likely;
	private String raffle;
	private String text;
	private String ename;
	private String ephone;
	private String eemail;

	public Student() {
	}

	public Student(String firstname, String lastname, String streetaddress,
			String zip, String city, String state, String telephone,
			String email, Date date, Date startDate, String radio,
			String check, String likely, String raffle, String text,
			String ename, String ephone, String eemail) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.telephone = telephone;
		this.email = email;
		this.date = date;
		this.startDate = startDate;
		this.check = check;
		this.radio = radio;
		this.likely = likely;
		this.raffle = raffle;
		this.text = text;
		this.text = ename;
		this.text = ephone;
		this.text = eemail;

	}

	ArrayList<Student> allSurveys = null;

	public ArrayList<Student> getAllSurveys() {
		return allSurveys;
	}

	public void setAllSurveys(ArrayList<Student> allSurveys) {
		this.allSurveys = allSurveys;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
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
					this.state = cityState[1];
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public String getNumbers() {
		return Arrays.toString(checkbox);
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
		check = getNumbers();
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
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
