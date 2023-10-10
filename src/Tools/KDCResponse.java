package Tools;

public class KDCResponse{
    private byte[] cipSessionKey;
    private byte[] cipNonce;
    private byte[] cipTime;
    private byte[] cipOtherId;

    public KDCResponse(byte[] cipSessionKey, byte[] cipSelfNonce, byte[] cipTime, byte[] cipOtherId){
        this.cipSessionKey = cipSessionKey;
        this.cipNonce = cipSelfNonce;
        this.cipOtherId = cipOtherId;
        this.cipTime = cipTime;
    }

    public byte[] getCipSessionKey(){
        return cipSessionKey;
    }
    public byte[] getCipNonce(){
        return cipNonce;
    }
    public byte[] getCipOtherId(){
        return cipOtherId;
    }
    public byte[] getCipTime(){
        return cipTime;
    }
}

/*
Класата KDCResponse се користи за инкапсулирање на шифрираните информации испратени од KDC како одговор на барање.
Обезбедува методи за добивање пристап до поединечните шифрирани компоненти на одговорот, како што се клучот за сесија, нонс,
 ID на другата страна и временскиот печат или вредноста на животниот век.
Овие шифрирани компоненти може да се користат од примачот за да воспостави безбедна комуникација или да изврши понатамошни криптографски операции.
 */