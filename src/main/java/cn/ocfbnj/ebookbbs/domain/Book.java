package cn.ocfbnj.ebookbbs.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Integer ebook_id;
    private String title;
    private String author;
    private String intro;
    private Integer download;
    private BigDecimal price;
    private Integer userId;
    private Date issueTime;
    private String cover_path;
    private String classify;

    public Integer getEbook_id() {
        return ebook_id;
    }

    public void setEbook_id(Integer ebook_id) {
        this.ebook_id = ebook_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ebook_id=" + ebook_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", download=" + download +
                ", price=" + price +
                ", userId=" + userId +
                ", issueTime=" + issueTime +
                ", cover_path='" + cover_path + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
