package com.zmf.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * filter some keywords
 */
@Component
public class KeywordFilterService {
    private List<String> keywordString;

    public KeywordFilterService() {
        keywordString = new ArrayList<>();
        resolveKeyWord();
    }

    /**
     * to judge whether the statement contains some words to be filtered
     *
     * @param statement the statement to be judged
     * @return true if the statement is invalid and is to be filtered
     */
    public boolean isToBeFiltered(String statement) {
        if (keywordString == null) {
            return false;
        }
        for (int i = 0; i < keywordString.size(); i++) {
            if (statement.contains(keywordString.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * resolve the keyword configuration xml and initialize the array.
     */
    @Test
    public void resolveKeyWord() {
        SAXReader saxReader = new SAXReader();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = null;
        try {
            file = new File(classLoader.getResource("config/keywordFilterList.xml").getFile());
        } catch (NullPointerException e) {
            return;
        }
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> keywords = root.elements("keyword");
        int length = keywords.size();
        for (int i = 0; i < length; i++) {
            keywordString.add(keywords.get(i).getText());
        }
    }

    @Test
    public void addKeyWord() {
        SAXReader saxReader = new SAXReader();
        ClassLoader classLoader = getClass().getClassLoader();
        File file;
        try {
            file = new File(classLoader.getResource("config/keywordFilterList.xml").getFile());
        } catch (NullPointerException e) {
            return;
        }
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element root = document.getRootElement();
        List<Element> keywords = root.elements("keyword");
        int length = keywords.size();
        for (int i = 0; i < length; i++) {
            keywordString.add(keywords.get(i).getText());
        }
        Element newe = root.addElement("keyword");
        newe.setParent(root);
        newe.setText("ssssss");
        try {
            saveDocument(document, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveDocument(Document document, File xmlFile) throws IOException {
        Writer osWrite = new OutputStreamWriter(new FileOutputStream(xmlFile));//创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式
        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite, format);//XMLWriter 指定输出文件以及格式
        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }

    public List<String> getKeywordString() {
        return keywordString;
    }
}

