package org.scratch.dateslider;

import android.graphics.Typeface;

public class SliderStyle
{
	private int background_resource;
	
	protected int color_center_primary = 0xFF333333;
	protected int color_center_secondary = 0xFF444444;
	
	protected int color_primary = 0xFF666666;
	protected int color_secondary = 0xFF666666;
	
	protected int color_highlight_center_secondary = 0xFF773333;
	protected int color_highlight_center_primary = 0xFF553333;
	
	protected int color_highlight_secondary = 0xFF442222;
	protected int color_highlight_primary = 0xFF553333;
	
	protected Typeface typeFace = Typeface.DEFAULT;
	
	public SliderStyle(int background_resource)
	{
		this.background_resource=background_resource;		
	}
	
	public void setTypeface(final Typeface typeface)
	{
		this.typeFace=typeface;
	}
	
	public final Typeface getTypeface()
	{
		//TODO: return DEFAULT as fallback ?!?
		return this.typeFace;
	}

	public final int getBackground_resource()
	{
		return background_resource;
	}

	public final void setBackground_resource(int background_resource)
	{
		this.background_resource=background_resource;
	}

	public final int getColor_center_primary()
	{
		return color_center_primary;
	}

	public final void setColor_center_primary(int color_center_primary)
	{
		this.color_center_primary=color_center_primary;
	}

	public final int getColor_center_secondary()
	{
		return color_center_secondary;
	}

	public final void setColor_center_secondary(int color_center_secondary)
	{
		this.color_center_secondary=color_center_secondary;
	}

	public final int getColor_primary()
	{
		return color_primary;
	}

	public final void setColor_primary(int color_primary)
	{
		this.color_primary=color_primary;
	}

	public final int getColor_secondary()
	{
		return color_secondary;
	}

	public final void setColor_secondary(int color_secondary)
	{
		this.color_secondary=color_secondary;
	}

	public final int getColor_highlight_center_secondary()
	{
		return color_highlight_center_secondary;
	}

	public final void setColor_highlight_center_secondary(
			int color_highlight_center_secondary)
	{
		this.color_highlight_center_secondary=color_highlight_center_secondary;
	}

	public final int getColor_highlight_center_primary()
	{
		return color_highlight_center_primary;
	}

	public final void setColor_highlight_center_primary(
			int color_highlight_center_primary)
	{
		this.color_highlight_center_primary=color_highlight_center_primary;
	}

	public final int getColor_highlight_secondary()
	{
		return color_highlight_secondary;
	}

	public final void setColor_highlight_secondary(int color_highlight_secondary)
	{
		this.color_highlight_secondary=color_highlight_secondary;
	}

	public final int getColor_highlight_primary()
	{
		return color_highlight_primary;
	}

	public final void setColor_highlight_primary(int color_highlight_primary)
	{
		this.color_highlight_primary=color_highlight_primary;
	}

	
	
}
