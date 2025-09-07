import java.util.Calendar;
import java.util.Objects;

public class Person {
        private String firstName;
        private String lastName;
        private final String ID ;        // immutable once set
        private String title;
        private int YOB;

        // Constructor with all fields
        public Person(String ID, String firstName, String lastName, String title, int YOB) {
            this.ID = ID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.title = title;
            this.YOB = YOB;
        }

        // Overloaded constructor: no title
        public Person(String ID, String firstName, String lastName, int YOB) {
            this(ID, firstName, lastName, "", YOB);
        }

        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getID() { return ID; }
        public String getTitle() { return title; }
        public int getYOB() { return YOB; }

        // Setters (ID is immutable)
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setTitle(String title) { this.title = title; }
        public void setYOB(int YOB) { this.YOB = YOB; }

        // Additional methods
        public String fullName() {
            return firstName + " " + lastName;
        }

        public String formalName() {
            return title + " " + fullName();
        }

        public int getAge() {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            return currentYear - YOB;
        }

        public int getAge(int year) {
            return year - YOB;
        }

        public String toCSV() {
            return String.format("%s, %s, %s, %s, %d", ID, firstName, lastName, title, YOB);
        }

        public String toJSON() {
            return String.format("{\"ID\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"title\":\"%s\",\"YOB\":%d}",
                    ID, firstName, lastName, title, YOB);
        }

        public String toXML() {
            return String.format("<Person><ID>%s</ID><FirstName>%s</FirstName><LastName>%s</LastName><Title>%s</Title><YOB>%d</YOB></Person>",
                    ID, firstName, lastName, title, YOB);
        }

        @Override
        public String toString() {
            return formalName() + " (" + ID + "), born " + YOB;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return YOB == person.YOB &&
                    ID.equals(person.ID) &&
                    Objects.equals(firstName, person.firstName) &&
                    Objects.equals(lastName, person.lastName) &&
                    Objects.equals(title, person.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, ID, title, YOB);
        }
    }

