package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.TimeObject;

public class DefHourLabeler extends Labeler
{
	public DefHourLabeler(int intervall)
	{
		super(intervall);
	}

	@Override
	public TimeObject add(long time, int val)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.HOUR_OF_DAY, val);
		return timeObjectfromCalendar(c);
	}
	
	@Override
	protected TimeObject timeObjectfromCalendar(Calendar c)
	{
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// get the first millisecond of that hour
		c.set(year, month, day, hour, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		// get the last millisecond of that hour
		c.set(year, month, day, hour, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		return new TimeObject(String.valueOf(hour), startTime, endTime);
	}
}
