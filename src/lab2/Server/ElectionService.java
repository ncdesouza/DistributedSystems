package lab2.Server;

import lab2.AlreadyVotedException;
import lab2.Election;

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
        votes = new HashMap<Integer, String>();
    }

    @Override
    public void vote(String candidate, int voter_number) throws RemoteException, AlreadyVotedException {
//        System.out.println(candidate + Integer.toString(voter_number));
        if (votes.get(voter_number) == null)
            votes.put(voter_number, candidate);
        else
            throw new AlreadyVotedException("You have already voted");
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
