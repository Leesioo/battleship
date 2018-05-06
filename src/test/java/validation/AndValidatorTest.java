package validation;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AndValidatorTest {

    @Test
    public void whenAllNestValidatorsAcceptThenAccept() {
        AndValidator nestedValidator1 = mock(AndValidator.class);
        when(nestedValidator1.isValidate(any(), any())).thenReturn(true);
    }

}