package validation;

import model.PlayerBoard;
import model.Ship;

public interface Validator {

    boolean isValidate(Ship ship, PlayerBoard playerBoard);
}
