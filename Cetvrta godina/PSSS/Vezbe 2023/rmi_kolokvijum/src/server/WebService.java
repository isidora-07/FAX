package server;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class WebService extends UnicastRemoteObject implements IAuth, ICalculation {
    private List<String> tokeni = new ArrayList<String>();

    protected WebService() throws RemoteException {
    }

    @Override
    public String generateToken(String username) throws RemoteException {
        String noviToken = sha1(username);

        synchronized (tokeni) {
            if (tokeni.contains(noviToken) == false) {
                tokeni.add(noviToken);
            }
        }
        return noviToken;
    }

    @Override
    public void Logout(String token) throws RemoteException {
        synchronized (token) {
            tokeni.remove(token);
        }
    }

    @Override
    public double mean(String token, double[] array) throws RemoteException {
        // los primer
//        synchronized (tokeni) {
//            if (tokeni.contains(token)) {
//                double zbir = 0;
//                for (double d : array) {
//                    zbir += d;
//                }
//                return zbir / array.length;
//            } else return -1;
//        }

        // bolji primer - brze radi
        boolean uListi = false;
        synchronized (tokeni) {
            uListi = tokeni.contains(token);
        }
        if (uListi != true)
            return -1;

        double zbir = 0;
        for (double d : array) {
            zbir += d;
        }
        return zbir / array.length;
    }

    public String sha1(String input) {
        String sha1 = null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }
}
