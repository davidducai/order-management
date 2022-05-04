package businesslogic.validator;

import java.util.regex.Pattern;

import model.Client;

/**
 * Validator pentru adresa de email a clientului.
 * 
 * @author Ducai David
 *
 */
public class EmailValidator implements Validator<Client> {
	
	public static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	@Override
	public void validate(Client object) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		if (!pattern.matcher(object.getEmail()).matches()) {
			throw new IllegalArgumentException("Emailul nu este valid!");
		}
	}
}
