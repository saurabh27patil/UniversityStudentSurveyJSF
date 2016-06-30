package swe645assign4;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import assign4.DeleteDataRemote;
import assign4.DisplayData;
import assign4.DisplayDataRemote;
import assign4.StoreDataRemote;
import assign4.Survey;

@ManagedBean(name = "user")
@SessionScoped
public class StudentService implements Serializable {

	private Student student = new Student();
	private ArrayList<Student> students = new ArrayList<Student>();
	private WinningResult winningResult = new WinningResult();
	private String str;

	ArrayList<Student> surveys = null;

	public ArrayList<Student> getsurveys() {
		return surveys;
	}

	public void setsurveys(ArrayList<Student> surveys) {
		this.surveys = surveys;
	}

	private int[] number = new int[10];

	private static final String dropDown = "Likely,Unlikely,Very Likely";
	private static final String[] dropDownArray = dropDown.split(",");

	public List<String> completeDrop(String dropDownPrefix) {
		List<String> matches = new ArrayList<String>();
		for (String possibleVal : dropDownArray) {
			if (possibleVal.toUpperCase().startsWith(
					dropDownPrefix.toUpperCase())) {
				matches.add(possibleVal);
			}
		}
		return (matches);
	}

	public WinningResult split(String raffle) {
		double t1 = 0;
		if (!raffle.isEmpty()) {
			String temp[] = raffle.split(",");
			System.out.println(temp);
			int i = 0, s = 0;
			for (String t : temp) {
				System.out.println(i);
				number[i] = Integer.parseInt(t);
				s = s + number[i];
				i++;
			}
			winningResult.setMean(s / i);
			for (i = 0; i < temp.length; i++) {
				t1 = t1 + Math.pow(number[i] - winningResult.getMean(), 2);
			}
			winningResult.setSd(Math.sqrt(t1 / (temp.length - 1)));
		}

		return winningResult;
	}

	public WinningResult getWinningResult() {
		return winningResult;
	}

	public void setWinningResult(WinningResult winningResult) {
		this.winningResult = winningResult;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public ArrayList<Student> getStudents() {
		return students;

	}

	public void StoreStudentSurvey(Student student) throws IOException,
			SQLException {

		String Query = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pr = null;
		InitialContext ic;
		try {
			ic = new InitialContext();
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES,
					"org.jboss.ejb.client.naming");
			final String beanName = DisplayData.class.getSimpleName();
			final String viewClassName = DisplayDataRemote.class.getName();
			final Context context = new InitialContext(jndiProperties);
			StoreDataRemote dd = (StoreDataRemote) context
					.lookup("ejb:EAR4/SurveysOfStudents//StoreData!assign4.StoreDataRemote");

			Survey students = new Survey();
			students.setFirstname(student.getFirstname());
			students.setLastname(student.getLastname());
			students.setStreetaddress(student.getStreetaddress());
			students.setCity(student.getCity());
			students.setUsstate(student.getState());
			students.setZip(student.getZip());
			students.setTelephone(student.getTelephone());
			students.setEmail(student.getEmail());
			
			SimpleDateFormat simple = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			String date1 = simple.format(student.getDate());
			String date2 = simple.format(student.getStartDate());
			
			students.setSurveydate(date1);
			students.setStartDate(date2);
			students.setSurveyRadio(student.getRadio());
			students.setCheckBx(student.getCheck());
			students.setLikely(student.getLikely());
			students.setRaffle(student.getRaffle());
			students.setText(student.getText());

			students.setEemail(student.getEemail());
			students.setEname(student.getEname());
			students.setEphone(student.getEphone());
			dd.saveSurvey(students);

		} catch (Exception e) {
			System.out.println("Exception thrown " + e);
		}
	}

	public String execute1() throws IOException, SQLException {
		StudentService ss = new StudentService();

		ss.StoreStudentSurvey(student);
		if (new StudentService().split(student.getRaffle()).getMean() < 90)
			return "SimpleAcknowledgement";
		else
			return "WinnerAcknowledgement";
	}

