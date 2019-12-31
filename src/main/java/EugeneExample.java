
public class EugeneExample {
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        final C c = new C();
        hi(a);
        hi(b);
        hi(c);
    }
    public static void hi(Base base) {
        base.sayHi();
    }
}
interface Base {
    void sayHi();
    void sayBye();
}
class A implements Base {

    public void sayHi() {
        System.out.println("A says hi!");
    }

    public void sayBye() {
        System.out.println("A says bye!");
    }
}
class B implements Base {

    public void sayHi() {
        System.out.println("B says hi!");
    }

    public void sayBye() {
        System.out.println("B says bye!");
    }
}
class C implements Base {

    public void sayHi() {
        System.out.println("C says hi!");
    }

    public void sayBye() {
        System.out.println("C says bye!");
    }
}
