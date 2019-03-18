package com.independent.independent;

import com.independent.independent.business.ExcelFileFilter;
import com.independent.independent.business.OvertimePay;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class BusinessApplication {

    private static final String DIALOG_OPTION_OVERTIME = "导出加班费";

    private static final String DIALOG_MESSAGE = "请选择一种业务类型";

    private static final String DIALOG_TITLE = "选择业务类型";

    public static void main(String[] args) throws IOException {
        Object[] possibleValues = { DIALOG_OPTION_OVERTIME };
        Object selectedValue = JOptionPane.showInputDialog(null, DIALOG_MESSAGE, DIALOG_TITLE,JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if(0==JOptionPane.YES_OPTION && selectedValue != null && selectedValue.toString().equals(DIALOG_OPTION_OVERTIME)){
            //文件选择器
            JFileChooser jfc = new JFileChooser();
            //excel过滤器
            ExcelFileFilter excelFilter = new ExcelFileFilter();
            jfc.addChoosableFileFilter(excelFilter);
            jfc.setFileFilter(excelFilter);

            //456454sasd

            if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File file=jfc.getSelectedFile();
                if(0==JOptionPane.YES_OPTION && file.getPath() != null){
                    String message = new OvertimePay().getOvertimePayByExcel(file);
                    JOptionPane.showMessageDialog(null,message);
                }
            }
        }
    }

}
