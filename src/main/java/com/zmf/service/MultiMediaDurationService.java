package com.zmf.service;

import com.zmf.annotations.Tested;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author zmf
 * @description the service to get the duration information of the multimedia
 * @timestamp 2017-12-08 23:11
 */
@Component
@Tested
@SuppressWarnings("all")
public class MultiMediaDurationService {

    /**
     * get the duration information of the multimedia
     *
     * @param filePath the file path of the multimedia
     * @return the duration
     * @throws EncoderException if the file is not supported
     */
    public String getDuration(String filePath) throws EncoderException {
        return getDuration(new File(filePath));
    }

    /**
     * get the duration information of the multimedia
     *
     * @param file the multimedia
     * @return the duration
     * @throws EncoderException if the file is not supported
     */
    public String getDuration(File file) throws EncoderException {
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(file);
        long ls = m.getDuration() / 1000;
        return convertSecondToHMS(ls);
    }


    /**
     * convert the number of seconds to the normal duration format
     *
     * @param seconds the seconds
     * @return the normal format
     */
    private String convertSecondToHMS(long seconds) {
        long second, hour, minute = 0;
        second = seconds % 60;
        minute = seconds / 60;
        hour = minute / 60;
        minute = minute % 60;
        StringBuilder builder = new StringBuilder();
        if (hour != 0) {
            builder.append(convertNumberToDoubleBit(hour)).append(":");
        }
        builder.append(convertNumberToDoubleBit(minute)).append(":");
        builder.append(convertNumberToDoubleBit(second));
        return builder.toString();
    }

    /**
     * convert the number to the double bit
     *
     * @param number the number
     * @return the result
     */
    private String convertNumberToDoubleBit(long number) {
        return number < 10 ? "0" + number : "" + number;
    }
}
