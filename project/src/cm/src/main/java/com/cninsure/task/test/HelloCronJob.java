package com.cninsure.task.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloCronJob implements Job {
	public HelloCronJob() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
	/*	System.err.println("HelloCronJob..." + new Date());
	*/
	}
}