package org.w7a.tasks;

import java.util.Date;

import org.w5a.requests.TimestampRequest;

@SuppressWarnings("serial")
public class TimestampTask extends TimestampRequest implements TaskInterface {

	public TimestampTask(TimestampRequest request) {
		request.setTimestamp(new Date().toString());
	}

	@Override
	public void resolve() {
	}
	
}
