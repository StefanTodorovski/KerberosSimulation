package Actors;

import Tools.AESImpl;
import Tools.HelperFunctions;
import Tools.KDCResponse;
import Tools.KDCRequest;
import java.util.ArrayList;

public class KDCServer {
    private final String keyA;
    private final String keyB;

    public KDCServer(String keyA, String keyB) {
        this.keyA = keyA;
        this.keyB = keyB;
    }

    public ArrayList<KDCResponse> ResponseGenerator(KDCRequest request) {
        byte[] sessionKey = HelperFunctions.generateSessionKeyOrNonce();
        String lifetime = HelperFunctions.generateLifetime();
        AESImpl aes = new AESImpl();

        // Generate KDC response for Alice (yA)
        aes.setTheKey(keyA.getBytes());
        byte[] cipSessionKey = aes.encrypt(sessionKey);  // Encrypt the session key
        byte[] cipNonce = aes.encrypt(request.getNonce());  // Encrypt the nonce
        byte[] cipTime = aes.encrypt(lifetime.getBytes());  // Encrypt the lifetime
        byte[] cipOtherId = aes.encrypt(request.getIdB().getBytes());  // Encrypt the ID of the other party (B)
        KDCResponse kdcResponseAlice = new KDCResponse(cipSessionKey, cipNonce, cipTime, cipOtherId);

        // Generate KDC response for Bobby (yB)
        aes.setTheKey(keyB.getBytes());
        cipSessionKey = aes.encrypt(sessionKey);  // Encrypt the session key
        cipOtherId = aes.encrypt(request.getIdA().getBytes());  // Encrypt the ID of the other party (A)
        cipTime = aes.encrypt(lifetime.getBytes());  // Encrypt the lifetime
        KDCResponse kdcResponseBobby = new KDCResponse(cipSessionKey, null, cipTime, cipOtherId);

        // Add the KDC responses to the list
        ArrayList<KDCResponse> responses = new ArrayList<>();
        responses.add(kdcResponseAlice);
        responses.add(kdcResponseBobby);

        return responses;
    }
}
/*Во овој код, класата KDCServer е одговорна за генерирање на KDC (Центар за дистрибуција на клучеви) одговори врз основа на даденото KDCRequest.
Еве преглед на кодот и неговата соодветна логика:
Методот ResponseGenerator зема KDCRequest како влез и генерира KDC одговори за два ентитета, Алис и Боби

Тој генерира клуч за сесија користејќи HelperFunctions.generateSessionKeyOrNonce() и животна вредност користејќи HelperFunctions.generateLifetime().

Се креира примерок од класата AESImpl за да се изврши шифрирање.

За Алис:

КлучотA е поставен во примерот AES за да го шифрира клучот за сесија, нонс, животниот век и ID на другата страна (Б).
Клучот за сесија, nonce, животен век и шифрирана ID на другата страна се зачувани во објект KDCResponse наречен kdcResponseAlice.
За Боби:

КлучотB е поставен во примерот AES да го шифрира клучот за сесија, ID на другата страна (A) и животниот век.
Клучот за сесија, животниот век и шифрираниот ID на другата страна се зачувани во објект KDCResponse наречен kdcResponseBobby.
И kdcResponseAlice и kdcResponseBobby се додаваат во ArrayList од објекти KDCResponse наречени одговори.

Се враќа списокот со одговори што ги содржи одговорите на KDC и за Алис и за Боби.

Кодот има за цел безбедно да генерира одговори на KDC со шифрирање чувствителни информации користејќи шифрирање AES, овозможувајќи сигурна комуникација помеѓу серверот KDC, Алис и Боби.

 */