package shop.develop.aurorashop.model;

public class DetalleVenta {

    private int amount;
    private  double subTotal;
    private Producto item;

    public DetalleVenta(int amount, double subTotal, Producto item) {
        this.amount = amount;
        this.subTotal = subTotal;
        this.item = item;
    }

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public double getSubTotal() {return subTotal;}

    public void setSubTotal(double subTotal) {this.subTotal = subTotal;}

    public Producto getItem() {return item;}

    public void itemRegister()  {

    }
    public void itemUpdate(){

    }
    public void itemDelate(){

    }
    public String printDetail(){
        return null;
    }
}



