package Verification;

import utilities.ExceptionUtil.PasswordIncorrectException;
import utilities.ExceptionUtil.FormatViolationException;
import utilities.ExceptionUtil.UnexpectedErrorException;

import java.util.ArrayList;
import java.util.List;

public class VerificationUtil {
    public VerificationUtil() {}

    public List<String> verifyLogin(Exception exception, String expectedResult){
        String status;
        String detail = "Login successful";
        String totalResult = "Login successful";
        List<String> resultList = new ArrayList<>();

        if(exception != null){
            if(exception instanceof PasswordIncorrectException){
                detail = ((PasswordIncorrectException) exception).getExceptionDetail();
            } else if(exception instanceof FormatViolationException){
                detail = ((FormatViolationException) exception).getExceptionDetail();
            } else {
                detail = new UnexpectedErrorException().getExceptionDetail();
            }
            if(ErrorsMap.LoginFailed.getErrorDetail().contains(exception.toString())){
                totalResult = "Login failed";
            }

            if(totalResult.equalsIgnoreCase(expectedResult)){
                status = StatusEnum.Passed.toString();
            } else {
                status = StatusEnum.Failed.toString();
            }
        } else {
            status = StatusEnum.Passed.toString();
        }



        resultList.add(status);
        resultList.add(detail);

        return resultList;
    }
}
