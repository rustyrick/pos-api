package it.novellis.pos.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
 
public class MyLogFormatter extends Formatter {
	
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
	
	@Override
	public synchronized String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(df.format(new Date(record.getMillis()))).append(" - ");
        sb.append("[").append(record.getSourceClassName()).append(".");
        sb.append(record.getSourceMethodName()).append("] - ");
        sb.append("[").append(record.getLevel()).append("] - ");
        sb.append(formatMessage(record));
        sb.append("\n");
		
		return sb.toString();
	}
}