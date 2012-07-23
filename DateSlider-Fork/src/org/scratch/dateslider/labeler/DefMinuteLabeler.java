package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.TimeObject;

public class DefMinuteLabeler extends Labeler
{
	public DefMinuteLabeler(int intervall)
	{
		super(intervall);
	}

	@Override
	public TimeObject add(long time, int val)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.MINUTE, val);
		return timeObjectfromCalendar(c);
	}
	
	@Override
	protected TimeObject timeObjectfromCalendar(Calendar c)
	{
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		// get the first millisecond of that minute
		c.set(year, month, day, hour, minute, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		// get the last millisecond of that minute
		c.set(year, month, day, hour, minute, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		return new TimeObject(String.valueOf(minute), startTime, endTime);
	}
}
