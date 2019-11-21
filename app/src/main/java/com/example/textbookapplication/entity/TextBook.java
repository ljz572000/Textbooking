package com.example.textbookapplication.entity;

public class TextBook {
    public TextBook(Integer bookNo, String bookName, String bookPic, String author, Double bookPrice, Integer totalnum, String brief) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.bookPic = bookPic;
        this.author = author;
        this.bookPrice = bookPrice;
        this.totalnum = totalnum;
        this.brief = brief;
    }

    public Integer getBookNo() {
        return bookNo;
    }

    public void setBookNo(Integer bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "{" +
                "bookNo=" + bookNo +
                ", bookName='" + bookName + '\'' +
                ", bookPic='" + bookPic + '\'' +
                ", author='" + author + '\'' +
                ", bookPrice=" + bookPrice +
                ", totalnum=" + totalnum +
                ", brief='" + brief + '\'' +
                '}';
    }

    private Integer bookNo;
    private String bookName;
    private String bookPic;
    private String author;
    private Double bookPrice;
    private Integer totalnum;
    private String brief;
}
