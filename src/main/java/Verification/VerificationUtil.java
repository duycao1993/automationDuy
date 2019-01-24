package Verification;


import utilities.ExceptionUtil.LoginFailedException;
import utilities.ExceptionUtil.PasswordIncorrectException;
import utilities.ExceptionUtil.SizeViolationException;
import utilities.ExceptionUtil.UnexpectedErrorException;

import java.util.ArrayList;
import java.util.List;

public class VerificationUtil {
    public VerificationUtil() {}

    public List verifyLogin(Exception exception, String expectedResult){
        String status = StatusEnum.Passed.toString();;
        String errorDetail = "Login successful";
        String totalResult = StatusEnum.Passed.toString();
        List resultList = new ArrayList<String>();

        if(exception != null){
            if(exception instanceof PasswordIncorrectException){
                errorDetail = ((PasswordIncorrectException) exception).getExceptionDetail();
            } else if(exception instanceof SizeViolationException){
                errorDetail = ((SizeViolationException) exception).getExceptionDetail();
            } else {
                errorDetail = new UnexpectedErrorException().getExceptionDetail();
            }
            totalResult = ((LoginFailedException) exception).getExceptionDetail();
        }

        if(totalResult.equalsIgnoreCase(expectedResult)){
            status = StatusEnum.Failed.toString();
        } else {
            status = StatusEnum.Passed.toString();
        }

        resultList.add(status);
        resultList.add(errorDetail);

        return resultList;
    }
}
