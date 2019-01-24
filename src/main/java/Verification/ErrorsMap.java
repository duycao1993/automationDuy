package Verification;

import com.google.common.collect.Lists;
import utilities.ExceptionUtil.PasswordIncorrectException;
import utilities.ExceptionUtil.SizeViolationException;

import java.util.*;

public enum ErrorsMap {

    LoginFailed(Arrays.asList(PasswordIncorrectException.class.toString(), SizeViolationException.class.toString()));

    private final List errorDetail;

    public List getErrorDetail() {
        return errorDetail;
    }

    ErrorsMap(List errorDetail) {
      this.errorDetail = errorDetail;
    }



}
