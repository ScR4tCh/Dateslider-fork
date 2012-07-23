package org.scratch.dateslider.views;

import org.scratch.dateslider.SliderStyle;
import org.scratch.dateslider.TimeView;
import org.scratch.dateslider.DateSlider.TimeObject;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This is a more complex implementation of the TimeView consisting of a LinearLayout with
 * two TimeViews.
 *
 */
public class TimeLayoutView extends LinearLayout implements TimeView
{
	
	protected SliderStyle slst = TimeView.DEFAULT_STYLE;
	
	protected long endTime, startTime;
	protected String text;
	protected boolean isCenter=false;
	protected TextView topView, bottomView;
			
	/**
	 * constructor 
	 * 
	 * @param context
	 * @param isCenterView true if the element is the centered view in the ScrollLayout 
	 * @param topTextSize	text size of the top TextView in dps
	 * @param bottomTextSize	text size of the bottom TextView in dps
	 * @param lineHeight	LineHeight of the top TextView
	 */
	public TimeLayoutView(Context context, boolean isCenterView, int topTextSize, int bottomTextSize, float lineHeight)
	{
		super(context);			
		setupView(context, isCenterView, topTextSize, bottomTextSize, lineHeight);
	}
	
	/**
	 * Setting up the top TextView and bottom TextVew
	 * @param context
	 * @param isCenterView true if the element is the centered view in the ScrollLayout 
	 * @param topTextSize	text size of the top TextView in dps
	 * @param bottomTextSize	text size of the bottom TextView in dps
	 * @param lineHeight	LineHeight of the top TextView
	 */
	protected void setupView(Context context, boolean isCenterView, int topTextSize, int bottomTextSize, float lineHeight)
	{
		setOrientation(VERTICAL);
		topView = new TextView(context);
		topView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
		topView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, topTextSize);
		bottomView = new TextView(context);
		bottomView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
		bottomView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, bottomTextSize);
		topView.setLineSpacing(0, lineHeight);
		if (isCenterView)
		{
			isCenter = true;
			topView.setTypeface(Typeface.DEFAULT_BOLD);
			topView.setTextColor(slst.getColor_center_primary());
			bottomView.setTypeface(Typeface.DEFAULT_BOLD);
			bottomView.setTextColor(slst.getColor_center_secondary());
			topView.setPadding(0, 5-(int)(topTextSize/15.0), 0, 0);
		}
		else
		{
			topView.setPadding(0, 5, 0, 0);
			topView.setTextColor(slst.getColor_primary());
			bottomView.setTextColor(slst.getColor_primary());
		}
		addView(topView);addView(bottomView);
		
	}

	public void setVals(TimeObject to)
	{
		text = to.text.toString();
		setText();
		this.startTime = to.startTime;
		this.endTime = to.endTime;
	}
	
	public void setVals(TimeView other)
	{
		text = other.getTimeText().toString();
		setText();
		startTime = other.getStartTime();
		endTime = other.getEndTime();			
	}
	
	/**
	 * sets the TextView texts by splitting the text into two 
	 */
	protected void setText()
	{
		String[] splitTime = text.split(" ");
		topView.setText(splitTime[0]);
		bottomView.setText(splitTime[1]);
	}

	public String getTimeText()
	{
		return text;
	}

	public long getStartTime()
	{
		return startTime;
	}

	public long getEndTime()
	{
		return endTime;
	}
	

	@Override
	public void setSliderStyle(SliderStyle slst)
	{
		this.slst=slst;
		
		if (isCenter)
		{
			//topView.setTypeface(Typeface.DEFAULT_BOLD);
			topView.setTypeface(slst.getTypeface(),Typeface.BOLD);
			topView.setTextColor(slst.getColor_center_primary());
			//bottomView.setTypeface(Typeface.DEFAULT_BOLD);
			bottomView.setTypeface(slst.getTypeface(),Typeface.BOLD);
			bottomView.setTextColor(slst.getColor_center_secondary());
		}
		else 
		{
			topView.setTypeface(slst.getTypeface(),Typeface.NORMAL);
			topView.setTextColor(slst.getColor_primary());
			
			bottomView.setTypeface(slst.getTypeface(),Typeface.NORMAL);
			bottomView.setTextColor(slst.getColor_primary());
		}
		
		
		invalidate();
		topView.invalidate();
		bottomView.invalidate();
	}
	
}