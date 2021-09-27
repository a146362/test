package com.util;
import java.sql.ResultSet;

/**
 * @author Administrator
 * 处理结果集
 */
public interface ResultSetHanlder<T> {
    T resultSetToBean(ResultSet rs);
}
