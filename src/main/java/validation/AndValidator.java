package validation;

import model.PlayerBoard;
import model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndValidator implements Validator{
    private List<Validator> list;

    public AndValidator(Validator... validators) {
        list = Arrays.asList(validators);
    }

    @Override
    public boolean isValidate(Ship ship, PlayerBoard playerBoard) {
        for (Validator validator : list) {
            boolean result = validator.isValidate(ship, playerBoard);
            if (!result) return false;
        }
        return true;
    }
}
