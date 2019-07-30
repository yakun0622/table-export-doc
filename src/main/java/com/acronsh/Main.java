package com.acronsh;

import com.acronsh.entity.Table;
import com.acronsh.hanlder.TableHandler;
import com.acronsh.hanlder.WordHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        WordHandler wh = new WordHandler();
        List<Table> tables = null;//获取表结构数据
        try {
            tables = TableHandler.getTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("tables", tables);
        wh.data2word(data);
    }
}