	public String AlphaOrder() throws IOException, SQLException {
		surveys = new ArrayList<Student>();
		StudentService ss = new StudentService();
		String query = "asc";// "SELECT * FROM SURVEY ORDER BY LNAME ASC";
		surveys = ss.retrieveStudentSurvey(query);
		return "displayallsurvey";

	}

	public void Delete(String delete) throws IOException, SQLException,
			NamingException {

		System.out.println("Delete Reached!");

		surveys = new ArrayList<Student>();
		StudentService ss = new StudentService();
		ss.delete(delete);
		CustomQuery();

	}

	public String CustomQuery() throws IOException, SQLException {
		StudentService ss = new StudentService();
		surveys = new ArrayList<Student>();
		String lname = student.getLastname();
		String fname = student.getFirstname();
		String city = student.getCity();
		String state = student.getState();
		int flag = 0;

		String query = "";
		if (!lname.equals("")) {
			if (lname.endsWith("*")) {
				query += " s.lastname like '"
						+ lname.substring(0, lname.length() - 1) + "%'";
			} else {
				query += " s.lastname = '" + lname + "'";
			}
			flag++;

		}
		if (!fname.equals("")) {
			if (flag > 0) {
				query += " OR";
			}
			if (fname.endsWith("*")) {
				query += " s.firstname like '"
						+ fname.substring(0, fname.length() - 1) + "%'";

			} else {
				query += " s.firstname = '" + fname + "'";
			}
			flag++;
		}
		if (!city.equals("")) {
			if (flag > 0) {
				query += " OR";
			}
			if (city.endsWith("*")) {
				query += " s.city like '"
						+ city.substring(0, city.length() - 1) + "%'";

			} else {
				query += " s.city = '" + city + "'";
			}
			flag++;
		}

		if (!state.equals("")) {
			if (flag > 0) {
				query += " OR";
			}
			if (state.endsWith("*")) {
				query += " s.usstate like '"
						+ state.substring(0, state.length() - 1) + "%'";

			} else {
				query += " s.usstate = '" + state + "'";
			}
		}
		System.out.println(query);
		surveys = ss.retrieveStudentSurvey(query);

		return "surveyafterdelete";

	}

	public ArrayList<Student> retrieveStudentSurvey() throws IOException,
			SQLException {
		ArrayList<Student> surveys = new ArrayList<Student>();
		ArrayList<String> temp = new ArrayList<String>();
		// ArrayList<ArrayList<String>> rs = new ArrayList<ArrayList<String>>();

		Student survey = new Student();

		try {
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES,
					"org.jboss.ejb.client.naming");
			final String beanName = DisplayData.class.getSimpleName();
			final String viewClassName = DisplayDataRemote.class.getName();
			final Context context = new InitialContext(jndiProperties);
			System.out.println("getting connection");

			Survey students = new Survey();
			String[] temp1;

			DisplayDataRemote dd = (DisplayDataRemote) context
					.lookup("ejb:EAR4/SurveysOfStudents//DisplayData!assign4.DisplayDataRemote");
			// rs= dd.getData(Query);
			List<Survey> rs = dd.retrieveAllProjects();
			for (int i = 0; i < rs.size(); i++) {

				survey.setFirstname(rs.get(i).getFirstname());
				survey.setLastname(rs.get(i).getLastname());
				survey.setStreetaddress(rs.get(i).getStreetaddress());
				survey.setCity(rs.get(i).getCity());
				survey.setState(rs.get(i).getUsstate());
				survey.setZip(rs.get(i).getZip());
				survey.setTelephone(rs.get(i).getTelephone());
				survey.setEmail(rs.get(i).getEmail());
				
				SimpleDateFormat simple = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				String date1 = simple.format(student.getDate());
				String date2 = simple.format(student.getStartDate());
				
				students.setSurveydate(date1);
				students.setStartDate(date2);
				temp1 = rs.get(i).getCheckBx().split(",");
				for (int j = 0; j < temp1.length; j++) {
					temp.add(temp1[j]);
				}
				survey.setRadio(rs.get(i).getSurveyRadio());
				survey.setLikely(rs.get(i).getLikely());
				survey.setCheck(rs.get(i).getCheckBx());
				survey.setRaffle(rs.get(i).getRaffle());
				survey.setText(rs.get(i).getText());
				survey.setEemail(rs.get(i).getEemail());
				survey.setEname(rs.get(i).getEname());
				survey.setEphone(rs.get(i).getEphone());
				surveys.add(survey);
				survey = new Student();

			}
		} catch (Exception e) {
			System.out.println("Exception thrown " + e);
		}

