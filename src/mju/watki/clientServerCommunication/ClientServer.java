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
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientServer {

    public static void main(String[] args) {

        //Zadanie 1
        //czy da sie ominac ustawianie serwera dla klienta, przez uzycie setServer
        //Zadanie 2
        //zaimplementowac mozliwosc obslugi ograniczonej liczby watkow po stronie serwera
        //kazdy request przekazywany jest do puli watkow, jezeli ilosc requestow przekracza
        //dostepna pule watkow to request jest kolejkowany i czeka na mozliwosc wykonania
        //(zwraca future??)

        Server server = new Server();
        Thread serverThread = new Thread(() -> {
            System.out.println("*** Server started *** (" + server.toString() + ")");
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();

        Client client1 = new Client();
        Thread client1Thread = new Thread(() -> {
            Connection client1Connection = client1.connect(server);
            while (true) {
                String messageToBeSend = getRandomMessageFromList();
                Future<Response> response = client1Connection.execute(new Request(messageToBeSend));//tutaj mam uzyc response.isDone
                String result = null;
                try {
                    System.out.println("Server total requests " +server.getCurrentRequest());
                    result = response.get().getPayload();
                } catch (Exception e) {
                    new Exception(e);
                }
                System.out.println("Client 1, Reqeust=" + messageToBeSend + ", response=" + result
                        + ", server id=" + server.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        client1Thread.start();

        Client client2 = new Client();
        Thread client2Thread = new Thread(() -> {
            Connection client2Connection = client2.connect(server);
            while (true) {
                String messageToBeSend = getRandomMessageFromList();
                Future<Response> response = client2Connection.execute(new Request(messageToBeSend));
                String result = null;
                try {
                    System.out.println("Server total requests " +server.getCurrentRequest());
                    result = response.get().getPayload();
                } catch (Exception e) {
                    new Exception(e);
                }
                System.out.println("Client 2, Reqeust=" + messageToBeSend + ", response=" + result
                        + ", server id=" + server.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        client2Thread.start();

        Client client3 = new Client();
        Thread client3Thread = new Thread(() -> {
            Connection client3Connection = client3.connect(server);
            while (true) {
                String messageToBeSend = getRandomMessageFromList();
                Future<Response> response = client3Connection.execute(new Request(messageToBeSend));
                String result = null;
                try {
                    System.out.println("Server total requests " +server.getCurrentRequest());
                    result = response.get().getPayload();
                } catch (Exception e) {
                    new Exception(e);
                }
                System.out.println("Client 3, Reqeust=" + messageToBeSend + ", response=" + result
                        + ", server id=" + server.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        client3Thread.start();

        Client client4 = new Client();
        Thread client4Thread = new Thread(() -> {
            Connection client4Connection = client4.connect(server);
            while (true) {
                String messageToBeSend = getRandomMessageFromList();
                Future<Response> response = client4Connection.execute(new Request(messageToBeSend));
                String result = null;
                try {
                    System.out.println("Server total requests " +server.getCurrentRequest());
                    result = response.get().getPayload();
                } catch (Exception e) {
                    new Exception(e);
                }
                System.out.println("Client 4, Reqeust=" + messageToBeSend + ", response=" + result
                        + ", server id=" + server.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        client4Thread.start();

        Client client5 = new Client();
        Thread client5Thread = new Thread(() -> {
            Connection client5Connection = client5.connect(server);
            while (true) {
                String messageToBeSend = getRandomMessageFromList();
                Future<Response> response = client5Connection.execute(new Request(messageToBeSend));
                String result = null;
                try {
                    System.out.println("Server total requests " +server.getCurrentRequest());
                    result = response.get().getPayload();
                } catch (Exception e) {
                    new Exception(e);
                }
                System.out.println("Client 5, Reqeust=" + messageToBeSend + ", response=" + result
                        + ", server id=" + server.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        client5Thread.start();
    }

    public static String getRandomMessageFromList() {
        Random random = new Random();
        String[] messagess = {"aBcDe", "FgHi", "jKlM", "nOpR", "qaz123", "JyHuH7s", "yh&jU", "jm67YHJ"};
        return messagess[random.nextInt(messagess.length - 1)];
    }

}

class Client {

    private Server server;

    public void setServer(Server server) {
        this.server = server;
    }

    Connection connect(Server server) {
        return new Connection(server);
    }
}

class Server {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int totalRequest = 0;
    private AtomicInteger currentRequest = new AtomicInteger(0);

    public Future<Response> service(Request request) {
        totalRequest++;
        currentRequest.incrementAndGet();
        Future<Response> responseFuture = executorService.submit(() -> {
            currentRequest.decrementAndGet();
            return new Response(request.getPayload());
        });

        try {
            return responseFuture;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrentRequest() {
        return currentRequest.intValue();
    }
}

class Connection {
    private Server server;

    public Connection(Server server) {
        this.server = server;
    }

    public Future<Response> execute(Request request){
        return server.service(request);
    }
}

class Request {
    private String payload;

    public Request(String payload) {
        this.payload = payload;
    }

    public String getPayload() {

        String[] payLoadArray = this.payload.split("");
        List<String> payLoadList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (int i = payLoadArray.length - 1; i >= 0; i--) {
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

class Response {
    private String payload;

    public Response(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
