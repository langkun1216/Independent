package com.independent.independent.business;

import com.independent.independent.model.OvertimePayModel;
import com.independent.independent.utils.DateUtil;
import com.independent.independent.utils.ExcelReaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OvertimePay {
    private static String DelayedOvertime = "延时加班";

    private static String WeekendOvertime = "周末加班";

    private static String HolidayOvertime = "节假日加班";

    private static String LunchStartTime = "12:00";
    private static String LunchEndTime = "13:00";

    private static String DinnerStartTime = "18:00";
    private static String DinnerEndTime = "19:00";

    public void getOvertimePayByExcel(String path){
        String path1 = path + "\\Test1.xlsx";
        List<OvertimePayModel> list = getModel(path1);
        String path2 = path + "\\Test2.xlsx";
        getExcel(list,path2);
    }

    public List<OvertimePayModel> getModel(String path){
        List<List<String>> lists = ExcelReaderUtil.readExcel(path);
        List<OvertimePayModel> overtimePayModelList = new ArrayList<>();
        for (int i=1;i<lists.size();i++) {
            List<String> list = lists.get(i);
            OvertimePayModel overtimePayModel = new OvertimePayModel();
            overtimePayModel.setId(list.get(0));
            overtimePayModel.setDepartment(list.get(1));
            overtimePayModel.setSubmitter(list.get(2));
            if(StringUtils.isBlank(list.get(3))){
                continue;
            }
            //判断加班类型
            if(list.get(3).equals(WeekendOvertime)){
                overtimePayModel.setOvertimeType(1);
            }else if(list.get(3).equals(HolidayOvertime)){
                overtimePayModel.setOvertimeType(2);
            }else{
                overtimePayModel.setOvertimeType(0);
            }
            if(StringUtils.isBlank(list.get(4))){
                continue;
            }
            overtimePayModel.setApprovalRecord(list.get(4));
            overtimePayModel.setStartTime(DateUtil.stringToDate(list.get(4).substring(list.get(4).indexOf("开始时间")+4,list.get(4).indexOf("结束时间")),DateUtil.DATE_FORMAT_YMDHM));
            overtimePayModel.setEndTime(DateUtil.stringToDate(list.get(4).substring(list.get(4).indexOf("结束时间")+4,list.get(4).indexOf("时长（小时）")),DateUtil.DATE_FORMAT_YMDHM));
            //overtimePayModel.setDuration((overtimePayModel.getEndTime().getTime()-overtimePayModel.getStartTime().getTime()) / (1000 * 60 * 60));
            //判断时长，如果超过8小时按8小时算
            overtimePayModel.setDuration(Long.valueOf(list.get(4).substring(list.get(4).indexOf("时长（小时）")+6,list.get(4).indexOf("；"))) >Long.valueOf("8") ? Long.valueOf("8") : Long.valueOf(list.get(4).substring(list.get(4).indexOf("时长（小时）")+6,list.get(4).indexOf("；"))));

            //计算加班费用
            Long overtimePay = null;
            if(list.get(3).equals(WeekendOvertime)){
                overtimePay = overtimePayModel.getDuration()*40;
            }else if(list.get(3).equals(HolidayOvertime)){
                overtimePay = overtimePayModel.getDuration()*80;
            }else{
                overtimePay = overtimePayModel.getDuration()*20;
            }
            overtimePayModel.setOvertimePay(overtimePay.toString());

            overtimePayModel.setCompensation(checkTiem(overtimePayModel.getStartTime(),overtimePayModel.getEndTime()));

            Integer total = Integer.parseInt(overtimePayModel.getOvertimePay())+Integer.parseInt(overtimePayModel.getCompensation());
            overtimePayModel.setTotal(total.toString());
            overtimePayModelList.add(overtimePayModel);
        }
        return overtimePayModelList;
    }

    public void getExcel(List<OvertimePayModel> overtimePayModelList,String path){
        String[] headers = {"序号", "部门", "提交人", "加班", "审批记录", "加班费", "误餐费", "合计"};
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);

        // 1.生成字体对象
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        //font.setBoldweight((short) 0.8);

        // 居中格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < overtimePayModelList.size(); i++) {
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(overtimePayModelList.get(i).getId());
            row.createCell(1).setCellValue(overtimePayModelList.get(i).getDepartment());
            row.createCell(2).setCellValue(overtimePayModelList.get(i).getSubmitter());
            row.createCell(3).setCellValue(overtimePayModelList.get(i).getOvertimeType() == 0 ? DelayedOvertime : WeekendOvertime);
            row.createCell(4).setCellValue(overtimePayModelList.get(i).getApprovalRecord());
            row.createCell(5).setCellValue(overtimePayModelList.get(i).getOvertimePay());
            row.createCell(6).setCellValue(overtimePayModelList.get(i).getCompensation());
            row.createCell(7).setCellValue(overtimePayModelList.get(i).getTotal());

            for(int j=0;j<8;j++){
                row.getCell(j).setCellStyle(style);
            }
        }
        //新建文件
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String checkTiem(Date startTime, Date endTime){
        Integer result = 0;
        DateFormat dateFormat = DateFormat.getTimeInstance();//只显示出时分秒
        Date start = DateUtil.stringToDate(dateFormat.format(startTime),"HH:mm");
        Date end = DateUtil.stringToDate(dateFormat.format(endTime),"HH:mm");
        Date lunchStartTime = DateUtil.stringToDate(LunchStartTime,"HH:mm");
        Date lunchEndTime = DateUtil.stringToDate(LunchEndTime,"HH:mm");
        Date dinnerStartTime = DateUtil.stringToDate(DinnerStartTime,"HH:mm");
        Date dinnerEndTime = DateUtil.stringToDate(DinnerEndTime,"HH:mm");

        //开始时间<中餐开始时间 且 结束时间>晚餐结束时间，为两餐
        if(start.getTime() <= lunchStartTime.getTime() && end.getTime() >= dinnerEndTime.getTime()){
            result = 30;
        }
        //开始时间<中餐开始时间 且 结束时间>中餐结束时间 且 结束时间<晚餐开始时间，为中餐
        if(start.getTime() <= lunchStartTime.getTime() && end.getTime() >= lunchEndTime.getTime() && end.getTime() < dinnerEndTime.getTime()){
            result = 15;
        }
        //开始时间>中餐结束时间 且 开始时间<=晚餐开始时间 且 结束时间>=晚餐开始时间，为晚餐
        if(start.getTime() >= lunchEndTime.getTime() && start.getTime() <= dinnerStartTime.getTime() && end.getTime() >= dinnerEndTime.getTime()){
            result = 15;
        }
        return result.toString();
    }
}
