package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.TimeObject;


public class DefMonthLabeler extends Labeler
{
	public DefMonthLabeler(int intervall)
	{
		super(intervall);
	}

	/**
	 * add "val" months to the month object that contains "time" and returns the new TimeObject
	 */
	@Override
	public TimeObject add(long time, int val)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.MONTH, val);
		return timeObjectfromCalendar(c);
	}
	
	/**
	 * creates an TimeObject from a CalendarInstance
	 */
	@Override
	protected TimeObject timeObjectfromCalendar(Calendar c)
	{
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		// set calendar to first millisecond of the month
		c.set(year, month, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		// set calendar to last millisecond of the month
		c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		return new TimeObject(String.format("%tB",c), startTime, endTime);
	}
}
