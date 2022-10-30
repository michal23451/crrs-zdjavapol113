package pl.sdaacademy.conferenceroomreservationsystem.test;

public class Example implements Cloneable {

    private String example;

    public Example(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public static void main(String[] args) {
        Example example1 = new Example("test");
        try {
            Example exampleCopy = (Example) example1.clone();
            System.out.println(exampleCopy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
