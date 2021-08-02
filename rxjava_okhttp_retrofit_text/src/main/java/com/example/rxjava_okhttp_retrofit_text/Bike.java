package com.example.rxjava_okhttp_retrofit_text;

public class Bike {
    private String sno;
    private String sna;
    private String tot;
    private String sbi;
    private String sarea;
    private String mday;
    private String ar;

    public Bike(String sno, String sna, String tot, String sbi, String sarea, String mday, String ar) {
        this.sno = sno;
        this.sna = sna;
        this.tot = tot;
        this.sbi = sbi;
        this.sarea = sarea;
        this.mday = mday;
        this.ar = ar;
    }

    public String getSno() {
        return sno;
    }

    public String getSna() {
        return sna;
    }

    public String getTot() {
        return tot;
    }

    public String getSbi() {
        return sbi;
    }

    public String getSarea() {
        return sarea;
    }

    public String getMday() {
        return mday;
    }

    public String getAr() {
        return ar;
    }
}
