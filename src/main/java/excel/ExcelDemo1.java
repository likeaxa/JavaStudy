package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by xinjian.yao
 * @date 2019/11/6 21:20
 */
public class ExcelDemo1 {

    public static class HeleDemo {
        String key;
        Integer value;


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }


    public static class excelHelp {
        String station;
        String year;
        // 早稻4个等级
        Integer pre1;
        Integer pre2;
        Integer pre3;
        Integer pre4;
        // 晚稻4个等级
        Integer out1;
        Integer out2;
        Integer out3;
        Integer out4;

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public Integer getPre1() {
            return pre1;
        }

        public void setPre1(Integer pre1) {
            this.pre1 = pre1;
        }

        public Integer getPre2() {
            return pre2;
        }

        public void setPre2(Integer pre2) {
            this.pre2 = pre2;
        }

        public Integer getPre3() {
            return pre3;
        }

        public void setPre3(Integer pre3) {
            this.pre3 = pre3;
        }

        public Integer getPre4() {
            return pre4;
        }

        public void setPre4(Integer pre4) {
            this.pre4 = pre4;
        }

        public Integer getOut1() {
            return out1;
        }

        public void setOut1(Integer out1) {
            this.out1 = out1;
        }

        public Integer getOut2() {
            return out2;
        }

        public void setOut2(Integer out2) {
            this.out2 = out2;
        }

        public Integer getOut3() {
            return out3;
        }

        public void setOut3(Integer out3) {
            this.out3 = out3;
        }

        public Integer getOut4() {
            return out4;
        }

        public void setOut4(Integer out4) {
            this.out4 = out4;
        }
    }

