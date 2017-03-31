package track.container;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import track.container.config.Bean;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {
    private List<Bean> beans;
    private Map<String, Object> objById;
    private Map<String, Object> objByClassName;

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) {
        this.beans = beans;
        objById = new HashMap<>();
        objByClassName = new HashMap<>();
    }

    /**
     * Вернуть объект по имени бина из конфига
     * Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) throws ReflectiveOperationException {
        if (!objById.containsKey(id)) {
            for (Bean bean : beans) {
                if (bean.getId().equals(id)) {
                    return createInstance(bean);
                }
            }
        } else {
            return objById.get(id);
        }
        return null;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) throws ReflectiveOperationException {
        if (!objByClassName.containsKey(className)) {
            for (Bean bean : beans) {
                if (bean.getClassName().equals(className)) {
                    return createInstance(bean);
                }
            }
        } else {
            return objByClassName.get(className);
        }
        return null;
    }

    private Object createInstance(Bean bean) throws ReflectiveOperationException {
        Class<?> clazz = Class.forName(bean.getClassName());
        Object instance = clazz.newInstance();
        for (Map.Entry<String, Property> entry : bean.getProperties().entrySet()) {
            Class<?> valueType;
            Object value;
            if (entry.getValue().getType().equals(ValueType.REF)) {
                value = getById(entry.getValue().getValue());
                valueType = value.getClass();
            } else {
                valueType = clazz.getDeclaredField(entry.getValue().getName()).getType();
                value = parseValueTo(entry.getValue().getValue(), valueType);
            }
            Method setter = clazz.getDeclaredMethod("set" + entry.getKey().substring(0, 1).toUpperCase() +
                    entry.getKey().substring(1), valueType);
            setter.invoke(instance, value);
        }
        objById.put(bean.getId(), instance);
        objById.put(bean.getClassName(), instance);
        return instance;
    }

    private Object parseValueTo(String value, Class<?> valueType) {
        if (valueType == Boolean.TYPE) {
            return Boolean.parseBoolean(value);
        } else if (valueType == Byte.TYPE) {
            return Byte.parseByte(value);
        } else if (valueType == Short.TYPE) {
            return Short.parseShort(value);
        } else if (valueType == Integer.TYPE) {
            return Integer.parseInt(value);
        } else if (valueType == Long.TYPE) {
            return Long.parseLong(value);
        } else if (valueType == Double.TYPE) {
            return Double.parseDouble(value);
        } else if (valueType.equals(String.class)) {
            return String.valueOf(value);
        }

        return value;
    }
}
