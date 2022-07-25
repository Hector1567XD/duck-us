package tests;

/*
    Este Main no tiene nada que ver con el cliente, servidor o commons, esta hecho
    solo para probar cosas puntuales de algunas clases
*/
import common.networking.engine.Agent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerTests {
    public static void main(String[] args) {
        try {
            System.out.println("================== Hash Map by Agent Test ==================");

            Agent agentTesta1 = new Agent(InetAddress.getByName("192.168.1.100"), 1000);
            System.out.println(agentTesta1.hashCode());
            Agent agentTesta2 = new Agent(InetAddress.getByName("192.168.1.100"), 1000);
            System.out.println(agentTesta2.hashCode());
            Agent agentTesta3 = new Agent(InetAddress.getByName("192.168.1.100"), 2000);
            System.out.println(agentTesta3.hashCode());
            Agent agentTesta4 = new Agent(InetAddress.getByName("192.168.1.101"), 1000);
            System.out.println(agentTesta4.hashCode());

            HashMap<Agent, String> hashMap = new HashMap();
            hashMap.put(agentTesta1, "Primero");
            hashMap.put(agentTesta2, "Segundo");
            hashMap.put(agentTesta3, "Tercero");
            hashMap.put(agentTesta4, "Cuarto");
            for (Agent key : hashMap.keySet()) {
                System.out.println("---> " + key.getIpAddress().getHostAddress() + " -- " + key.getPort() + " --> " + hashMap.get(key));
            }
            
            
            System.out.println("================== AGENTs ==================");

            Agent agentTest1 = new Agent(InetAddress.getByName("192.168.1.100"), 1000);
            Agent agentTest2 = new Agent(InetAddress.getByName("192.168.1.100"), 1000);

            if (agentTest1 == agentTest2) {
                System.out.println("(+) 1 y 2 son iguales por '=='");
            }else{
                System.out.println("(-) 1 y 2 son desiguales por '=='");
            }

            if (agentTest1.equals(agentTest2)) {
                System.out.println("(+) 1 y 2 son iguales por 'equals'");
            }else{
                System.out.println("(-) 1 y 2 son desiguales por 'equals'");
            }
            
            Agent agentTest3 = new Agent(InetAddress.getByName("192.168.1.100"), 1005);
            
            if (agentTest1 == agentTest3) {
                System.out.println("(+) 1 y 3 son iguales por '=='");
            }else{
                System.out.println("(-) 1 y 3 son desiguales por '=='");
            }

            if (agentTest1.equals(agentTest3)) {
                System.out.println("(+) 1 y 3 son iguales por 'equals'");
            }else{
                System.out.println("(-) 1 y 3 son desiguales por 'equals'");
            }

            System.out.println("================== InetAddress ==================");

            InetAddress inetAddressTest1 = InetAddress.getByName("192.168.1.100");
            InetAddress inetAddressTest2 = InetAddress.getByName("192.168.1.100");

            if (inetAddressTest1 == inetAddressTest2) {
                System.out.println("(+) 1 y 2 son iguales por '=='");
            }else{
                System.out.println("(-) 1 y 2 son desiguales por '=='");
            }

            if (inetAddressTest1.equals(inetAddressTest2)) {
                System.out.println("(+) 1 y 2 son iguales por 'equals'");
            }else{
                System.out.println("(-) 1 y 2 son desiguales por 'equals'");
            }

            InetAddress inetAddressTest3 = InetAddress.getByName("192.168.1.100");

            if (inetAddressTest1 == inetAddressTest3) {
                System.out.println("(+) 1 y 3 son iguales por '=='");
            }else{
                System.out.println("(-) 1 y 3 son desiguales por '=='");
            }

            if (inetAddressTest1.equals(inetAddressTest3)) {
                System.out.println("(+) 1 y 3 son iguales por 'equals'");
            }else{
                System.out.println("(-) 1 y 3 son desiguales por 'equals'");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
