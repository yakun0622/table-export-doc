package com.acronsh.hanlder;

import com.acronsh.entity.Column;
import com.acronsh.entity.Table;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableHandler {

    /**
     * @param args
     */
    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/hinew2017_pre";
    public static final String DBUSER = "root";
    public static final String DBPASS = "123456";

    public static final String DB_NAME = "db_name";

    public static List<Table> getTables() throws Exception {
        List<Table> tables = new ArrayList<Table>();
        Connection con = null; //表示数据库的连接对象
        Statement stmt = null;  //表示数据库的更新操作
        ResultSet result = null; //表示接收数据库的查询结果
        Class.forName(DBDRIVER); //1、使用CLASS 类加载驱动程序
        con = DriverManager.getConnection(DBURL, DBUSER, DBPASS); //2、连接数据库
        stmt = con.createStatement(); //3、Statement 接口需要通过Connection 接口进行实例化操作
        result = stmt.executeQuery("select table_name,table_comment from information_schema.tables where table_schema = '" + DB_NAME + "'"); //执行SQL 语句，查询数据库
        while (result.next()) {
            String name = result.getString(1);
            String comment = result.getString(2);
            Table table = new Table();
            table.setName(name);
            table.setComment(comment);
            tables.add(table);
        }
        for (Table table : tables) {
            List<Column> columns = new ArrayList<Column>();
            //查询字段
            result = stmt.executeQuery("select ORDINAL_POSITION,COLUMN_NAME,COLUMN_TYPE,IS_NULLABLE,COLUMN_COMMENT from information_schema.columns where table_schema ='" + DB_NAME + "' and table_name = '" + table.getName() + "' "); //执行SQL 语句，查询数据库
            while (result.next()) {
                int index = result.getInt(1);
                String name = result.getString(2);
                String type = result.getString(3);
                String isNull = result.getString(4);
                String comment = result.getString(5);
                Column column = new Column();
                column.setIndex(index);
                column.setName(name);
                column.setDataType(type);
                column.setIsNull(isNull);
                column.setComment(comment);
                columns.add(column);
            }
            table.setColumns(columns);
        }
        result.close();
        con.close(); // 4、关闭数据库
        return tables;
    }

}
