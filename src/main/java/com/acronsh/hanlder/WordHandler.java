package com.acronsh.hanlder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class WordHandler {
    private Configuration configuration = null;

    public WordHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public void data2word(Map<String, Object> data) {
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
        // 这里我们的模板是放在org.cnzjw.template包下面
        configuration.setClassForTemplateLoading(this.getClass(),
                "/");
        Template t = null;
        try {
            // word.ftl为要装载的模板
            t = configuration.getTemplate("table.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File("/Users/wangyakun/Desktop/table.doc");
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(data, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
