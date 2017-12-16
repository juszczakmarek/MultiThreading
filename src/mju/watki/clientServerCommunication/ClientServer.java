/**
 * Przykład komunikacji klient - serwer
 * Uwtórz klasy:
 * Client
 * Server
 * Instancja Client może połączyć się z instancją Server. Połącznie jest reprezentowane za pomocą obiektu klasy Connection.
 * Zakładamy że klient używa tylko połączeń które sam utworzył
 * Klasa Connection umożliwia wysłanie żądań i otrzymywanie odpowiedzi. Utwórz odpowiednio klasę Request i Response.
 * Usługa która realizuje serwer polega na odwróceniu kolejności liter zawartości żądania oraz zmianie liter z dużych
 * na małe i małych na duże.
 * Mamy jedną instancję serwera i 5 instancji klientów
 */

package mju.watki.clientServerCommunication;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientServer {

    public static void main(String[] args) {
        //serwer działa we własnym wątku
        //każdy z klientów też działą we własnym wątku

        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();

        Client client1 = new Client();
        client1.setServer(server);
        Thread client1Thread = new Thread(client1);
        client1Thread.start();

        Client client2 = new Client();
        client2.setServer(server);
        Thread client2Thread = new Thread(client2);
        client2Thread.start();

        Client client3 = new Client();
        client3.setServer(server);
        Thread client3Thread = new Thread(client3);
        client3Thread.start();

        Client client4 = new Client();
        client4.setServer(server);
        Thread client4Thread = new Thread(client4);
        client4Thread.start();

        Client client5 = new Client();
        client5.setServer(server);
        Thread client5Thread = new Thread(client5);
        client5Thread.start();
    }

}

class Client implements Runnable {

    private Server server;
    private Connection currentConnection;

    public void setServer(Server server) {
        this.server = server;
    }

    Connection connect(Server server){
        return new Connection(server);
    }

    @Override
    public void run() {
        System.out.println("Client " + this.hashCode() + " started");
        while(true) {
            String messageToBeSend = getRandomMessageFromList();
            Connection connection = connect(server);
            Request request = new Request(messageToBeSend);
            Response response = connection.execute(request);
            String result = response.getPayload();
            System.out.println("Client " + this.hashCode() + " sends " + messageToBeSend + " and recieve's " + result);
        }
    }

    private String getRandomMessageFromList() {
        Random random = new Random();
        String[] messagess = {"aBcDe", "FgHi", "jKlM", "nOpR", "qaz123", "JyHuH7s"};
        return messagess[random.nextInt(messagess.length-1)];
    }
}

class Server implements Runnable {
    public Response service(Request request) {
        return new Response(request.getPayload());
    }

    @Override
    public void run() {
        System.out.println("*** Server started *** (" + toString() + ")");
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Connection{
    private Server server;
    public Connection(Server server) {
        this.server = server;
    }

    public Response execute(Request request){
        return server.service(request);
    }
}

class Request{
    private String payload;

    public Request(String payload){
        this.payload = payload;
    }

    public String getPayload(){

        String[] payLoadArray = this.payload.split("");
        List<String> payLoadList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (int i=payLoadArray.length-1;i>=0;i--) {
            payLoadList.add(payLoadArray[i]);
        }


        for (String character : payLoadList) {
            if (character.equals(character.toUpperCase())) {
                result.append(character.toLowerCase());
            } else {
                result.append(character.toUpperCase());
            }
        }

        return result.toString();
    }
}

class Response{
    private String payload;
    public Response(String payload){
        this.payload = payload;
    }

    public String getPayload(){
        return payload;
    }
}
