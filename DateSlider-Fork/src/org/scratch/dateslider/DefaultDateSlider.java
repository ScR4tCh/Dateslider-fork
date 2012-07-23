/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * Default DateSlider which allows for an easy selection of a date 
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

import org.scratch.dateslider.labeler.DayMonthLabeler;
import org.scratch.dateslider.labeler.MonthYearLabeler;

import android.content.Context;
import android.view.LayoutInflater;

public class DefaultDateSlider extends DateSlider
{
	
	// the month labeler  takes care of providing each TimeTextView element in the monthScroller
	// with the right label and information about its time representation
	protected MonthYearLabeler monthLabeler = new MonthYearLabeler(1);
		

	// the day labeler takes care of providing each TimeTextView element in the dayScroller
	// with the right label and information about its time representation
	protected DayMonthLabeler dayLabeler = new DayMonthLabeler(1);
	
	/**
	 * initialise the DateSlider
	 * 
	 * @param context
	 * @param l
	 * @param calendar calendar set with the date that should appear at start up
	 */
	public DefaultDateSlider(Context context, OnDateSetListener l, Calendar calendar)
	{
		super(context, l, calendar);
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		// create the month scroller and assign its labeler and add it to the layout
		ScrollLayout mMonthScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mMonthScroller.setLabeler(monthLabeler, mTime.getTimeInMillis(),90,60);
		addView(mMonthScroller, 0,lp);
		mScrollerList.add(mMonthScroller);
		
		// create the month scroller and assign its labeler and add it to the layout
		ScrollLayout mDayScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mDayScroller.setLabeler(dayLabeler, mTime.getTimeInMillis(),45,60);
		addView(mDayScroller, 1, lp);
		mScrollerList.add(mDayScroller);
		
		// this method _has_ to be called to set the onScrollListeners for all the Scrollers
		// in the mScrollerList.
		setListeners();
	}
}
