package homework.service;

public class MathService {
    public Integer sum(Integer a, Integer b) {
        return a + b;
    }

    public Integer minus(Integer a, Integer b) {
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    public String destroy(){
        return "call destroy";
    }
}
