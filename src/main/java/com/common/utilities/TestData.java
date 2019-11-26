package com.common.utilities;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;


public class TestData {

	Faker faker = new Faker(new Locale("en-IND"));

	private String firstName;
	private String futureDate;
	private String pastDate;
	private String numberSizeTen;
	private boolean trueValue;
	private boolean falseValue;
	private String address;
	private String mobileNumber;
	private String email;

	public String getMobileNumber() {
		mobileNumber = faker.phoneNumber().cellPhone();
		return mobileNumber;
	}

	public String getEmail() {
		email = faker.internet().emailAddress();
		return email;
	}

	public String getAddress() {
		address = faker.address().streetAddress();
		return address;
	}

	public boolean gettrueValue() {
		trueValue = true;
		return trueValue;

	}

	public boolean getfalseValue() {
		falseValue = false;
		return falseValue;

	}

	public String getFirstName() {
		firstName = faker.name().firstName();
		return firstName;
	}

	public String getfutureDate() {
		Date d = faker.date().future(600, TimeUnit.DAYS);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		futureDate = formatter.format(d);
		return futureDate;

	}

	public String getpastDate() {
		Date d = faker.date().past(600, TimeUnit.DAYS);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		pastDate = formatter.format(d);
		return pastDate;

	}

	public String getnumberSizeTen() {
		numberSizeTen = faker.number().digits(10);
		return numberSizeTen;

	}

	public static HashMap<String, String> getCredentialsMap() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		String environment = ConfigReader.getValue("Environment");

		switch (environment) {
		case "DEV":
			map.put("businessAdmin", ConfigReader.getValue("businessAdminDev"));
			map.put("operationsSupervisor", ConfigReader.getValue("operationsSupervisorDev"));
			map.put("operationsUser", ConfigReader.getValue("operationsUserDev"));
			map.put("creditAppRaiser", ConfigReader.getValue("creditAppRaiserDev"));
			map.put("creditAppRaiserChecker", ConfigReader.getValue("creditAppRaiserCheckerDev"));
			map.put("creditApprover", ConfigReader.getValue("creditApproverDev"));
			map.put("fieldOfficer", ConfigReader.getValue("fieldOfficerDev"));
			map.put("merchant", ConfigReader.getValue("merchantDev"));
			map.put("connector", ConfigReader.getValue("connectorDev"));
			return map;

		case "QA":
			map.put("businessAdmin", ConfigReader.getValue("businessAdmin"));
			map.put("operationsSupervisor", ConfigReader.getValue("operationsSupervisor"));
			map.put("operationsUser", ConfigReader.getValue("operationsUser"));
			map.put("creditAppRaiser", ConfigReader.getValue("creditAppRaiser"));
			map.put("creditAppRaiserChecker", ConfigReader.getValue("creditAppRaiserChecker"));
			map.put("creditApprover", ConfigReader.getValue("creditApprover"));
			map.put("fieldOfficer1", ConfigReader.getValue("fieldOfficer1"));
			map.put("merchant", ConfigReader.getValue("merchant"));
			map.put("connector", ConfigReader.getValue("connector"));
			return map;

		case "UAT":
			map.put("businessAdmin", ConfigReader.getValue("businessAdminUat"));
			map.put("operationsSupervisor", ConfigReader.getValue("operationsSupervisorUat"));
			map.put("operationsUser", ConfigReader.getValue("operationsUserUat"));
			map.put("creditAppRaiser", ConfigReader.getValue("creditAppRaiserUat"));
			map.put("creditAppRaiserChecker", ConfigReader.getValue("creditAppRaiserCheckerUat"));
			map.put("creditApprover", ConfigReader.getValue("creditApproverUat"));
			map.put("fieldOfficer", ConfigReader.getValue("fieldOfficerUat"));
			map.put("merchant", ConfigReader.getValue("merchantUat"));
			map.put("connector", ConfigReader.getValue("connectorUat"));
			return map;

		case "PROD":
			map.put("businessAdmin", ConfigReader.getValue("businessAdminProd"));
			map.put("operationsSupervisor", ConfigReader.getValue("operationsSupervisorProd"));
			map.put("operationsUser", ConfigReader.getValue("operationsUserProd"));
			map.put("creditAppRaiser", ConfigReader.getValue("creditAppRaiserProd"));
			map.put("creditAppRaiserChecker", ConfigReader.getValue("creditAppRaiserCheckerProd"));
			map.put("creditApprover", ConfigReader.getValue("creditApproverProd"));
			map.put("fieldOfficer", ConfigReader.getValue("fieldOfficerProd"));
			map.put("merchant", ConfigReader.getValue("merchantProd"));
			map.put("connector", ConfigReader.getValue("connectorProd"));
			return map;

		default:
		throw new IllegalArgumentException("Environment value is null");
		}

		
	}

	public String getUserName(String role) {
		String credentials = getCredentialsMap().get(role);
		return credentials.split(":")[0];
	}

	public String getPassword(String role) {
		String credentials = getCredentialsMap().get(role);
		return credentials.split(":")[1];
	}

	public static void main(String[] args) {
		TestData t = new TestData();
		System.out.println(t.getAddress());
		System.out.println(t.getMobileNumber());
		System.out.println(t.getEmail());

	}

}
