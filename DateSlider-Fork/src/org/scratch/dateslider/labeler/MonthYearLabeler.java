package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.TimeView;
import org.scratch.dateslider.DateSlider.TimeObject;
import org.scratch.dateslider.views.TimeLayoutView;

import android.content.Context;

public class MonthYearLabeler extends Labeler
{
	public MonthYearLabeler(int intervall)
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
		return new TimeObject(String.format("%tb %tY",c,c), startTime, endTime);
	}
	
	/**
	 * rather than a standard TextView this is returning a LimearLayout with two TextViews
	 */
	@Override
	public TimeView createView(Context context, boolean isCenterView)
	{
		return new TimeLayoutView(context, isCenterView,25,8,0.95f);
	}
	
}
