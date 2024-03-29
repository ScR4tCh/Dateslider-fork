package org.scratch.dateslider.labeler;

import java.util.Calendar;

import org.scratch.dateslider.TimeView;
import org.scratch.dateslider.DateSlider.TimeObject;
import org.scratch.dateslider.views.TimeTextView;

import android.content.Context;

/**
 * This class has the purpose of telling the corresponding scroller, which values make up
 * a single TimeTextView element.   
 *
 */
public abstract class Labeler
{	
	protected int intervall;
	
	public Labeler(int intervall)
	{
		this.intervall=intervall;
	}
	
	/**
	 * gets called once, when the scroller gets initialised
	 * @param time
	 * @return the TimeObject representing "time"
	 */
	public TimeObject getElem(long time)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return timeObjectfromCalendar(c);
	}
	
	/**
	 * returns a new TimeTextView instance, is only called a couple of times in the
	 * initialisation process
	 * 
	 * @param context
	 * @param isCenterView is true when the view is the central view
	 * @return 
	 */
	public TimeView createView(Context context, boolean isCenterView)
	{
		return new TimeTextView(context, isCenterView, 25);
	}
	
	/**
	 * This method will be called constantly, whenever new date information is required
	 * it receives a timestamps and adds "val" time units to that time and returns it as
	 * a TimeObject
	 * 
	 * @param time
	 * @param val
	 * @return
	 */
	public abstract TimeObject add(long time, int val);
	protected abstract TimeObject timeObjectfromCalendar(Calendar c);
}