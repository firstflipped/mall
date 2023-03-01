package com.flipped.mall.common.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class JsonUtil {
    private static final String STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        //设置java.util.Date时间类的序列化以及反序列化的格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(STANDARD_PATTERN));

        // 初始化JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //处理LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(STANDARD_PATTERN);
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

        //处理LocalDate
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

        // 处理LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

        // 注册时间模块, 支持支持jsr310, 即新的时间类(java.time包下的时间类)
        OBJECT_MAPPER.registerModule(javaTimeModule);

        // 包含所有字段
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 在序列化一个空对象时时不抛出异常
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 忽略反序列化时在json字符串中存在, 但在java对象中不存在的属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * JavaBean转换为JSON字符串
     *
     * @param bean JavaBean对象
     * @return 泛型对象
     */
    public static <T> String bean2Json(T bean) {
        if (bean == null) {
            return null;
        }
        try {
            return bean instanceof String ? (String) bean : getInstance().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * JavaBean转换为JSON字符串并美化
     *
     * @param bean JavaBean对象
     * @return 泛型对象
     */
    public static <T> String bean2JsonPretty(T bean) {
        if (bean == null) {
            return null;
        }
        try {
            return bean instanceof String ? (String) bean : getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * JSON字符串转换为JavaBean
     *
     * @param json  json字符串
     * @param clazz JavaBean类型
     * @return 泛型对象
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        if (json == null || json.length() == 0 || "nil".equals(json) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) json : getInstance().readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * JSON字符串转换为JavaBean，支持转为集合类型
     * 示例: JsonUtil.json2Bean(json, new TypeReference<List<T>>() {})
     *
     * @param json          json字符串
     * @param typeReference JavaBean类型
     * @return 泛型对象
     */
    public static <T> T json2Bean(String json, TypeReference<T> typeReference) {
        if (json == null || json.length() == 0 || "nil".equals(json) || typeReference == null) {
            return null;
        }
        try {
            return typeReference.getType().equals(String.class) ? (T) json : getInstance().readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * JSON字符串转换为集合
     * 示例: JsonUtil.json2Bean(json, List.class, PermissionEntity.class)
     *
     * @param json            json字符串
     * @param collectionClazz 集合类型
     * @param elementClazz    内部引用类型
     * @return 泛型对象
     */
    public static <T> T json2BeanList(String json, Class<?> collectionClazz, Class<?>... elementClazz) {
        JavaType javaType = getInstance().getTypeFactory().constructParametricType(collectionClazz, elementClazz);
        try {
            return getInstance().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过反射将json字符串转换为复杂JavaBean
     *
     * @param json      json字符串
     * @param clazz     JavaBean类型
     * @param className 属性对应属性引用类型
     * @return 泛型对象
     */
    public static <T> T json2ComplicatedBean(String json, Class<T> clazz, String className) {
        try {
            JSONObject sourceJsonObject = JSONObject.parseObject(json);
            Class<?> result = Class.forName(className);

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 属性名
                String fieldName = field.getName();
                // 属性类型
                String fieldTypeName = field.getGenericType().getTypeName();

                // 集合类型
                if (fieldTypeName.equals("java.util.List")) {
                    JSONArray jsonArray = sourceJsonObject.getJSONArray(fieldName);
                    List<?> fieldValue = json2BeanList(jsonArray.toJSONString(), List.class);

                    Method setMethod = result.getDeclaredMethod(
                            "set" + getMethodName(field.getName()));
                    setMethod.invoke(result, fieldValue);// 调用setter方法获取属性值
                    continue;
                }


                Class<?> aClass = Class.forName(fieldTypeName);
                JSONObject jsonObject = sourceJsonObject.getJSONObject(fieldName);
                Object o = json2Bean(jsonObject.toJSONString(), aClass);

                Method setMethod = result.getDeclaredMethod("set" + getMethodName(field.getName()), aClass);
                setMethod.invoke(aClass, o);// 调用setter方法获取属性值

            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 亮点：模拟构造方法设计模式提供类似于阿里巴巴FastJSON的put方式构造JSON功能
     *
     * @return JsonBuilder
     */
    public static JsonBuilder builder() {
        return new JsonBuilder();
    }

    /**
     * 实体转Map
     *
     * @param bean 实体
     * @return Map
     */
    public static <T> Map<String, String> bean2Map(T bean) {
        Map<String, String> map = new HashMap<>(8);
        for (Field field : bean.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                String value = field.get(bean).toString();
                map.put(field.getName(), value);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        return map;
    }

    /**
     * Map转实体
     *
     * @param map   map
     * @param clazz JavaBean类型
     * @return 泛型对象
     */
    public static <T> T map2Bean(Map<String, String> map, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 驼峰转下划线
     *
     * @param map map
     * @return map
     */
    public static Map<String, String> humpToUnderline(Map<String, String> map) {
        Map<String, String> transitionMap = new HashMap<>(16);
        map.forEach((k, v) -> transitionMap.put(StrUtil.toUnderlineCase(k), v));
        return transitionMap;
    }

    public static class JsonBuilder {
        private final Map<String, Object> map = new HashMap<>();

        JsonBuilder() {
        }

        public JsonBuilder put(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public String build() {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this.map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
