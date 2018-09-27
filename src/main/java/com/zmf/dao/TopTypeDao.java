package com.zmf.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zmf.mapper.TopTypeRowMapper;
import com.zmf.pojo.TopType;
import com.zmf.service.TopTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.util.List;

@Component
public class TopTypeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TopTypeRowMapper topTypeRowMapper;

    @Test
    public void test() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            TopTypeService service = new TopTypeService();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ICourse?characterEncoding=utf8&useSSL=false");
            dataSource.setUser("zmf");
            dataSource.setPassword("123456sql");
            jdbcTemplate = new JdbcTemplate(dataSource);

            TopType topType1 = new TopType();
//            String[] ss = new String[]{
//                    "前端开发", "后端开发", "移动开发", "数据库", "云计算&大数据", "运维&测试", "UI设计"
//            };
//            for (int i = 0; i < ss.length; i++) {
//                topType1.setTypeName(ss[i]);
//                topType1.setTypeId(service.generateTopTypeId(System.currentTimeMillis()));
//                addTopType(topType1);
//            }
            topType1.setTypeName("项目管理");
            topType1.setTypeId(service.generateTopTypeId(System.currentTimeMillis()));
            addTopType(topType1);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    //C
    public void addTopType(TopType topType) {
        String sql = "INSERT INTO `top_type`\n" +
                "(`type_id`,\n" +
                "`type_name`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?);\n";
        jdbcTemplate.update(sql,
                topType.getTypeId(),
                topType.getTypeName()
        );
    }

    //R
    public TopType getTopTypeById(String topTypeId) {
        String sql = "SELECT * FROM top_type WHERE type_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, topTypeRowMapper, topTypeId);
        } catch (Exception e) {
            return null;
        }
    }

    public TopType getTopTypeByTypeName(String typeName) {
        String sql = "SELECT * FROM top_type WHERE type_name=?";
        try {
            return jdbcTemplate.queryForObject(sql, topTypeRowMapper, typeName);
        } catch (Exception e) {
            return null;
        }
    }

    //U
    public void updateTopTypeById(String topTypeId, TopType topType) {
        String sql = "UPDATE `top_type`\n" +
                "SET\n" +
                "`type_id` = ?,\n" +
                "`type_name` = ?\n" +
                "WHERE `type_id` = ?;\n";
        jdbcTemplate.update(sql,
                topType.getTypeId(),
                topType.getTypeName(),
                topTypeId);
    }

    //D
    public void deleteTopType(String topTypeId) {
        String sql = "DELETE FROM top_type WHERE type_id=?";
        jdbcTemplate.update(sql, topTypeId);
    }


    public List<TopType> getAllTopTypes() {
        String sql = "SELECT * FROM top_type";
        return jdbcTemplate.query(sql, topTypeRowMapper);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}