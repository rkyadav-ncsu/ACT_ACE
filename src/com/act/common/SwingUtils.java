package com.act.common;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SwingUtils {
	
	public static GridBagConstraints getConstraints(int gridx,
			int gridy,
			int gridwidth,
			double weightx,
			double weighty,
			int anchor, 
			int fill,
			int top,
			int left,
			int bottom,
			int right){
		
	
		GridBagConstraints gc = new GridBagConstraints(gridy,
														gridx,
														gridwidth,
														1,
														weightx,
														weighty,
														anchor,
														fill,
														new Insets(top, left, bottom, right),
														0,0 ) ;
		
		return gc;
	
	
	}

	public static GridBagConstraints getConstraints(int gridx,
			int gridy,
			int gridwidth,
			int gridHeight,
			double weightx,
			double weighty,
			int anchor, 
			int fill,
			int top,
			int left,
			int bottom,
			int right){
		
	
		GridBagConstraints gc = new GridBagConstraints(gridy,
														gridx,
														gridwidth,
														gridHeight,
														weightx,
														weighty,
														anchor,
														fill,
														new Insets(top, left, bottom, right),
														0,0 ) ;
		
		return gc;
	
	
	}
}
