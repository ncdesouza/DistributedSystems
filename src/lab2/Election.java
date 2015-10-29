package lab2;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ElectionInterface:
 * <brief description of class>
 */
public interface Election extends Remote {

    /**
     * vote:
     *      This method has two parameters through which the client supplies
     *      the name of a candidate (a string) and the “voter's number” (an
     *      integer used to ensure each user votes once only). The voter's
     *      numbers are allocated sparsely from the range of integers to make
     *      them hard to guess.
     * @param candidate A string that represents the name of the candidate.
     * @param voter_number A unique integer that represents the user's id
     */
    void vote(String candidate, int voter_number) throws IOException, AlreadyVotedException;


    /**
     * result:
     *      This method has two parameters through which the server supplies
     *      the client with the name of the candidates and the number of votes
     *      for that candidate.
     * @return A string that represents the the total votes for each of the
     *         candidates.
     */
    String result() throws RemoteException;

}
