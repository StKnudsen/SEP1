package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class MyDate implements Serializable {

	private int day;
	private int month;
	private int year;

	public MyDate(LocalDate localDate) {

		year = localDate.getYear( );
		month = localDate.getMonthValue( );
		day = localDate.getDayOfMonth( );

	}

	@Override
	public String toString( ) {
		//tilføj 0 foran tal, hvis tallet er mindre end 9 (3 = 03)
		return year  + "-" + (month < 9 ? "0" + month : month) + "-" + (day < 9 ? "0" + day : day);
	}

	/*

	public MyDate(int year, int month, int day) {
		set(day, month, year);
	}

	public static MyDate now( ) {
		GregorianCalendar now = new GregorianCalendar( );
		return new MyDate(now.get(Calendar.DATE), ( now.get(Calendar.MONTH) + 1 ), now.get(Calendar.YEAR));
	}

	public int getYear( ) {
		return year;
	}

	public int getMonth( ) {
		return month;
	}

	public int getDay( ) {
		return day;
	}

	public String getMonthName( ) {
		switch ( month ) {
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
		}
		return "";
		//We are using DatePicker in our GUI, so months out of range won't be possible.

	}

	public void set(int year, int month, int day) {
		this.day = Math.abs(day);
		this.month = Math.abs(month);
		this.year = Math.abs(year);
		while ( this.day > numberOfDaysInMonth(this.month) ) {
			this.day -= numberOfDaysInMonth(this.month);
			this.month++;
			while ( this.month > 12 ) {
				this.month -= 12;
				this.year++;
			}
		}
	}

	public boolean isLeapYear( ) {
		return year % 400 == 0 || ! ( ( year % 100 ) == 0 ) && year % 4 == 0;
	}

	public int numberOfDaysInMonth(int monthNumber) {
		if ( isLeapYear( ) && monthNumber == 2 ) {
			return 29;
		}
		switch ( monthNumber ) {
			case 2:
				return 28;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
		}
		return - 1;
		//We are using DatePicker in GUI, so user can't select out of range.
	}

	public int yearsBetween(MyDate newDate) {
		if ( newDate.getMonth() > month )
			return Math.abs(year - newDate.getYear());
		return Math.abs(year - newDate.getYear()) - 1;
	}

	public int daysBetween(MyDate newDate) {
		int daySum = 0;
		MyDate firstDate;
		MyDate lastDate;

		if ( newDate.isBefore(this) ) {
			firstDate = newDate.copy( );
			lastDate = this.copy( );
		} else {
			firstDate = this.copy( );
			lastDate = newDate.copy( );
		}

		if ( firstDate.year < lastDate.year ) {
			for ( int i = firstDate.year ; i < lastDate.year ; i++ ) {
				daySum += 365;
			}
		}
		if ( firstDate.month < lastDate.month ) {
			for ( int j = firstDate.month ; j < lastDate.month ; j++ ) {
				daySum += numberOfDaysInMonth(j);
			}
			if ( firstDate.day > lastDate.day ) {
				daySum += numberOfDaysInMonth(firstDate.month) - firstDate.day;
				daySum += lastDate.day;
			} else {
				daySum += lastDate.day - firstDate.day;
			}
		} else if ( lastDate.month < firstDate.month ) {
			for ( int j = lastDate.month ; j < firstDate.month ; j++ ) {
				daySum += numberOfDaysInMonth(j);
			}
			if ( lastDate.day > firstDate.day ) {
				daySum += numberOfDaysInMonth(lastDate.month) - lastDate.day;
				daySum += firstDate.day;
			} else {
				daySum += firstDate.day - lastDate.day;
			}
		} else {
			if ( firstDate.day > lastDate.day ) {
				daySum += firstDate.day - lastDate.day;
			} else {
				daySum += lastDate.day - firstDate.day;
			}
		}
		return daySum;
	}

	public boolean isBefore(MyDate newDate) {
		if ( this.year > newDate.year )
			return false;
		else if ( this.month > newDate.month )
			return false;
		else
			return this.day < newDate.day;
	}

	public MyDate copy( ) {
		return new MyDate(year, month, day);
	}

	public boolean equals(Object obj) {
		if ( ! ( obj instanceof MyDate ) )
			return false;
		MyDate other = (MyDate) obj;
		return year == other.year && month == other.month && day == other.day;
	}

	 */
}
