package com.independent.independent.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OvertimePayModel {
    /**
     * 序号
     */
    //@ApiModelProperty(value = "序号" ,required = false)
    private String id;

    /**
     * 部门
     */
    //@ApiModelProperty(value = "部门" ,required = false)
    private String department;

    /**
     * 提交人
     */
    //@ApiModelProperty(value = "提交人" ,required = false)
    private String submitter;

    /**
     * 加班类型（延时加班0、周末加班1、节假日加班2）
     */
    //@ApiModelProperty(value = "加班类型（延时加班0、周末加班1）" ,required = false)
    private Integer overtimeType;

    /**
     * 审批记录
     */
    //@ApiModelProperty(value = "审批记录" ,required = false)
    private String approvalRecord;

    /**
     * 开始时间
     */
    //@ApiModelProperty(value = "开始时间" ,required = false)
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startTime;

    /**
     * 结束时间
     */
    //@ApiModelProperty(value = "结束时间" ,required = false)
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endTime;

    /**
     * 时长
     */
    //@ApiModelProperty(value = "时长" ,required = false)
    private Long duration;

    /**
     * 加班费
     */
    //@ApiModelProperty(value = "加班费" ,required = false)
    private String overtimePay;

    /**
     * 误餐费
     */
    //@ApiModelProperty(value = "误餐费" ,required = false)
    private String compensation;

    /**
     * 合计
     */
    //@ApiModelProperty(value = "合计" ,required = false)
    private String total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Integer getOvertimeType() {
        return overtimeType;
    }

    public void setOvertimeType(Integer overtimeType) {
        this.overtimeType = overtimeType;
    }

    public String getApprovalRecord() {
        return approvalRecord;
    }

    public void setApprovalRecord(String approvalRecord) {
        this.approvalRecord = approvalRecord;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(String overtimePay) {
        this.overtimePay = overtimePay;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
