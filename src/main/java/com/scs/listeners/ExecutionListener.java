package com.scs.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;

/**
 *Customized Listener class for handling test execution.
 * @author MXC0RO7
 *         
 */
public class ExecutionListener implements IExecutionListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

	@Override
	public void onExecutionStart() {
		LOGGER.info("ON EXECUTION START");
		

	}

	@Override
	public void onExecutionFinish() {

		LOGGER.info("ON EXECUTION FINISH");
	}

}
