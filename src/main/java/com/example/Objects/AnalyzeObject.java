package com.example.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Sigal on 5/29/2016.
 */
public class AnalyzeObject {
    private int totalQuestions;
    private int successCount;
    private int failureCount;
    private BigDecimal successPercent;
    private BigDecimal failurePercent;
    private int subjectOid;

    public AnalyzeObject(int subjectOid, List<UserQuestionObject> questionObjects) {
        this.subjectOid = subjectOid;
        this.totalQuestions = questionObjects.size();
        for(UserQuestionObject questionObject : questionObjects) {
            if(questionObject.isSuccess()) {
                this.successCount++;
            } else {
                this.failureCount++;
            }
        }
        this.successPercent = new BigDecimal(this.failureCount).divide(new BigDecimal(this.totalQuestions), 2, RoundingMode.CEILING);

    }

    public AnalyzeObject(List<UserQuestionObject> questionObjects) {
        this.subjectOid = subjectOid;
        this.totalQuestions = questionObjects.size();
        for(UserQuestionObject questionObject : questionObjects) {
            if(questionObject.isSuccess()) {
                this.successCount++;
            } else {
                this.failureCount++;
            }
        }
        this.successPercent = new BigDecimal(this.failureCount).divide(new BigDecimal(this.totalQuestions), 2, RoundingMode.CEILING);

    }


    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public BigDecimal getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(BigDecimal successPercent) {
        this.successPercent = successPercent;
    }

    public BigDecimal getFailurePercent() {
        return failurePercent;
    }

    public void setFailurePercent(BigDecimal failurePercent) {
        this.failurePercent = failurePercent;
    }

    public int getSubjectOid() {
        return subjectOid;
    }

    public void setSubjectOid(int subjectOid) {
        this.subjectOid = subjectOid;
    }
}
