package com.rule.engine.api.enums;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:27 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum IndicatorValueTypeEnum {
    TYPE_LONG(1, "long", Long.class.getCanonicalName()) {
        @Override
        protected Long parseObj(Object value) {
            return Long.parseLong(value.toString());
        }
    },
    TYPE_INTEGER(2, "integer", Integer.class.getCanonicalName()) {
        @Override
        protected Integer parseObj(Object value) {
            return Integer.parseInt(value.toString());
        }
    },

    TYPE_BYTE(4, "byte", Byte.class.getCanonicalName()) {
        @Override
        protected Byte parseObj(Object value) {
            return Byte.parseByte(value.toString());
        }
    },

    TYPE_BOOLEAN(8, "boolean", Boolean.class.getCanonicalName()) {
        @Override
        protected Boolean parseObj(Object value) {
            return Boolean.valueOf(value.toString());
        }
    },

    TYPE_BIG_DECIMAL(16, "big-decimal", BigDecimal.class.getCanonicalName()) {
        @Override
        protected BigDecimal parseObj(Object value) {
            return new BigDecimal(value.toString());
        }
    },

    TYPE_FLOAT(32, "float", Float.class.getCanonicalName()) {
        @Override
        protected Float parseObj(Object value) {
            return Float.valueOf(value.toString());
        }
    },

    TYPE_DOUBLE(64, "double", Double.class.getCanonicalName()) {
        @Override
        protected Double parseObj(Object value) {
            return Double.valueOf(value.toString());
        }
    },

    TYPE_TEXT(128, "text", String.class.getCanonicalName()) {
        @Override
        protected Object parseObj(Object value) {
            return value.toString();
        }
    },

    TYPE_LIST(256, "list", List.class.getCanonicalName()) {
        @Override
        protected List<Object> parseObj(Object value) {
            return JSON.parseArray(JSON.toJSONString(value), Object.class);
        }
    },

    TYPE_TIMESTAMP(518, "timestamp", Long.class.getCanonicalName()) {
        @Override
        protected Long parseObj(Object value) {
            return Long.valueOf(value.toString());
        }
    },

    TYPE_OBJECT(1024, "object", Object.class.getCanonicalName()) {
        @Override
        protected Object parseObj(Object value) {
            return value;
        }
    },

    DATE(2048, "date", Date.class.getCanonicalName()) {
        @Override
        protected Date parseObj(Object value) {
            String text = value.toString();
            if (value instanceof Number) {
                return new Date(Long.parseLong(text));
            }
            if (StringUtils.isBlank(text)) {
                return null;
            } else {
                SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    return frm.parse(text);
                } catch (ParseException var4) {
                    return null;
                }
            }
        }
    },
    ;

    private int code;
    private String msg;
    private String javaTypeDesc;

    private static final Map<Integer, IndicatorValueTypeEnum> TYPE_MAP;

    static {
        Map<Integer, IndicatorValueTypeEnum> map = new HashMap<>();
        for (IndicatorValueTypeEnum bizTypeEnum : IndicatorValueTypeEnum.values()) {
            map.put(bizTypeEnum.getCode(), bizTypeEnum);
        }
        TYPE_MAP = Collections.unmodifiableMap(map);
    }

    private Object parse(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return parseObj(value);
        } catch (Exception e) {
            return null;
        }
    }

    protected abstract Object parseObj(Object value);

    public static IndicatorValueTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        return TYPE_MAP.get(code);
    }

    public static Object parseVal(Integer code, Object val) {
        IndicatorValueTypeEnum typeEnum = getByCode(code);
        if (typeEnum == null) {
            return null;
        }
        return typeEnum.parse(val);
    }
}
