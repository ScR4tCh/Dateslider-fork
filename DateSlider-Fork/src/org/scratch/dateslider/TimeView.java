/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * This interface represents Views that are put onto the ScrollLayout
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


import org.scratch.dateslider.DateSlider.TimeObject;


/**
 * This interface represents the views that will settle the ScrollLayout. Each element has to deal
 * with its label via the setText method and needs to contain the start and end time of the element.
 * Moreover this interface contains three implementations one simple TextView, A two-row 
 * LinearLayout and a LinearLayout which colors Sundays red.
 *
 */
public interface TimeView
{

	public static final SliderStyle DEFAULT_STYLE = new SliderStyle(R.drawable.slider_silver_patch);
	
	public void setVals(TimeObject to);
	public void setVals(TimeView other);
	public String getTimeText();
	public long getStartTime();
	public long getEndTime();
	
	public void setSliderStyle(final SliderStyle slst);		
}
