package Tools;

public class YABMessage{
    private byte[] Id;
    private byte[] Timestamp;

    public YABMessage(byte[] cipSelfId, byte[] cipTimestamp){
        this.Id = cipSelfId;
        this.Timestamp = cipTimestamp;
    }

    public byte[] getId(){
        return Id;
    }
    public byte[] getTimestamp(){
        return Timestamp;
    }
}

/*
Оваа класа YABMessage претставува порака (message) која се разменува меѓу корисниците Y и B во Kerberos протоколот.
 */