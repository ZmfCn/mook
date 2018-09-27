package com.zmf.service;

import com.zmf.Utils.Util;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zmf
 * @description some methods of top type
 * @timestamp 2017-12-09 19:36
 */
@Transactional
@Component
public class TopTypeService {
    /**
     * generate the top type id according to the user email and current millisecond.
     *
     * @param currentMillis the current millisecond
     * @return the id
     */
    public String generateTopTypeId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("top");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }

}
