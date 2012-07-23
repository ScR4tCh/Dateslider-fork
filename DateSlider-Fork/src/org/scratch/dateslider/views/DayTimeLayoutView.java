package org.scratch.dateslider.views;

import java.util.Calendar;

import org.scratch.dateslider.SliderStyle;
import org.scratch.dateslider.TimeView;
import org.scratch.dateslider.DateSlider.TimeObject;

import android.content.Context;

/**
 * More complex implementation of the TimeView which is based on the TimeLayoutView.
 * Sundays are colored red in here.
 *
 */
public class DayTimeLayoutView extends TimeLayoutView
{

	protected boolean isSunday=false;
	
	/**
	 * Constructor
	 * @param context
	 * @param isCenterView true if the element is the centered view in the ScrollLayout 
	 * @param topTextSize	text size of the top TextView in dps
	 * @param bottomTextSize	text size of the bottom TextView in dps
	 * @param lineHeight	LineHeight of the top TextView
	 */
	public DayTimeLayoutView(Context context, boolean isCenterView, int topTextSize, int bottomTextSize, float lineHeight)
	{
		super(context, isCenterView, topTextSize, bottomTextSize, lineHeight);
	}
	

	public void setVals(TimeObject to)
	{
		super.setVals(to);
		// TODO: make it timeZone dependent!
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(to.endTime);
		if (c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY && !isSunday)
		{
			isSunday=true;
			colorMeSunday();
		}
		else if (isSunday && c.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)
		{
			isSunday=false;
			colorMeWorkday();
		}
	}
	
	/**
	 * this method is called when the current View takes a Sunday as time unit
	 */
	protected void colorMeSunday()
	{
		if (isCenter)
		{
			bottomView.setTextColor(slst.getColor_highlight_center_secondary());
			topView.setTextColor(slst.getColor_highlight_center_primary());
		}
		else
		{
			bottomView.setTextColor(slst.getColor_highlight_secondary());
			topView.setTextColor(slst.getColor_highlight_primary());					
		}
	}
	

	/**
	 * this method is called when the current View takes no Sunday as time unit
	 */
	protected void colorMeWorkday()
	{
		if (isCenter)
		{
			topView.setTextColor(slst.getColor_center_primary());
			bottomView.setTextColor(slst.getColor_center_secondary());
		}
		else
		{
			topView.setTextColor(slst.getColor_primary());
			bottomView.setTextColor(slst.getColor_secondary());					
		}			
	}
	
	public void setVals(TimeView other)
	{
		super.setVals(other);
		DayTimeLayoutView otherDay = (DayTimeLayoutView) other;
		if (otherDay.isSunday && !isSunday)
		{
			isSunday = true;
			colorMeSunday();
		}
		else if (isSunday && !otherDay.isSunday)
		{
			isSunday = false;
			colorMeWorkday();
		}
	}
	
	public void setSliderStyle(SliderStyle slst)
	{
		super.setSliderStyle(slst);
		invalidate();
	}
	
}