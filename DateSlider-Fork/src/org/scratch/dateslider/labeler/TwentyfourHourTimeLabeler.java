package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.TimeObject;

import android.util.Log;

public class TwentyfourHourTimeLabeler extends Labeler
{
	public TwentyfourHourTimeLabeler(int intervall)
	{
		super(intervall);
	}

	/**
	 * add "val" days to the month object that contains "time" and returns the new TimeObject
	 */
	@Override
	public TimeObject add(long time, int val)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.MINUTE, val*intervall);
		return timeObjectfromCalendar(c);
	}
	
	/**
	 * override this method to set the inital TimeObject to a multiple of MINUTEINTERVAL
	 */
	@Override
	public TimeObject getElem(long time)
	{ 
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)/intervall*intervall);
		Log.v("GETELEM","getelem: "+c.get(Calendar.MINUTE));
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
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE)/intervall*intervall;
		// get the last millisecond of that 15 minute block
		c.set(year, month, day, hour, minute+intervall-1, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		// get the first millisecond of that 15 minute block
		c.set(year, month, day, hour, minute, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		return new TimeObject(String.format("%tR", c), startTime, endTime);
	}
}
