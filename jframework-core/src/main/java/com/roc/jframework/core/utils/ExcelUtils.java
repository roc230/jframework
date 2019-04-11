package com.roc.jframework.core.utils;

import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Excel操作工具类
 */
public class ExcelUtils {

    /**
     * 把Excel文件读成内存对象
     * @param filepath 磁盘文件路径
     * @return
     */
    public static XSSFWorkbook read(String filepath){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取指定excel的sheet对象
     * @param workbook workbook
     * @param sheetIndex sheet页标
     * @return
     */
    public static XSSFSheet getSheet(XSSFWorkbook workbook, int sheetIndex){
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     * 在内存中创建一个workbook
     * @return
     */
    public static XSSFWorkbook newWorkbook(){
        return new XSSFWorkbook();
    }

    /**
     * 为指定workbook创建sheet
     * @param workbook workbook
     * @param sheetName shhet页名称
     * @return
     */
    public static XSSFSheet newSheet(XSSFWorkbook workbook, String sheetName){
        if(StringUtils.isNullOrEmpty(sheetName)){
            return workbook.createSheet();
        }else{
            return workbook.createSheet(sheetName);
        }
    }

    /**
     * 创建行
     * @param sheet sheet
     * @param rowIndex 行标
     * @return
     */
    public static XSSFRow newRow(XSSFSheet sheet, int rowIndex){
        return sheet.createRow(rowIndex);
    }

    public static void addToRow(XSSFRow row, Object... cells){
        for(int i = 0; i < cells.length; i++){
            row.createCell(i).setCellValue(cells[i].toString());
        }
    }

    /**
     * 创建row并设置内容
     * @param sheet sheet
     * @param rowIndex rowIndex
     * @param cells 列内容集合
     * @return
     */
    public static XSSFRow addRowToSheet(XSSFSheet sheet, int rowIndex, Object... cells){
        XSSFRow row = sheet.createRow(rowIndex);
        for(int i = 0; i < cells.length; i++){
            row.createCell(i).setCellValue(cells[i].toString());
        }
        return row;
    }

    /**
     * 添加内容到末尾
     * @param sheet
     * @param cells
     * @return
     */
    public static int insertLastRowToSheet(XSSFSheet sheet, Object... cells){
        int lastRowNum = sheet.getLastRowNum();
        XSSFRow row = sheet.createRow(lastRowNum + 1);
        for(int i = 0; i < cells.length; i++){
            row.createCell(i).setCellValue(cells[i].toString());
        }
        return lastRowNum + 1;
    }

    /**
     * 保存workbook到磁盘
     * @param workbook workbook
     * @param filepath 磁盘文件路径
     */
    public static void saveWorkbook(XSSFWorkbook workbook, String filepath){
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(new File(filepath));
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
