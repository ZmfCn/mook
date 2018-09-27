package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.KeywordDao;
import com.zmf.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 22:29
 */
@Component
public class KeywordService {
    @Autowired
    private KeywordDao keywordDao;

    private List<Keyword> keywords;


    public KeywordService() {
        keywords = new ArrayList<>();
        refreshKeywords();
    }


    private void refreshKeywords() {
        if (keywordDao != null) {
            keywords = keywordDao.getAllKeyWords();
            keywords = keywords == null ? new ArrayList<>() : keywords;
        }
    }

    /**
     * to judge whether the statement contains some words to be filtered
     *
     * @param statement the statement to be judged
     * @return true if the statement is to be filtered
     */
    public boolean isToBeFiltered(String statement) {
        int size = keywords.size();
        for (int i = 0; i < size; i++) {
            if (statement.contains(keywords.get(i).getContent())) {
                return true;
            }
        }
        return false;
    }


    public boolean addKeyword(String content, List<String> container, List<String> errorContainer) {
        if (Util.isEmpty(content)) {
            errorContainer.add("敏感词不能为空");
            return false;
        }
        if (keywordDao.getKeyWordByContent(content) != null) {
            errorContainer.add("敏感词已存在，无需再次添加");
            return false;
        } else {
            keywordDao.add(generateKeyword(content));
            container.add("添加成功");
            refreshKeywords();
            return true;
        }
    }

    public boolean deleteKeyword(String content, List<String> container, List<String> errorContainer) {
        if (Util.isEmpty(content)) {
            errorContainer.add("敏感词不能为空");
            return false;
        }
        if (keywordDao.getKeyWordByContent(content) == null) {
            errorContainer.add("敏感词不存在，无需删除");
            return false;
        } else {
            keywordDao.deleteKeyWordByContent(content);
            container.add("删除成功");
            refreshKeywords();
            return true;
        }
    }

    public List<Keyword> getKeywords() {
        if (keywords == null || keywords.size() == 0) {
            refreshKeywords();
        }
        return keywords;
    }

    public Keyword generateKeyword(String content) {
        Keyword keyword = new Keyword();
        keyword.setKeywordId(generateCourseId(System.currentTimeMillis()));
        keyword.setContent(content);
        return keyword;
    }

    public String generateCourseId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("keyword");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }
}