		return surveys;

	}

	public ArrayList<Student> retrieveStudentSurvey(String Query)
			throws IOException, SQLException {
		ArrayList<Student> surveys = new ArrayList<Student>();
		ArrayList<String> temp = new ArrayList<String>();
		// ArrayList<ArrayList<String>> rs = new ArrayList<ArrayList<String>>();

		Student survey = new Student();

		try {
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES,
					"org.jboss.ejb.client.naming");
			final String beanName = DisplayData.class.getSimpleName();
			final String viewClassName = DisplayDataRemote.class.getName();
			final Context context = new InitialContext(jndiProperties);
			System.out.println("i am about to get connection");

		
			DisplayDataRemote dd = (DisplayDataRemote) context
					.lookup("ejb:EAR4/SurveysOfStudents//DisplayData!assign4.DisplayDataRemote");
			DateFormat df = new SimpleDateFormat("EE MMM dd HH:mm:ss Z yyyy");
			List<Survey> rs = dd.retrieveProjects(Query);
			for (int i = 0; i < rs.size(); i++) {

				survey.setFirstname(rs.get(i).getFirstname());
				survey.setLastname(rs.get(i).getLastname());
				survey.setStreetaddress(rs.get(i).getStreetaddress());
				survey.setCity(rs.get(i).getCity());
				survey.setState(rs.get(i).getUsstate());
				survey.setZip(rs.get(i).getZip());
				survey.setTelephone(rs.get(i).getTelephone());
				survey.setEmail(rs.get(i).getEmail());
				
				Date surveydate = df.parse(rs.get(i).getSurveydate());
				survey.setDate(surveydate);
				
				Date startdate = df.parse(rs.get(i).getStartDate());
				survey.setStartDate(startdate);
				
				survey.setRadio(rs.get(i).getSurveyRadio());
				survey.setLikely(rs.get(i).getLikely());
				survey.setCheck(rs.get(i).getCheckBx());
				survey.setRaffle(rs.get(i).getRaffle());
				survey.setText(rs.get(i).getText());
				survey.setEemail(rs.get(i).getEemail());
				survey.setEname(rs.get(i).getEname());
				survey.setEphone(rs.get(i).getEphone());
				surveys.add(survey);
				survey = new Student();

			}
		} catch (Exception e) {
			System.out.println("Exception thrown " + e);
		}
		return surveys;
	}

	private Student getInstance() {
		return null;

	}

	public void delete(String del) throws NamingException
	{
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		
		final Context context = new InitialContext(jndiProperties);
		System.out.println("i am about to get connected");
		
		DeleteDataRemote dd = (DeleteDataRemote) context.lookup("ejb:EAR4/SurveysOfStudents//DeleteData!assign4.DeleteDataRemote");
		dd.delete(del);
	}

	public String Check(String del) throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		final String beanName = DisplayData.class.getSimpleName();
		final String viewClassName = DisplayDataRemote.class.getName();
		final Context context = new InitialContext(jndiProperties);
		System.out.println("Getting connected");
		
		StoreDataRemote dd = (StoreDataRemote) context
				.lookup("ejb:EAR4/SurveysOfStudents//StoreData!assign4.StoreDataRemote");
		return dd.check(del);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
