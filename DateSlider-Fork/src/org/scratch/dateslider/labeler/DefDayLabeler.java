package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.TimeView;
import org.scratch.dateslider.DateSlider.TimeObject;
import org.scratch.dateslider.views.DayTimeLayoutView;

import android.content.Context;

public class DefDayLabeler extends Labeler
{
	public DefDayLabeler(int intervall)
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
		c.add(Calendar.DAY_OF_MONTH, val);
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
		// set calendar to first millisecond of the day
		c.set(year, month, day, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		long startTime = c.getTimeInMillis();
		// set calendar to last millisecond of the day
		c.set(year, month, day, 23, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endTime = c.getTimeInMillis();
		return new TimeObject(String.format("%td %ta",c,c), startTime, endTime);
	}

	/**
	 * rather than a standard TextView this is returning a LimearLayout with two TextViews
	 */
	@Override
	public TimeView createView(Context context, boolean isCenterView)
	{
		return new DayTimeLayoutView(context, isCenterView,30,8,0.8f);
	}
}
