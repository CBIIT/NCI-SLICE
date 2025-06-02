package com.nih.slice.cdk;

import java.util.HashMap;
import java.util.Map;

public class Config {
	
	Map<Integer, Boolean> whereConfig = new HashMap<Integer, Boolean>();
	
	public void initWhereConfig() {
		whereConfig.put(SliceConstants.ONPATH, false);
		whereConfig.put(SliceConstants.OFFPATH, false);
		whereConfig.put(SliceConstants.ONRING, false);
		whereConfig.put(SliceConstants.OFFRING, false);
		//whereConfig.put(SmartsChemConstants.ONCURRENTRING, false);
		//whereConfig.put(SmartsChemConstants.OFFCURRENTRING, false);
		whereConfig.put(SliceConstants.ONTHEBRIDGE, false);
		whereConfig.put(SliceConstants.OFFTHEBRIDGE, false);
	}
	
}
