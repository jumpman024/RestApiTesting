package traffic;

public class AustralianTraffic implements CentralTraffic,ContinentalTraffic {

    public static void main(String[] args) {
        CentralTraffic a = new AustralianTraffic();
        a.redStop();
        a.flashYellow();
        a.greenGo();


        AustralianTraffic b = new AustralianTraffic();
        ContinentalTraffic c = new AustralianTraffic();
        b.goOnSymbol();
        c.TrainSymbol();
    }


    public void greenGo() {

        System.out.println("green stop implementation");
    }

    public void redStop() {

        System.out.println("red stop implementation");

    }

    public void flashYellow() {

        System.out.println("flash yellow implementation");
    }

    public void goOnSymbol(){
        System.out.println("Go on hand symbol");

    }

    public void TrainSymbol() {

    }
}
