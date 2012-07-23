/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * DateSlider which allows for selecting of a date including a time 
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

import java.util.Calendar;

import org.scratch.dateslider.DateSlider.OnDateSetListener;
import org.scratch.dateslider.labeler.DefDayLabeler;
import org.scratch.dateslider.labeler.MonthYearLabeler;
import org.scratch.dateslider.labeler.TwentyfourHourTimeLabeler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

public class DateTimeSlider extends DateSlider
{
	private int minuteintervall = 1;
	
	// the month labeler  takes care of providing each TimeTextView element in the monthScroller
	// with the right label and information about its time representation
	protected MonthYearLabeler monthLabeler = new MonthYearLabeler(1);
		

	// the day labeler takes care of providing each TimeTextView element in the dayScroller
	// with the right label and information about its time representation
	protected DefDayLabeler dayLabeler = new DefDayLabeler(1);
		
	// the day labeler takes care of providing each TimeTextView element in the timeScroller
	// with the right label and information about its time representation
	protected TwentyfourHourTimeLabeler timeLabeler = new TwentyfourHourTimeLabeler(minuteintervall);
	
	public DateTimeSlider(Context context,AttributeSet attrs)
	{
		this(context,attrs,null, 1, Calendar.getInstance());
	}
	
	public DateTimeSlider(Context context)
	{
		this(context,null,null,1,Calendar.getInstance());
	}
	
	public DateTimeSlider(Context context, OnDateSetListener l, Calendar calendar)
	{
		this(context, null, l , 1,  calendar);
	}
	
	public DateTimeSlider(Context context, AttributeSet attrs, OnDateSetListener l,	int m, Calendar calendar)
	{
		super(context, l, calendar);
		
		this.minuteintervall=m;
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		// create the month scroller and assign its labeler and add it to the layout
		ScrollLayout mMonthScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mMonthScroller.setLabeler(monthLabeler, mTime.getTimeInMillis(),180,60);
		addView(mMonthScroller, 0,lp);
		mScrollerList.add(mMonthScroller);
		
		// create the month scroller and assign its labeler and add it to the layout
		ScrollLayout mDayScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mDayScroller.setLabeler(dayLabeler, mTime.getTimeInMillis(),120,60);
		addView(mDayScroller, 1, lp);
		mScrollerList.add(mDayScroller);
		
		// create the minute scroller and assign its labeler and add it to the layout
		ScrollLayout mTimeScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mTimeScroller.setLabeler(timeLabeler, mTime.getTimeInMillis(),80,60);
		addView(mTimeScroller, 2,lp);
		mScrollerList.add(mTimeScroller);
		
		// this method _has_ to be called to set the onScrollListeners for all the Scrollers
		// in the mScrollerList.
		setListeners();
	}
	
	@Override
	protected void setTitle()
	{
		if (dateTx != null)
		{
			if(minuteintervall==0)
				minuteintervall=15;
			
			System.err.println(this.minuteintervall*this.minuteintervall);
			int minute = mTime.get(Calendar.MINUTE)/this.minuteintervall*this.minuteintervall;
			dateTx.setText(String.format("Selected DateTime: %te/%tm/%ty %tH:%02d",mTime,mTime,mTime,mTime,minute)); 
		}
	}
}
