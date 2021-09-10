package com.scs.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
public class SuiteListener implements ISuiteListener {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
    
   

    @Override
    public void onStart(ISuite suite) {
    	
    	 LOGGER.info("ON SUITE Executed");
    }

    @Override
    public void onFinish(ISuite suite) {

        
            LOGGER.info("ON SUITE FINISH ");
        
    }
}