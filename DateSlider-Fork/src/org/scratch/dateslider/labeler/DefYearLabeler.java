package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.TimeObject;

public class DefYearLabeler extends Labeler
{
	public DefYearLabeler(int intervall)
	{
		super(intervall);
	}

	/**
	 * add "val" year to the month object that contains "time" and returns the new TimeObject
	 */
	@Override
	public TimeObject add(long time, int val)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.YEAR, val);
		return timeObjectfromCalendar(c);
	}
	
	/**
	 * creates an TimeObject from a CalendarInstance
	 */
	@Override
	protected TimeObject timeObjectfromCalendar(Calendar c)
	{
		int year = c.get(Calendar.YEAR);
		// set calendar to first millisecond of the year
		c.set(year, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		// set calendar to last millisecond of the year
		c.set(year, 11, 31, 23, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		return new TimeObject(String.valueOf(year), startTime, endTime);
	}
}
