package lab2.server;

import lab2.AlreadyVotedException;
import lab2.Election;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


/**
 * ElectionServer:
 *  This class implements the remote Election interface. It will create a registry
 *  and bind the remote implemented class to it.
 */
public class ElectionService extends UnicastRemoteObject implements Election {
    Map<Integer, String> votes;


    public ElectionService() throws RemoteException{
        super();

        try {
            votes = readFromDisk();
        } catch (Exception e) {
            votes = new HashMap<Integer, String>();
        }
    }

    public HashMap<Integer, String> readFromDisk() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("./votes.ser");
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (HashMap<Integer, String>) ois.readObject();
    }


    public void writeToDisk() throws IOException {
        FileOutputStream fout = new FileOutputStream("./votes.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(votes);
    }

    @Override
    public void vote(String candidate, int voter_number) throws IOException, AlreadyVotedException {
        if (votes.get(voter_number) == null)
            votes.put(voter_number, candidate);
        else
            throw new AlreadyVotedException("You have already voted");

            writeToDisk();
    }

    @Override
    public String result() throws RemoteException {
        Map<String, Integer> results = new HashMap<String, Integer>();

        Set<String> candiates = new HashSet<String>(votes.values());
        for (String candiate: candiates) {
            results.put(candiate, Collections.frequency(votes.values(), candiate));
        }

        return results.toString();
    }
}
