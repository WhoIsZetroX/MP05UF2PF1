package ex3;

public class Item {

    String key, value;

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Item() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
