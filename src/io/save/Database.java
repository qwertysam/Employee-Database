package io.save;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import data.employee.EmployeeInfo;
import data.employee.FullTimeEmployee;
import data.employee.Gender;
import data.employee.Location;
import data.employee.PartTimeEmployee;

public class Database {
	private static Database instance = null;

	public static Database instance() {
		if (instance == null) {
			File file = new File("db.json");
			instance = new Database(file);
		}

		return instance;
	}

	private File file;

	private Database(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.file = file;

		table = new ArrayList<EmployeeInfo>();

		load();
	}

	// All employees
	private static final String KEY_ID = "id"; // int, employee ID
	private static final String KEY_FIRSTNAME = "fst"; // String, first name
	private static final String KEY_LASTNAME = "lst"; // String, last name
	private static final String KEY_GENDER = "gen"; // Gender, the gender
	private static final String KEY_LOCATION = "loc"; // Location, the gender
	private static final String KEY_DEDUCTIONS = "ded"; // double, the deductions rate

	private static final String KEY_FULLTIME = "fll"; // Boolean, if the employee is full time, else part time

	// Full Time
	private static final String KEY_YEARLY_SALARY = "sal"; // double, the yearly salary

	// Part Time
	private static final String KEY_HOURLY_SALARY = "dph"; // double, the hourly wage
	private static final String KEY_WEEKLY_HOURS = "hpw"; // double, the hours worked per week
	private static final String KEY_WEEKS_PER_YEAR = "wpy"; // double, weeks worked per year

	// TODO make into a hash tabe
	public List<EmployeeInfo> table;

	public boolean load() {
		try {
			// Loads lines from the file
			List<String> lines = Files.readAllLines(file.toPath());

			// Parses loaded lines into JSON objects, then to EmployeeInfo
			for (String s : lines) {
				try {
					JSONObject jObj = (JSONObject) JSONValue.parse(s);

					int id = Integer.parseInt(jObj.get(KEY_ID).toString());
					String first = jObj.get(KEY_FIRSTNAME).toString();
					String last = jObj.get(KEY_LASTNAME).toString();
					Gender gender = Gender.valueOf(jObj.get(KEY_GENDER).toString());
					Location location = Location.valueOf(jObj.get(KEY_LOCATION).toString());
					double deductions = Double.parseDouble(jObj.get(KEY_DEDUCTIONS).toString());
					boolean isFullTime = Boolean.parseBoolean(jObj.get(KEY_FULLTIME).toString());

					if (isFullTime) {
						double yearlySalary = Double.parseDouble(jObj.get(KEY_YEARLY_SALARY).toString());
						table.add(new FullTimeEmployee(id, first, last, gender, location, deductions, yearlySalary));
					} else {
						double hourlySalary = Double.parseDouble(jObj.get(KEY_HOURLY_SALARY).toString());
						double weeklyHours = Double.parseDouble(jObj.get(KEY_WEEKLY_HOURS).toString());
						double weeksPerYear = Double.parseDouble(jObj.get(KEY_WEEKS_PER_YEAR).toString());
						table.add(new PartTimeEmployee(id, first, last, gender, location, deductions, hourlySalary, weeklyHours, weeksPerYear));
					}

				} catch (Exception e) {
					// Failed to load entry
					e.printStackTrace();
				}
			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void save() {
		List<String> lines = new ArrayList<String>();
		for (EmployeeInfo e : table) {
			JSONObject obj = new JSONObject();

			obj.put(KEY_ID, e.getEmployeeNumber());
			obj.put(KEY_FIRSTNAME, e.getFirstName());
			obj.put(KEY_LASTNAME, e.getLastName());
			obj.put(KEY_GENDER, e.getGender().toString());
			obj.put(KEY_LOCATION, e.getLocation().toString());
			obj.put(KEY_DEDUCTIONS, e.getDeductionsRate());

			if (e instanceof FullTimeEmployee) {
				FullTimeEmployee f = (FullTimeEmployee) e;
				obj.put(KEY_FULLTIME, true);
				obj.put(KEY_YEARLY_SALARY, f.getYearlySalary());
			} else if (e instanceof PartTimeEmployee) {
				PartTimeEmployee p = (PartTimeEmployee) e;
				obj.put(KEY_FULLTIME, false);
				obj.put(KEY_HOURLY_SALARY, p.getHourlyWage());
				obj.put(KEY_WEEKLY_HOURS, p.getHoursPerWeek());
				obj.put(KEY_WEEKS_PER_YEAR, p.getWeeksPerYear());
			}

			lines.add(obj.toJSONString());
		}

		FileUtil.writeLines(file, lines);
	}
}
