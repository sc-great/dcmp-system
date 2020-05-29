package com.great.tool;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建并保存Excel
 * @author LM
 *
 */
public class ExcelUtil {
	
	// 创建一个EXCEL的方法
	public static void createExcel(ExcelBean excelBean) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet，对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(excelBean.getSheetName());
		
		// 第三步，添加标题  -----------------------------START---------------------------------------
		// 标题样式（加粗，垂直居中）
		HSSFCellStyle style_title = wb.createCellStyle();
		style_title.setAlignment(HorizontalAlignment.CENTER); // 水平居中
		style_title.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
		HSSFFont font_title = wb.createFont(); // 字体
		font_title.setBold(true); // 加粗
		font_title.setFontHeightInPoints((short) 10); // 设置标题字体大小
		style_title.setFont(font_title);
		// 在第 0 行创建rows (表标题)
		HSSFRow title = sheet.createRow(0);
		title.setHeightInPoints(20); // 行高
		HSSFCell cellValue = title.createCell(0);
		cellValue.setCellValue(excelBean.getTitle()); // 标题内容
		cellValue.setCellStyle(style_title);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (excelBean.getHeader().length - 1))); // 合并单元格
		// 第三步，添加标题  -----------------------------END---------------------------------------
		// 默认列宽
		sheet.setDefaultColumnWidth(20); 
		
		// 第四步，在sheet中添加表头第 1 行，注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(1);
		// 表头样式
		HSSFCellStyle style_header = wb.createCellStyle();
		style_header.setAlignment(HorizontalAlignment.CENTER); // 居中
		HSSFFont font_header = wb.createFont();
		font_header.setBold(true); // 加粗
		style_header.setFont(font_header);
		
		// 添加 excel 表头
		HSSFCell cell = null;
		for (int i = 0; i < excelBean.getHeader().length; i ++) {
			cell = row.createCell(i);
			cell.setCellValue(excelBean.getHeader()[i]);
			cell.setCellStyle(style_header);
		}

		// 第五步，写入实体数据
		int i = 0;
		for (Integer str : excelBean.getMemberMap().keySet()) {
			row = sheet.createRow(i + 2); // 除去标题和表头，从第 3 行开始
			List<String> list = excelBean.getMemberMap().get(str);

			// 第六步，创建单元格，并设置值
			for (int j = 0; j < excelBean.getHeader().length; j ++) {
				row.createCell(j).setCellValue(list.get(j));
			}

			// 第七步，将文件存到指定位置
			try {
				FileOutputStream fos = new FileOutputStream(excelBean.getPath()); // "C:\\Users\\Administrator\\Desktop\\Members.xls"
				wb.write(fos);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	
	public static List<List<String>> exportExcelData(String fileLocation, FileInputStream fileInputStream) {
        List<List<String>> result = new ArrayList<>();
        Workbook workbook = null;
        try {
            // 判断是否为Excel类型文件
            if (fileLocation.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fileInputStream);
            } else {
                workbook = new XSSFWorkbook(fileInputStream);
            }

            Sheet sheet = workbook.getSheetAt(0);
            int maxRowNum = sheet.getLastRowNum() + 1;
            for (int i = 0; i < maxRowNum; i++) {
                List<String> rowList = new ArrayList<>();
                Row row = sheet.getRow(i);
                if (row != null) {
                    // getLastCellNum，是获取最后一个不为空的列是第几个
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        String cellStr = row.getCell(j) != null ? getCellStringValue(row.getCell(j)) : "";
                        rowList.add(cellStr);
                    }
                }
                result.add(rowList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

	public static String getCellStringValue(Cell cell) {
        String cellValue;
        switch (cell.getCellType()) {
            case NUMERIC:
                DecimalFormat df = new DecimalFormat("0");
                cellValue = df.format(cell.getNumericCellValue());
                break;

            case STRING:
                cellValue = cell.getStringCellValue();
                break;

            case BOOLEAN:
                cellValue = cell.getBooleanCellValue() + "";
                break;

            case FORMULA:
                cellValue = cell.getCellFormula() + "";
                break;

            case BLANK:
                cellValue = "";
                break;

            case ERROR:
                cellValue = "非法字符";
                break;

            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}
