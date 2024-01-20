public class Person {
    private String name, lastName;

    public Person(String nameLastName) {

        if(nameLastName.contains(" ")) {
            this.name = nameLastName.split(" ")[0];
            this.lastName = nameLastName.split(" ")[1];
        }else {
            this.name = nameLastName;
            this.lastName = "";
        }

    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public String getLastName () {
        return lastName;
    }

}
