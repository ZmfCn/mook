package com.zmf.dao;

import com.zmf.mapper.KeywordRowMapper;
import com.zmf.pojo.Keyword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 22:21
 */
@Component
public class KeywordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KeywordRowMapper keywordRowMapper;
    private Logger logger = Logger.getLogger(KeywordDao.class);

    /**
     * add keyword
     *
     * @param keyword the keyword
     */
    public void add(Keyword keyword) {
        logger.info("keywords: add keyword... content: " + keyword.getContent());
        String sql = "INSERT INTO keyword(keyword_id,content) VALUES (?,?)";
        jdbcTemplate.update(sql, keyword.getKeywordId(), keyword.getContent());
    }

    /**
     * delete key word by id
     *
     * @param keywordId the keyword if
     */
    public void deleteKeyWordById(String keywordId) {
        logger.info("keywords: delete key word by id... id: " + keywordId);
        String sql = "DELETE FROM keyword WHERE keyword_id = ?";
        jdbcTemplate.update(sql, keywordId);
    }

    /**
     * delete key word by content
     *
     * @param content the content
     */
    public void deleteKeyWordByContent(String content) {
        logger.info("keywords: delete key word by content... content: " + content);
        String sql = "DELETE FROM keyword WHERE content = ?";
        jdbcTemplate.update(sql, content);
    }


    /**
     * get keyword by id
     *
     * @param keywordId the id
     * @return the record
     */
    public Keyword getKeyWordById(String keywordId) {
        logger.info("keywords: get keyword by id... id " + keywordId);
        String sql = "SELECT * FROM keyword WHERE keyword_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, keywordRowMapper, keywordId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get keyword by content
     *
     * @param content content
     * @return record
     */
    public Keyword getKeyWordByContent(String content) {
        logger.info("keywords: get keyword by content... content: " + content);
        String sql = "SELECT * FROM keyword WHERE content = ?";
        try {
            return jdbcTemplate.queryForObject(sql, keywordRowMapper, content);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get all keywords
     *
     * @return list
     */
    public List<Keyword> getAllKeyWords() {
        logger.info("keywords: get all keywords...");
        String sql = "SELECT * FROM keyword";
        try {
            return jdbcTemplate.query(sql, keywordRowMapper);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
