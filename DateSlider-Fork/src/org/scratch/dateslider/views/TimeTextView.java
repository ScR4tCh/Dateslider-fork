package org.scratch.dateslider.views;

import org.scratch.dateslider.DateSlider;
import org.scratch.dateslider.SliderStyle;
import org.scratch.dateslider.TimeView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * This is a simple implementation of a TimeView which realised through a TextView.
 *
 */
public class TimeTextView extends TextView implements TimeView
{
	
	protected SliderStyle slst = TimeView.DEFAULT_STYLE;
	private long endTime, startTime;
	
	private boolean _isCenterView;
	
	/**
	 * constructor 
	 * @param context
	 * @param isCenterView true if the element is the centered view in the ScrollLayout
	 * @param textSize text size in dps
	 */
	public TimeTextView(Context context, boolean isCenterView, int textSize)
	{
		super(context);
		
		this._isCenterView=isCenterView;
		
		setupView(isCenterView, textSize);
	}

	/**
	 * this method should be overwritten by inheriting classes to define its own look and feel
	 * @param isCenterView true if the element is in the center of the scrollLayout
	 * @param textSize textSize in dps
	 */	
	protected void setupView(boolean isCenterView, int textSize)
	{
		setGravity(Gravity.CENTER);
		setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		if (isCenterView)
		{
			setTypeface(Typeface.DEFAULT_BOLD);
			setTextColor(slst.getColor_center_primary());
		}
		else
		{
			setTextColor(slst.getColor_primary());
		}
	}
	
	public void setVals(DateSlider.TimeObject to)
	{
		setText(to.text);
		this.startTime = to.startTime;
		this.endTime = to.endTime;
	}
	
	public void setVals(TimeView other)
	{
		setText(other.getTimeText());
		startTime = other.getStartTime();
		endTime = other.getEndTime();
	}

	public long getStartTime()
	{
		return this.startTime;
	}

	public long getEndTime()
	{
		return this.endTime;
	}

	public String getTimeText()
	{
		return getText().toString();
	}

	@Override
	public void setSliderStyle(SliderStyle slst)
	{
		this.slst=slst;
		
		if (_isCenterView)
		{
			//setTypeface(Typeface.DEFAULT_BOLD);
			setTypeface(slst.getTypeface(),Typeface.BOLD);
			setTextColor(slst.getColor_center_primary());
		}
		else
		{
			setTypeface(slst.getTypeface(),Typeface.NORMAL);
			setTextColor(slst.getColor_primary());
		}
		
		invalidate();
	}
}