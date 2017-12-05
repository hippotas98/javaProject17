package w5;
import java.time.*;
class HoaQua{
    String gia;
    String nguongoc;
    //String ngaynhap;
    LocalDate ngaynhap;
    int soluong;
    /**
     * @return the gia
     */
    public String getGia() {
        return gia;
    }
    /**
     * @param gia the gia to set
     */
    public void setGia(String gia) {
        this.gia = gia;
    }
    /**
     * @return the nguongoc
     */
    public String getNguongoc() {
        return nguongoc;
    }
    /**
     * @param nguongoc the nguongoc to set
     */
    public void setNguongoc(String nguongoc) {
        this.nguongoc = nguongoc;
    }
    /**
     * @return the soluong
     */
    public int getSoluong() {
        return soluong;
    }
    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    /**
     * @return the ngaynhap
     */
    public LocalDate getNgaynhap() {
        return ngaynhap;
    }
    /**
     * @param ngaynhap the ngaynhap to set
     */
    public void setNgaynhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }
}
class QuaTao extends HoaQua
{
    String mausac = "mau xanh";
    @Override
    public String getGia() {
        return super.getGia();
    }
    @Override
    public void setGia(String gia) {
        super.setGia(gia);
    }
    //public QuaTao()
}
class QuaCam extends HoaQua
{
    String mausac = "mau cam";
    String muivi;
    /**
     * @return the muivi
     */
    public String getMuivi() {
        return muivi;
    }
    /**
     * @param muivi the muivi to set
     */
    public void setMuivi(String muivi) {
        this.muivi = muivi;
    }
    /**
     * @return the mausac
     */
    public String getMausac() {
        return mausac;
    }
    /**
     * @param mausac the mausac to set
     */
    public void setMausac(String mausac) {
        this.mausac = mausac;
    }   
}

class CamCaoPhong extends QuaCam
{
    @Override
    public String getNguongoc() {
        return super.getNguongoc();
    }
    @Override
    public void setNguongoc() {
        super.setNguongoc("Cao Phong");
    }
}
class CamSanh extends QuaCam
{
    @Override
    public String getNguongoc() {
        //super.setNguongoc("Ha Giang");
        return super.getNguongoc();
    }
    @Override
    public void setNguongoc() {
        super.setNguongoc("Ha Giang");
    }
}
public class problem1 
{
    public static void main (String [] args)
    {
        HoaQua f1 = new HoaQua();
        f1.setGia("100000");
        f1.setNgaynhap(new LocalDateTimeStringConverter("10/12/2017"));
        f1.setSoluong(10);
        f1.setNguongoc("Ha Noi");
        
    }
}