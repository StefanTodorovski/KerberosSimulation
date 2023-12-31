package Tools;

public class KDCRequest{
    String idA;
    String idB;
    byte[] nonce;

    public KDCRequest(String idA, String idB, byte[] nonce){
        this.idA = idA;
        this.idB = idB;
        this.nonce = nonce;
    }

    public String getIdA(){
        return idA;
    }

    public String getIdB(){
        return idB;
    }

    public byte[] getNonce(){
        return nonce;
    }
}
/*

Оваа класа KDCRequest претставува захтев (request) кој се праќа до KDC (Key Distribution Center) серверот за генерирање на KDC одговор.
 */