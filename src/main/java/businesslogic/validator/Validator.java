package businesslogic.validator;

/**
 * Interfata <code>Validator</code> se implementeaza pentru clasele care valideaza campul unui obiect din <code>model</code>.
 * 
 * @author Ducai David
 *
 * @param <T>
 */
public interface Validator<T> {

	public void validate(T object);
}
