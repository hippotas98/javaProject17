package w5;
public class p1
{
    public static void main (String [] args)
    {
        HoaQua hq = new HoaQua(10, "Ha Noi");
        hq.Print();
        QuaCam qc = new QuaCam(100, "Thanh Hoa", 10);
        qc.Print();
        QuaTao qt = new QuaTao(100,"Hoa Binh","Chua");
        qt.Print();
        CamCaoPhong ccp = new CamCaoPhong(1000, 5);
        ccp.Print();
        CamSanh cs = new CamSanh(10000,3);
        cs.Print();
    }
}
class HoaQua
{
    double price;
    String nguongoc;
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
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    public HoaQua(double price, String nguongoc)
    {
        setPrice(price);
        setNguongoc(nguongoc);
    }
    public void Print()
    {
        System.out.println(this.getNguongoc());
        System.out.println(this.getPrice());
    }
}
class QuaCam extends HoaQua
{
    double soluong;
    /**
     * @return the soluong
     */
    public double getSoluong() {
        return soluong;
    }
    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(double soluong) {
        this.soluong = soluong;
    }
    public QuaCam (double price, String nguongoc, double soluong)
    {
        super(price, nguongoc);
        setSoluong(soluong);
    }
    @Override
    public void Print()
    {
        super.Print();
        System.out.println(getSoluong());
    }
}
class QuaTao extends HoaQua
{
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
    public QuaTao(double price, String nguongoc, String muivi)
    {
        super(price,nguongoc);
        setMuivi(muivi);
    }
    @Override
    public void Print()
    {
        super.Print();
        System.out.println(getMuivi());
    }
}

class CamCaoPhong extends QuaCam
{
    public CamCaoPhong(double price, double soluong)
    {
        super(price,"Cao Phong",soluong);
    }
}
class CamSanh extends QuaCam
{
    public CamSanh(double price, double soluong)
    {
        super(price,"Hung Yen",soluong);
    }
}
