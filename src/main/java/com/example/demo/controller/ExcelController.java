package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.demo.util.BigDecimalUtil;
import com.example.demo.vo.*;
import com.example.demo.service.ExcelService;
import com.example.demo.service.IUser;
import com.example.demo.util.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author hjl
 * @date 2019/3/14 16:45
 */
@RequestMapping("excel")
@RestController
@CrossOrigin(origins = "*")
public class ExcelController {

    private final static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;
    @Autowired
    private IUser iUser;

    @PostMapping("testExcel")
    public void testExcel() {
        LocalDateTime begin = LocalDateTime.now();
        String fileName = "D:/test4.xlsx";
        Map<Integer, String> discount = new HashMap<>();
        List<DiscountAdItemVo> priceDiscountItemInfo = new ArrayList<>();
        // 读文件
        AnalysisEventListener listener = new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                for (int i = 0; i < headMap.size(); i++) {
                    String value = headMap.get(i);
                    // excel的折扣名称，后面多带了“折扣”2个字
                    if (value.contains("折扣")) {
                        value = value.substring(0, value.length() - 2);
                    }
                    discount.put(i, value);
                }
            }

            @Override
            public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {
                DiscountAdItemVo discountAdItemVo = new DiscountAdItemVo();
                discountAdItemVo.setUid(Long.valueOf(o.get(1)));
                discountAdItemVo.setUsername(o.get(4));
                discountAdItemVo.setProvince(o.get(5));
                discountAdItemVo.setCity(o.get(6));
                discountAdItemVo.setCounty(o.get(7));
                List<PriceDiscountItemVo> priceDiscountItemVos = new ArrayList<>();
                // 遍历所有的列
                for (int i = 11; i < o.size(); i = i + 2) {
                    PriceDiscountItemVo priceDiscountItemVo = new PriceDiscountItemVo();
                    // 插入的时间再放入
                    //priceDiscountItemVo.setName(discount.get(i));
                    priceDiscountItemVo.setDiscount(BigDecimalUtil.mul(o.get(i), "1000").intValue());
                    //priceDiscountItemVo.setDiscountCategoryId(discountInfoIdMap.get(discount.get(i)));
                    priceDiscountItemVos.add(priceDiscountItemVo);
                }
                discountAdItemVo.setPriceDiscountItemInfo(priceDiscountItemVos);
                discountAdItemVo.setExcelNo(priceDiscountItemInfo.size() + 1);
                priceDiscountItemInfo.add(discountAdItemVo);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        };
        EasyExcel.read(fileName, listener).sheet().doRead();

        System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
        System.out.println(1);
    }

    @PostMapping("export")
    public String export(ExportInputVo vo) {
        excelService.export(vo);
        return "1";
    }

    @PostMapping("import")
    public void excelImport(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExcelReader excelReader = null;
        InputStream in = null;
        try {
            //AnalysisEventListener<ExcelTest> listener = ExcelUtils.getListener();
            in = serviceFile.getInputStream();
            excelReader = EasyExcel.read(in, ExcelTestVo.class,
                    new DemoDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
            logger.error("import excel to db fail", ex);
        } finally {
            in.close();
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    @PostMapping("importTest")
    public void importTest(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExcelReader excelReader = null;
        InputStream in = null;
        try {
            AnalysisEventListener<ExcelTest2> listener = ExcelUtils.getListener(this.batchInsert());
            in = serviceFile.getInputStream();
            excelReader = EasyExcel.read(in, ExcelTestVo.class, listener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
            logger.error("import excel to db fail", ex);
        } finally {
            in.close();
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    private Consumer<List<ExcelTest2>> batchInsert() {
        System.out.println("===begin===");
        return users -> {
            iUser.saveData2(users);
        };
    }


}
