package com.util;
import java.sql.ResultSet;

/**
 * @author Administrator
 * ��������
 */
public interface ResultSetHanlder<T> {
    T resultSetToBean(ResultSet rs);
}
