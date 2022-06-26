package classroomSix;

import com.github.javafaker.Faker;

public class Example {
    public static void main(String[] args) {
        // Zem faker. atrodās viss kas ir dependencijā!

        // Ievadam datus un izvadam
        Employee employee1 = new Employee("Toms", "Jēkabsons",28);
        System.out.println(employee1.toString());


        // ģenerēsim datus un izvadīsim
        Faker faker = new Faker(); // datu ģenerātors

        String name = faker.name().firstName();
        String lastname = faker.name().lastName();
        int age = faker.number().numberBetween(1,100);

        Employee employee2 = new Employee(name, lastname, age);
        System.out.println(employee2.toString());


        // Trešais veids kā ģenerēt un izvadīt
        Employee employee3 = new Employee(faker.name().firstName(), faker.name().lastName(), faker.number().randomDigit());
        System.out.println(employee3.toString()); // to string nāk no citas klases

    }
}
