package demoproject;

public class ComplexObject {
    private int a;
    private int b;
    private int c;
    private int d;

    public static void main(String[] args) {
        ComplexObject.Builder builder = new ComplexObject.Builder();
        builder.withA(1);
        builder.withB(2);
        builder.withC(3);
        ComplexObject complexObject1 = builder.build();

        ComplexObject complexObject = new ComplexObject.Builder()
                .withA(1)
                .withA(2)
                .withC(3)
                .build();
    }

    public static class Builder {
        private int a;
        private int b;
        private int c;
        private int d;

        public ComplexObject build() {
            ComplexObject complexObject = new ComplexObject();
            complexObject.a = a;
            complexObject.b = b;
            complexObject.c = c;
            complexObject.d = d;
            return complexObject;
        }

        public Builder withA(int a) {
            this.a = a;
            return this;
        }

        public Builder withB(int b) {
            this.b = b;
            return this;
        }

        public Builder withC(int c) {
            this.c = c;
            return this;
        }

        public Builder withD(int d) {
            this.d = d;
            return this;
        }
    }
}
