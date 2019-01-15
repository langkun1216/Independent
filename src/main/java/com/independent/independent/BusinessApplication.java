package com.independent.independent;

import com.independent.independent.business.OvertimePay;

import javax.swing.*;

public class BusinessApplication {

    public static void main(String[] args){
        Object[] possibleValues = {"导出加班费"};
        Object selectedValue = JOptionPane.showInputDialog(null, "请选择一种业务类型", "选择业务类型",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if(0==JOptionPane.YES_OPTION && selectedValue != null && selectedValue.toString().equals("导出加班费")){
            String path = JOptionPane.showInputDialog("请输入文件所在文件夹，例如：G:\\test\\Test1.xlsx");
            if(0==JOptionPane.YES_OPTION && path != null){
                String message = new OvertimePay().getOvertimePayByExcel(path);
                JOptionPane.showMessageDialog(null,message);
            }
        }
    }

}
