import org.eclipse.paho.client.mqttv3.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        String myId = MqttClient.generateClientId();
        try {
            MqttClient client = new MqttClient("tcp://localhost:1883",myId);


            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("message -> "+ message.toString());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });

            client.connect();
            client.subscribe("LBS",0);
            System.out.println("this client id is ---> "+myId);
            while (true) {
                System.out.println("start");
                Scanner scanner = new Scanner(System.in);
                String a = scanner.next();

                if(a.equals("1")) {
                    //client.publish("LBS", ("hello!!!!!!!").getBytes(), 0, false);
                    client.lbsManager("paho123456", "null", "null", "one-gu", "one-dong");
                    //client.lbsManager("paho123456","null","seongbuk-dong","null","samsung-dong");
                    //client.lbsManager("paho123456","seongbuk-gu","seongbuk-dong","samsung-gu","samsung-dong");
                    System.out.println("end");
                }else if(a.equals("2")){
                    client.lbsManager("paho123456", "null", "one-dong", "null", "two-dong");
                }else if(a.equals("3")){
                    client.lbsManager("paho123456", "one-gu", "two-dong", "three-gu", "three-dong");
                }
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}

