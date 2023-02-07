package com.flipped.mall.auth.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间转换配置类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Configuration
public class DateConverterConfig {

    /**
     * 日期正则表达式
     */
    private static final String DATE_REGEX = "[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";

    /**
     * 时间正则表达式
     */
    private static final String TIME_REGEX = "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d";

    /**
     * 日期和时间正则表达式
     */
    private static final String DATE_TIME_REGEX = DATE_REGEX + "\\s" + TIME_REGEX;

    /**
     * 13位时间戳正则表达式
     */
    private static final String TIME_STAMP_REGEX = "1\\d{12}";

    /**
     * 年和月正则表达式
     */
    private static final String YEAR_MONTH_REGEX = "[1-9]\\d{3}-(0[1-9]|1[0-2])";

    /**
     * 年和月格式
     */
    private static final String YEAR_MONTH_PATTERN = "yyyy-MM";

    /**
     * 年月日 + 时间 格式
     */
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日格式
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式
     */
    private static final String TIME_PATTERN = "HH:mm:ss";

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    /**
     * Jackson序列化和反序列化转换器，用于转换Post请求体中的json以及将对象序列化为返回响应的json
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMATTER))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER))
                .serializerByType(Date.class, new DateSerializer(false, new SimpleDateFormat(DATETIME_PATTERN)))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER))
                .deserializerByType(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(DATETIME_PATTERN), DATETIME_PATTERN));
    }

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (StringUtils.isBlank(source)) {
                    return null;
                }
                return LocalDate.parse(source, DATE_FORMATTER);
            }

            @Override
            public JavaType getInputType(TypeFactory typeFactory) {
                return null;
            }

            @Override
            public JavaType getOutputType(TypeFactory typeFactory) {
                return null;
            }
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (StringUtils.isBlank(source)) {
                    return null;
                }
                return LocalDateTime.parse(source, DATETIME_FORMATTER);
            }

            @Override
            public JavaType getInputType(TypeFactory typeFactory) {
                return null;
            }

            @Override
            public JavaType getOutputType(TypeFactory typeFactory) {
                return null;
            }
        };
    }

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                if (StringUtils.isBlank(source)) {
                    return null;
                }
                return LocalTime.parse(source, TIME_FORMATTER);
            }

            @Override
            public JavaType getInputType(TypeFactory typeFactory) {
                return null;
            }

            @Override
            public JavaType getOutputType(TypeFactory typeFactory) {
                return null;
            }
        };
    }

    /**
     * Date转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {

            @Override
            public Date convert(String source) {
                if (StringUtils.isBlank(source)) {
                    return null;
                }
                if (source.matches(TIME_STAMP_REGEX)) {
                    return new Date(Long.parseLong(source));
                }
                DateFormat format;
                if (source.matches(DATE_TIME_REGEX)) {
                    format = new SimpleDateFormat(DATETIME_PATTERN);
                } else if (source.matches(DATE_REGEX)) {
                    format = new SimpleDateFormat(DATE_PATTERN);
                } else if (source.matches(YEAR_MONTH_REGEX)) {
                    format = new SimpleDateFormat(YEAR_MONTH_PATTERN);
                } else {
                    throw new IllegalArgumentException();
                }
                try {
                    return format.parse(source);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public JavaType getInputType(TypeFactory typeFactory) {
                return null;
            }

            @Override
            public JavaType getOutputType(TypeFactory typeFactory) {
                return null;
            }
        };
    }
}
