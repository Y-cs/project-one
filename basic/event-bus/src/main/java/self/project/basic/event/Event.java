package self.project.basic.event;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-14:11
 * @description
 */
public class Event {

    private Class<?> clazz;
    private String methodName;

    private Object[] args;

    private Class<?>[] argsClass;

    public Event(Class<?> clazz, String methodName, Object[] args, Class<?>[] argsClass) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.args = args;
        this.argsClass = argsClass;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class<?>[] getArgsClass() {
        return argsClass;
    }
}
