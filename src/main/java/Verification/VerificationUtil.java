package Verification;

import Verification.ENUM.ErrorsMap;
import Verification.ENUM.StatusEnum;
import Utilities.ExceptionUtil.PasswordIncorrectException;
import Utilities.ExceptionUtil.FormatViolationException;
import Utilities.ExceptionUtil.UnexpectedErrorException;

import java.util.ArrayList;
import java.util.List;

public class VerificationUtil {
    public VerificationUtil() {}

    public List<String> verifyLogin(Exception exception, String expectedResult){
        String status;
        String detail;
        List<String> resultList = new ArrayList<>();

        if(exception != null){
            String totalResult = "";
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
            detail = "Login successful";
            status = StatusEnum.Passed.toString();
        }



        resultList.add(status);
        resultList.add(detail);

        return resultList;
    }
}
