package com.roc.jframework.core.utils;

import com.roc.jframework.basic.ext.HashMapExt;
import com.roc.jframework.basic.utils.DateUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

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

    /**
     * 获取指定坐标单元格
     * @param sheet sheet
     * @param x x坐标
     * @param y y坐标
     * @return
     */
    public static XSSFCell getCell(XSSFSheet sheet, int x, int y){
        XSSFRow row = sheet.getRow(x);
        if(row == null){
            return null;
        }
        XSSFCell cell = row.getCell(y);
        return cell;
    }

    /**
     * 获取行数
     * @param sheet
     * @return
     */
    public static int getRowCount(XSSFSheet sheet){
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * 获取列数
     * @param sheet
     * @return
     */
    public static int getColumnCount(XSSFSheet sheet){
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    /**
     * 获取指定列
     * @param sheet
     * @param columnIndex
     * @return
     */
    public static List<XSSFCell> getColumn(XSSFSheet sheet, int columnIndex){

        List<XSSFCell> list = ListUtils.newArrayList();

        for(int i = 0; i < getRowCount(sheet); i++){
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(columnIndex);
            list.add(cell);
        }
        return list;
    }

    public static HashMapExt<String, List<String>> getDateColumn(XSSFSheet sheet, int columnIndex, String dateformat){
        List<XSSFCell> list = getColumn(sheet, columnIndex);
        List<String> l = ListUtils.newArrayList();
        for(XSSFCell cell : list){
           if(cell.getCellType().equals(CellType.NUMERIC)){
                l.add(DateUtils.getString(cell.getDateCellValue(), dateformat));
            }else if(cell.getCellType().equals(CellType.STRING)){
                l.add(cell.getStringCellValue());
            }else{
                System.out.println("非日期内容将被忽略");
            }
        }
        HashMapExt<String, List<String>> map = new HashMapExt<>();
        map.putE(l.get(0), l.subList(1, l.size()));
        return map;
    }

    public static HashMapExt<String, List<Double>> getDoubleColumn(XSSFSheet sheet, int columnIndex){
        List<XSSFCell> list = getColumn(sheet, columnIndex);
        List<Double> l = ListUtils.newArrayList();
        //取第一个String为key
        String key = "";
        for(int i = 0; i < list.size(); i++){
            XSSFCell cell = list.get(i);

            if(cell.getCellType().equals(CellType.STRING)){
                key = cell.getStringCellValue();
            }else if(cell.getCellType().equals(CellType.NUMERIC)){
                l.add(cell.getNumericCellValue());
            }
        }

        HashMapExt<String, List<Double>> map = new HashMapExt<>();
        map.putE(key, l);
        return map;
    }

    public static Map<String, List<String>> getAllRows(XSSFSheet sheet){
        Map<String,List<String>> map = new LinkedHashMap<>();
        for(int i = 0; i < getRowCount(sheet); i++){
            XSSFRow row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            List<String> list = ListUtils.newArrayList();
            for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
                XSSFCell cell = row.getCell(j);
                if(cell == null){
                    continue;
                }
                String value = getString(cell);
                list.add(value);
            }
            map.put(list.get(0), list.subList(1, list.size()));
        }
        return map;
    }

    public static String getString(XSSFCell cell){
        if(CellType.STRING.equals(cell.getCellType())){
            return cell.getStringCellValue();
        }else if(CellType.NUMERIC.equals(cell.getCellType())){
            return String.valueOf(cell.getNumericCellValue());
        }else if(CellType.BLANK.equals(cell.getCellType())){
            return "";
        }else{
            return cell.getErrorCellString();
        }
    }

}
