package cn.ocfbnj.ebookbbs.domain;

public class CheckCode {
    private String value;

    @Override
    public String toString() {
        return "CheckCode{" +
                "value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
