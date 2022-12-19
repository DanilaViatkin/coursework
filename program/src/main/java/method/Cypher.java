package method;

public interface Cypher {
    public abstract byte[] encode(byte[] message) throws Exception;
    public abstract byte[] decode(byte[] encryptedMessage) throws Exception;
}
