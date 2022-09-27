package br.com.api.condomanager.condomanager.util.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateFormat, String> {

	@Override
	public void initialize(DateFormat dateStr) {
	}

	@Override
	public boolean isValid(String dateStr, ConstraintValidatorContext cxt) {
		if (dateStr != null) {
			try {
				// Create a Pattern object
				Pattern r = Pattern.compile("^([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})$");

				// Now create matcher object.
				Matcher m = r.matcher(dateStr);

				if (m.find()) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Calendar date = Calendar.getInstance();
					date.setTime(formatter.parse(dateStr));

					if ((Integer.valueOf(m.group(1)) != date.get(Calendar.DAY_OF_MONTH))
							|| (Integer.valueOf(m.group(2)) != date.get(Calendar.MONTH) + 1)
							|| (Integer.valueOf(m.group(3)) != date.get(Calendar.YEAR))) {
						return false;
					}
				} else {
					return false;
				}
			} catch (ParseException e) {
				return false;
			}
		}

		return true;
	}

}
