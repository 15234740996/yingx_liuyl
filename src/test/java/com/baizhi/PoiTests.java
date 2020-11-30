package com.baizhi;



import com.baizhi.entity.Emp;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class PoiTests {

    @Test
    public void PoiImport(){

        try {

            //创建Excel文档
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:\\testpoi.xls")));

            //获取Sheet
            Sheet sheet = workbook.getSheet("学生信息表1");

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {

                //获取行
                Row row = sheet.getRow(i);

                //获取单元格
                Cell cell = row.getCell(0);
                //获取单元格内容
                String id = cell.getStringCellValue();
                
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                Date birthday = row.getCell(3).getDateCellValue();

                Emp emp = new Emp(id, name, age, birthday);
                System.out.println(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void PoiExport(){

        ArrayList<Emp> emps = new ArrayList<>();

        emps.add(new Emp("1","小黑",18,new Date()));
        emps.add(new Emp("2","小黄",30,new Date()));
        emps.add(new Emp("3","小可爱",18,new Date()));
        emps.add(new Emp("4","小狗蛋",23,new Date()));

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表   参数:工作表表明  默认:sheet1,sheet2....
        Sheet sheet1 = workbook.createSheet("学生信息表1");

        //设置列宽  参数:列索引,列宽  单位 1/256
        sheet1.setColumnWidth(3,20*256);

        //创建合并单元格对象  参数:int firstRow(开始行), int lastRow(结束行), int firstCol(开始单元格), int lastCol(结束单元格)
        CellRangeAddress addresses = new CellRangeAddress(0,0,0,3);

        //合并单元格
        sheet1.addMergedRegion(addresses);


        //创建字体对象
        Font font = workbook.createFont();

        font.setFontName("微软雅黑"); //字体
        font.setBold(true); //加粗
        font.setColor(IndexedColors.GREEN.getIndex()); //颜色
        font.setFontHeightInPoints((short)15);  //字号
        //font.setItalic(true);    //斜体
        font.setUnderline(FontFormatting.U_SINGLE);  //下划线


        //创建单元格样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);  //文字居中
        cellStyle1.setFont(font); //设置字体样式

        //创建标题行
        Row r = sheet1.createRow(0);
        //创建标题单元格
        Cell c = r.createCell(0);
        //设置单元格样式
        c.setCellStyle(cellStyle1);
        //单元格设置数据
        c.setCellValue("学生信息表");

        //目录行
        String[] titles={"ID","名字","年龄","生日"};

        //创建一行   参数:行下标(从0开始)
        Row row = sheet1.createRow(1);

        //设置行高  参数:行高  单位 1/20
        row.setHeight((short)(20*20));

        for (int i = 0; i < titles.length; i++) {

            //创建单元格
            Cell cell = row.createCell(i);

            //设置数据
            cell.setCellValue(titles[i]);
        }

        //创建日期样式对象
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd");//设置日期样式

        //创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        //将日期样式交给单元格样式对象
        cellStyle.setDataFormat(format);

        //处理数据
        for (int i = 0; i < emps.size(); i++) {

            //创建一行
            Row rows = sheet1.createRow(i+2);

            //创建单元格  设置数据
            rows.createCell(0).setCellValue(emps.get(i).getId());
            rows.createCell(1).setCellValue(emps.get(i).getName());
            rows.createCell(2).setCellValue(emps.get(i).getAge());

            //创建单元格
            Cell cell = rows.createCell(3);
            //设置数据
            cell.setCellValue(emps.get(i).getBirthday());
            //给单元格设置样式
            cell.setCellStyle(cellStyle);
        }

        try {
            //导出
            workbook.write(new FileOutputStream(new File("E:\\testpoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPoi(){

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表   参数:工作表表明  默认:sheet1,sheet2....
        Sheet sheet1 = workbook.createSheet("学生信息表1");
        Sheet sheet2 = workbook.createSheet("学生信息表2");

        //创建一行   参数:行下标(从0开始)
        Row row = sheet1.createRow(5);

        //创建一个单元格  参数:单元格下标(从0开始)
        Cell cell = row.createCell(2);

        //给单元格设置数据
        cell.setCellValue("这是第六行,第3个单元格");

        try {
            //导出
            workbook.write(new FileOutputStream(new File("R:\\testpoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
