package hexlet.code;

public final class DiffNode {
    private final String key;
    private final Status status;
    private final Object value1;
    private final Object value2;

    private DiffNode(String key, Status status, Object value1, Object value2) {
        this.key = key;
        this.status = status;
        this.value1 = value1;
        this.value2 = value2;
    }

    public static DiffNode unchanged(String key, Object value) {
        return new DiffNode(key, Status.UNCHANGED, value, null);
    }

    public static DiffNode added(String key, Object value) {
        return new DiffNode(key, Status.ADDED, null, value);
    }

    public static DiffNode removed(String key, Object value) {
        return new DiffNode(key, Status.REMOVED, value, null);
    }

    public static DiffNode changed(String key, Object value1, Object value2) {
        return new DiffNode(key, Status.CHANGED, value1, value2);
    }

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    public enum Status {
        ADDED,
        REMOVED,
        CHANGED,
        UNCHANGED
    }
}
