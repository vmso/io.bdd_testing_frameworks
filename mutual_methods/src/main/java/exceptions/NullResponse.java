package exceptions;

public class NullResponse extends Exception{
    public NullResponse(){
        super("Response yok, lütfen önce bir api isteği oluşturun");
    }
}
