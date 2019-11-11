package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by xinjian.yao
 * @date 2019/11/6 21:20
 */
public class ExcelDemo1 {

    public static  class  HeleDemo {
        String key ;
        Integer value ;

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
    public static void main(String[] args) throws Exception{



        String resultFile = "C:\\Users\\yaoxi\\Desktop\\file1.txt";
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
        Optional.ofNullable(files)
                .map(Arrays::stream)
                .orElseThrow(()->new RuntimeException("文件夹下文件问空"))
                .forEach(fs->{
                    // 获取文件名字
                    String name = fs.getName();
                    if(!name.contains(".xls")){
                        // txt 文件过滤
                        return;
                    }
                    // 获取站点名字
                    String station = name.split("_")[2];
                    String pre = "站点名称↓↓↓↓↓↓" + station + "↓↓↓↓↓↓\n";
                    String end = "站点名称↑↑↑↑↑↑" + station + "↑↑↑↑↑↑\n";
                    //
                    try {

                        Integer stationNum = dealWithExcelFile(fs, fos);
                        String wirle = name+"--->"+stationNum+"\n";
                        fos.write(wirle.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                });
        fos.close();
    }
    private static Integer dealWithExcelFile(File file,FileOutputStream fos) throws Exception{
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
        int flag = 0 ;
        // 遍历所有行
        int resultNumber = 0;
        for(int currentRow = firstRowNum ; currentRow<=lastRowNum;currentRow++){
            // 或者当前行
            Row row = sheet.getRow(currentRow);
            // 获取当前行的最后一列
            short lastCellNum = row.getLastCellNum()  ;
            lastCellNum -- ;
            //获取最后一列的值转行成浮点类型
            Double numericCellValue = Double.valueOf(row.getCell(lastCellNum).toString());
            if(numericCellValue>=20.0){
                // 如果大于20mm 判断当前行数 和上一次超过20mm的行数是否大于20
                int datNumber = currentRow - flag -1;
                if(datNumber >=20.0){
                    resultNumber++;
                    // 超过 输出上一次行数的值和当前行的值
                    // 获取上一次的年份
                    String oldYear = flag == 0 ? "1960": sheet.getRow(flag).getCell(1).toString();
                    // 获取上一次的月份天数
                    String oldDay = flag == 0 ? "12.31": sheet.getRow(flag).getCell(2).toString();
                    // 获取当前年份
                    String newYear = sheet.getRow(currentRow).getCell(1).toString();
                    // 获取当前月份天数
                    String newDay = sheet.getRow(currentRow).getCell(2).toString();
                    //  System.out.println(oldYear+"-"+oldDay +"--->"+newYear+"-"+newDay +"-->"+datNumber+"天下雨量小于20");
                    // 更新当前行数
                    flag = currentRow;
                    HeleDemo temp = new HeleDemo();
                    temp.setKey(oldYear+"-"+newYear);
                    temp.setValue(datNumber);
                    result.add(temp);
                }
            }
        }
        return  resultNumber;
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