    public static void main(String[] args) throws Exception {


        String resultFile = "C:\\Users\\yaoxi\\Desktop\\file1.xls";
        File file1 = new File(resultFile);
        //文件不存在则创建文件，先创建目录
        if (!file1.exists()) {
            File dir = new File(file1.getParent());
            dir.mkdirs();
            file1.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file1);

        // excel 地址
        String filePath = "C:\\Users\\yaoxi\\Desktop\\压缩包\\dailydata_seperate_station_s2\\dailydata_seperate_station_s2";
        File file = new File(filePath);
        File[] files = file.listFiles();
        /**
         * 这个outputstream可以来自与文件的输出流，
         * 也可以直接输出到response的getOutputStream()里面
         * 然后用户就可以直接解析到你生产的excel文件了
         */
        Workbook wb = new SXSSFWorkbook(100);
        //创建一个工作本
        Sheet sheet = wb.createSheet("sheet");
        headerSet(sheet);
        Integer rowIndex = 1;
        List<File> fileList = Optional.ofNullable(files)
                .map(Arrays::stream)
                .orElseThrow(() -> new RuntimeException("文件夹下文件问空"))
                .collect(Collectors.toList());
        for (File fs : fileList) {
            // 获取文件名字
            String name = fs.getName();
            if (!name.contains(".xls")) {
                // txt 文件过滤
                continue;
            }
            // 获取站点名字
            String station = name.split("_")[2];
            String pre = "站点名称↓↓↓↓↓↓" + station + "↓↓↓↓↓↓\n";
            String end = "站点名称↑↑↑↑↑↑" + station + "↑↑↑↑↑↑\n";
            //
            try {

                List<excelHelp> excelHelps = dealWithExcelFile(fs, fos);
                writeExcel(fos, excelHelps, wb, sheet, rowIndex);
                rowIndex = rowIndex + excelHelps.size();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        wb.write(fos);
        wb.close();
        fos.close();

    }

    private static void headerSet(Sheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("站点");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("年份");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("早稻>=10<=20" );
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("早稻>=20<=30" );
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("早稻>=30<=40");
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("早稻>=40" );
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("晚稻>=10<=20" );
        Cell cell7 = row.createCell(7);
        cell7.setCellValue("晚稻>=20<=30" );
        Cell cell8 = row.createCell(8);
        cell8.setCellValue("晚稻>=30<=40");
        Cell cell9 = row.createCell(9);
        cell9.setCellValue("晚稻>=40" );
    }

    public static void writeExcel(FileOutputStream outputStream, List<excelHelp> excelHelps,
                                  Workbook wb, Sheet sheet, Integer rowIndex) {

        for (int i = 0; i < excelHelps.size(); i++, rowIndex++) {
            //通过一个sheet创建一个Row
            Row row = sheet.createRow(rowIndex);
            excelHelp excelHelp = excelHelps.get(i);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(excelHelp.getStation());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(excelHelp.getYear());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue( Optional.ofNullable(excelHelp.getPre1())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell3 = row.createCell(3);
            cell3.setCellValue( Optional.ofNullable(excelHelp.getPre2())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell4 = row.createCell(4);
            cell4.setCellValue( Optional.ofNullable(excelHelp.getPre3())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell5 = row.createCell(5);
            cell5.setCellValue( Optional.ofNullable(excelHelp.getPre4())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell6 = row.createCell(6);
            cell6.setCellValue( Optional.ofNullable(excelHelp.getOut1())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell7 = row.createCell(7);
            cell7.setCellValue( Optional.ofNullable(excelHelp.getOut2())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell8 = row.createCell(8);
            cell8.setCellValue(Optional.ofNullable(excelHelp.getOut3())
                    .map(Object::toString)
                    .orElse("0"));
            Cell cell9 = row.createCell(9);
            cell9.setCellValue(Optional.ofNullable(excelHelp.getOut4())
                    .map(Object::toString)
                    .orElse("0"));

        }



    }

    private static List<excelHelp> dealWithExcelFile(File file, FileOutputStream fos) throws Exception {

        List<excelHelp> resultList = new ArrayList<>();
        // excel 文件流
        FileInputStream excelStream = new FileInputStream(file);
        // 打开excel文件
        Workbook wb = new HSSFWorkbook(excelStream);
        // 读第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 第一行到最后一行
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        int flag = 0;
        // 遍历所有行
        int resultNumber = 0;
        for (int currentRow = firstRowNum; currentRow <= lastRowNum; currentRow++) {
            // 或者当前行
            Row row = sheet.getRow(currentRow);
            // 获取当前行的最后一列
            short lastCellNum = row.getLastCellNum();
            // 获取站点
            String station = row.getCell(0).toString();
            //获取年份
            String year = row.getCell(1).toString();
            // 获取月份
            String stringCellValue = row.getCell(2).toString();
            Double match = Double.valueOf(stringCellValue);
            //获取最后一列的值转行成浮点类型 平均降水量
            lastCellNum--;
            Double numericCellValue = Double.valueOf(row.getCell(lastCellNum).toString());
            // 早稻
            if (match >= 3.1 && match <= 7.15) {
                excelHelp excelOneRow = findExcelOneRow(resultList, station, year);
                if (numericCellValue >= 10 && numericCellValue <= 20) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setPre1(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getPre1())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setPre1(integer);
                    }
                } else if (numericCellValue > 20 && numericCellValue <= 30) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setPre2(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getPre2())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setPre2(integer);
                    }

                } else if (numericCellValue > 30 && numericCellValue <= 40) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setPre3(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getPre3())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setPre3(integer);
                    }
                } else if (numericCellValue > 40) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setPre4(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getPre4())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setPre4(integer);
                    }
                } else {
                    continue;
                }
            }
            // 晚稻
            if (match > 7.15 && match <= 11.20) {
                excelHelp excelOneRow = findExcelOneRow(resultList, station, year);

                if (numericCellValue >= 10 && numericCellValue <= 20) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setOut1(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getOut1())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setOut1(integer);
                    }
                } else if (numericCellValue > 20 && numericCellValue <= 30) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setOut2(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getOut2())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setOut2(integer);
                    }

                } else if (numericCellValue > 30 && numericCellValue <= 40) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setOut3(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getOut3())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setOut3(integer);
                    }
                } else if (numericCellValue > 40) {
                    if (excelOneRow == null) {
                        excelHelp temp = new excelHelp();
                        temp.setStation(station);
                        temp.setYear(year);
                        temp.setOut4(1);
                        resultList.add(temp);
                    } else {
                        Integer integer = Optional.ofNullable(excelOneRow.getOut4())
                                .map(val -> val + 1)
                                .orElse(1);
                        excelOneRow.setOut4(integer);
                    }
                }
            }


        }

        return resultList;

    }

    private static excelHelp findExcelOneRow(List<excelHelp> resultList, String station, String year) {
        return resultList.stream()
                .filter(val -> val.getStation().equals(station) && val.getYear().equals(year))
                .findFirst()
                .orElse(null);
    }

    private static Integer dealWithExcelFileSave(File file, FileOutputStream fos) throws Exception {
        List<HeleDemo> result = new ArrayList<>();
        // excel 文件流
        FileInputStream excelStream = new FileInputStream(file);
        // 打开excel文件
        Workbook wb = new HSSFWorkbook(excelStream);
        // 读第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 第一行到最后一行
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        int flag = 0;
        // 遍历所有行
        int resultNumber = 0;
        for (int currentRow = firstRowNum; currentRow <= lastRowNum; currentRow++) {
            // 或者当前行
            Row row = sheet.getRow(currentRow);
            // 获取当前行的最后一列
            short lastCellNum = row.getLastCellNum();
            lastCellNum--;
            //获取最后一列的值转行成浮点类型
            Double numericCellValue = Double.valueOf(row.getCell(lastCellNum).toString());
            if (numericCellValue >= 20.0) {
                // 如果大于20mm 判断当前行数 和上一次超过20mm的行数是否大于20
                int datNumber = currentRow - flag - 1;
                if (datNumber >= 20.0) {
                    resultNumber++;
                    // 超过 输出上一次行数的值和当前行的值
                    // 获取上一次的年份
                    String oldYear = flag == 0 ? "1960" : sheet.getRow(flag).getCell(1).toString();
                    // 获取上一次的月份天数
                    String oldDay = flag == 0 ? "12.31" : sheet.getRow(flag).getCell(2).toString();
                    // 获取当前年份
                    String newYear = sheet.getRow(currentRow).getCell(1).toString();
                    // 获取当前月份天数
                    String newDay = sheet.getRow(currentRow).getCell(2).toString();
                    //  System.out.println(oldYear+"-"+oldDay +"--->"+newYear+"-"+newDay +"-->"+datNumber+"天下雨量小于20");
                    // 更新当前行数
                    flag = currentRow;
                    HeleDemo temp = new HeleDemo();
                    temp.setKey(oldYear + "-" + newYear);
                    temp.setValue(datNumber);
                    result.add(temp);
                }
            }
        }
        return resultNumber;
        // 分组统计 汇总输出
//        Map<String, Long> collect = result.stream().collect(Collectors.groupingBy(HeleDemo::getKey, LinkedHashMap::new, Collectors.counting()));
//        collect.forEach((k,v)->{
//            String body = k+"-->"+v+"\n";
//            try {
//                fos.write(body.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
