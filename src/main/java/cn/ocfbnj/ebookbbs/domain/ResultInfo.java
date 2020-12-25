package cn.ocfbnj.ebookbbs.domain;

public class ResultInfo {
    /**
     * @param flag 后端返回结果正常为true，发生异常返回false
     * @param data 后端返回结果数据对象
     * @param errorMessage 发生异常的错误消息
     */
    private boolean flag;
    private Object data;
    private String errorMessage;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
