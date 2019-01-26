package Verification.ENUM;

import Utilities.ExceptionUtil.PasswordIncorrectException;
import Utilities.ExceptionUtil.FormatViolationException;
import Utilities.ExceptionUtil.UnexpectedErrorException;

import java.util.*;

public enum ErrorsMap {

    LoginFailed(Arrays.asList(PasswordIncorrectException.class.toString(), FormatViolationException.class.toString(), UnexpectedErrorException.class.toString()));

    private final List errorDetail;

    public List getErrorDetail() {
        return errorDetail;
    }

    ErrorsMap(List errorDetail) {
      this.errorDetail = errorDetail;
    }



}
