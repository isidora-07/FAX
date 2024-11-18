package zadatak1;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Service extends UnicastRemoteObject implements IAuth, ICalculation {
    List<String> tokeni = new ArrayList<String>();

    protected Service() throws RemoteException {
    }

    @Override
    public synchronized String generateToken(String username) throws RemoteException {
        String token = sha1(username);

        if (tokeni.contains(token) == true) {
            System.out.println("vec postoji to ime.");
        }

        tokeni.add(token);
        return token;
    }

    @Override
    public void logout(String token) throws RemoteException {
        synchronized (token) {
          if(tokeni.contains(token)){
              tokeni.remove(token);
          }
        }
    }

    @Override
    public double calculateMean(String token, double[] array) throws RemoteException {
        boolean uListi = false;
        synchronized (tokeni) {
            uListi = tokeni.contains(token);
        }

        if (uListi == false)
            return -1;

        double sum = 0;
        for (double a : array) {
            sum += a;
        }
        return sum / array.length;
    }

    public String sha1(String input) {
        String sha1 = null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1,
                    digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private double srednjaVrednost(double[] array) {
        double suma = 0;
        for (int i = 0; i < array.length; i++) {
            suma += array[i];
        }
        return suma / array.length;
    }
}
