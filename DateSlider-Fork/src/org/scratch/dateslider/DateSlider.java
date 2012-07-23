/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * Class for setting up the dialog and initialsing the underlying
 * ScrollLayouts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scratch.dateslider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class DateSlider extends LinearLayout 
{

//	private static String TAG = "DATESLIDER";
	
	protected OnDateSetListener onDateSetListener;
	protected Calendar mTime;
	protected TimeZone mTimeZone;
	protected List<ScrollLayout> mScrollerList = new ArrayList<ScrollLayout>();
	
	protected TextView dateTx;
	
	public DateSlider(Context context,AttributeSet attrs)
	{
		this(context,attrs,null,Calendar.getInstance());
	}
	
	public DateSlider(Context context)
	{
		this(context,null,Calendar.getInstance());
	}
	
	public DateSlider(Context context, OnDateSetListener l, Calendar calendar)
	{
		this(context,null,  l,  calendar);
	}
	
	public DateSlider(Context context,AttributeSet attrs, OnDateSetListener l, Calendar calendar)
	{
		super(context,attrs);
		this.onDateSetListener = l;
		mTimeZone = calendar.getTimeZone();
		mTime = Calendar.getInstance(mTimeZone);
		mTime.setTimeInMillis(calendar.getTimeInMillis());
				
		/**
		 * <TextView
			  android:layout_width="fill_parent"
			  android:layout_height="wrap_content"
			  android:gravity="center_vertical"
			  android:id="@+id/dateSliderTitleText" android:textStyle="bold"
			  android:textColor="#FFFFFF" android:text="">
			</TextView>
		 */
		dateTx = new TextView(context);
		setOrientation(LinearLayout.VERTICAL);
		
		addView(dateTx);
		
		arrangeScroller(null);
	}
	
	public void setTime(final Calendar c)
	{
		mTime = Calendar.getInstance(c.getTimeZone());
        mTime.setTimeInMillis(c.getTimeInMillis());
        arrangeScrollers();
	}
	
	public void setTime(long l)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		setTime(c);
		arrangeScrollers();
	}
	
	public void setMinTime(long min)
	{
		Iterator<ScrollLayout> isc = mScrollerList.iterator();
		while(isc.hasNext())
			isc.next().setMinTime(min);
	}
	
	public void setMaxTime(long max)
	{
		Iterator<ScrollLayout> isc = mScrollerList.iterator();
		while(isc.hasNext())
			isc.next().setMaxTime(max);
	}
	
	public final Calendar getCalendar()
	{
		return mTime;
	}
	
	
	/**
	 * This method allows to change the displayed time of the slider(s).
	 * this can be handy if you need to invoke the dialog several times
	 * using OnPrepareDialog.
	 * @param calendar the calendar object containing the new time
	 */
	public void updateCalendar(Calendar calendar)
	{
		mTimeZone = calendar.getTimeZone();
		mTime = Calendar.getInstance(mTimeZone);
		mTime.setTimeInMillis(calendar.getTimeInMillis());
	}
	
	/**
	 * Sets the Scroll listeners for all ScrollLayouts in "mScrollerList"
	 */
	protected void setListeners()
	{
		for (final ScrollLayout sl: mScrollerList)
		{
			sl.setOnScrollListener(
					new ScrollLayout.OnScrollListener()
					{		
						public void onScroll(long x)
						{
							mTime.setTimeInMillis(x);
							arrangeScroller(sl);
						}
					});
		}
		
	}
	
	protected void arrangeScroller(ScrollLayout source)
	{
		setTitle();
		if (source!=null)
		{
			for (ScrollLayout scroller: mScrollerList)
			{
				if (scroller==source) continue;
				scroller.setTime(mTime.getTimeInMillis(),0);
			}
		}
	}
	
	/**
	 * This method sets the title of the dialog
	 */
	protected void setTitle()
	{
		if (dateTx != null)
		{
			dateTx.setText(getContext().getString(R.string.dateSliderTitle) + 
					String.format(": %te. %tB %tY", mTime,mTime,mTime)); 
		}
	}
	
	
	/**
	 * Defines the interface which defines the methods of the OnDateSetListener
	 */
	public interface OnDateSetListener
	{
		/**
		 * this method is called when a date was selected by the user
		 * @param view			the caller of the method
		 * 
		 */
		public void onDateSet(DateSlider view, Calendar selectedDate);
	}
	
	/**
	 * Very simple helper class that defines a time unit with a label (text) its start-
	 * and end date 
	 *
	 */
	public static class TimeObject
	{
		public final CharSequence text;
		public final long startTime, endTime;
		public TimeObject(final CharSequence text, final long startTime, final long endTime)
		{
			this.text = text;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
	
	public void setSliderStyle(final SliderStyle slst)
	{
		this.post(new Runnable()
				  {
						public void run()
						{
							for(ScrollLayout sv : mScrollerList)
							{
								sv.setBackgroundResource(slst.getBackground_resource());
								sv.setSliderStyle(slst);
								sv.invalidate();
							}
						}
				  }
				);
	}
	
	private void arrangeScrollers()
	{
        for(ScrollLayout sv : mScrollerList)
		{
            sv.setTime(mTime.getTimeInMillis());
        }
    }
	
	
}
