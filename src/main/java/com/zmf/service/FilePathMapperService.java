package com.zmf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zmf
 * @description map the relative file path of the chapter that are
 * stored in the database to the absolute file path
 * @timestamp 2017-12-07 21:55
 */
@Component
public class FilePathMapperService {
    @Value("${www_service_root_path}")
    private String wwwRootPath;
    @Value("${server_ip}")
    private String server_ip;
    @Value("${server_port}")
    private String server_port;
    private String prefix;


    /**
     * map the relative file path of the chapter that are
     * stored in the database to the absolute file path
     *
     * @param filePath the file path like '/var/www/html/download/dbwork/...'
     * @return the http url
     */
    public String map(String filePath) {
        if (prefix == null) {
            System.out.println(111);
            prefix = "http://" + server_ip + ":" + server_port;
        }
        return prefix + filePath.replace(wwwRootPath, "");
    }
}
