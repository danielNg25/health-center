package project.medical.ui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import project.medical.core.Person;


class PersonTableModel extends AbstractTableModel {
	public  static final int OBJECT_COL = -1;
	private static final int ORDINAL_COL = 0;
	private static final int ID_COL = 1;
	private static final int FIRST_NAME_COL = 2;
	private static final int LAST_NAME_COL = 3;
	private static final int DATE_OF_BIRTH_COL = 4;
	private static final int GENDER_COL = 5;
	private static final int HEIGHT_COL = 6;
	private static final int WEIGHT_COL = 7;
	private static final int OCCUPATION_COL = 8;
	private static final int ADDRESS_COL = 9;
	private static final int EMAIL_COL = 10;
	private static final int PHONE_NUMBER_COL = 11;



	private String[] columnNames = {"Ordinal","ID","First_Name","Last_Name","Date_of_birth","Gender","Height",
			"Weight","Occupation","Address","Email","Phone_number"}; 
	private List<Person> Persons;

	public PersonTableModel(List<Person> thePersons) {
		Persons = thePersons;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Persons.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Person tempPerson = Persons.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempPerson;
		case ORDINAL_COL:
			return row + 1;
		case ID_COL:
			return tempPerson.getID();
		case FIRST_NAME_COL:
			return tempPerson.getFirst_Name();
		case LAST_NAME_COL:
			return tempPerson.getLast_Name();
		case GENDER_COL:
			return tempPerson.getGender();
		case EMAIL_COL:
			return tempPerson.getEmail();
		case DATE_OF_BIRTH_COL:
			return tempPerson.getDate_of_birth();
		case ADDRESS_COL:
			return tempPerson.getAddress();
		case PHONE_NUMBER_COL:
			return tempPerson.getPhone_number();
		case HEIGHT_COL:
			return tempPerson.getHeight();
		case WEIGHT_COL:
			return tempPerson.getWeight();
		case OCCUPATION_COL:
			return tempPerson.getOccupation();
		default:
			return tempPerson.getFirst_Name();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}


