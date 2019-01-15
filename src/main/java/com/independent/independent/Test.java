package com.independent.independent;

import com.independent.independent.business.OvertimePay;
import com.independent.independent.model.OvertimePayModel;
import com.independent.independent.utils.DateUtil;
import com.independent.independent.utils.ExcelReaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {



    public static void main(String[] args){
        Object[] possibleValues = {"导出加班费"};
        Object selectedValue = JOptionPane.showInputDialog(null, "请选择一种业务类型", "选择业务类型",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if(0==JOptionPane.YES_OPTION && selectedValue != null && selectedValue.toString().equals("导出加班费")){
            String path = JOptionPane.showInputDialog("请输入文件所在文件夹，例如：D:\\Test");
            new OvertimePay().getOvertimePayByExcel(path);
        }
    }


}
