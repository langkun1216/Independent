package com.independent.independent;

import com.independent.independent.business.OvertimePay;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class BusinessApplication {

    public static void main(String[] args) throws FileNotFoundException {
        Object[] possibleValues = {"导出加班费"};
        Object selectedValue = JOptionPane.showInputDialog(null, "请选择一种业务类型", "选择业务类型",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if(0==JOptionPane.YES_OPTION && selectedValue != null && selectedValue.toString().equals("导出加班费")){
            JFileChooser jfc=new JFileChooser();
            if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File file=jfc.getSelectedFile();
                if(0==JOptionPane.YES_OPTION && file.getPath() != null){
                    String message = new OvertimePay().getOvertimePayByExcel(file.getPath());
                    JOptionPane.showMessageDialog(null,message);
                }
            }
        }
    }

}
