/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * DateSlider which allows for an easy selection of only a month and a year 
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

import org.scratch.dateslider.labeler.DefMonthLabeler;
import org.scratch.dateslider.labeler.DefYearLabeler;

import android.content.Context;
import android.view.LayoutInflater;

public class MonthYearDateSlider extends DateSlider
{
	// the year labeler  takes care of providing each TimeTextView element in the yearScroller
	// with the right label and information about its time representation
	protected DefYearLabeler yearLabeler = new DefYearLabeler(1);
		
	// the month labeler  takes care of providing each TimeTextView element in the monthScroller
	// with the right label and information about its time representation
	protected DefMonthLabeler monthLabeler = new DefMonthLabeler(1);
		
	
	public MonthYearDateSlider(Context context, OnDateSetListener l, Calendar calendar)
	{
		super(context, l, calendar);
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		// create the year scroller and assign its labeler and add it to the layout
		ScrollLayout mYearScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mYearScroller.setLabeler(yearLabeler, mTime.getTimeInMillis(),200,60);
		addView(mYearScroller, 0,lp);
		mScrollerList.add(mYearScroller);
		
		// create the month scroller and assign its labeler and add it to the layout
		ScrollLayout mMonthScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
		mMonthScroller.setLabeler(monthLabeler, mTime.getTimeInMillis(),150,60);
		addView(mMonthScroller, 1,lp);
		mScrollerList.add(mMonthScroller);
		
		// this method _has_ to be called to set the onScrollListeners for all the Scrollers
		// in the mScrollerList.
		setListeners();
	}
	
	
	/**
	 * override the setTitle method so that only the month and the year are shown.
	 */
	@Override
	protected void setTitle()
	{
		if (dateTx != null)
		{
			dateTx.setText(getContext().getString(R.string.dateSliderTitle) + String.format(": %tB %tY",mTime,mTime)); 
		}
	}

}
