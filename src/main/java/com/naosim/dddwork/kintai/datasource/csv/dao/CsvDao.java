package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class CsvDao<T> {

    public void writeAll(Writer writer, List<T> beans) throws CsvException {
        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
        beanToCsv.write(beans);
    }

    public void write(Writer writer, T bean) throws CsvException {
        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
        beanToCsv.write(bean);
    }

    public List<T> read(Reader reader, Class<? extends T> type) throws CsvException {
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType(type).build();
        return csvToBean.parse();
    }

}
