import java.util.Objects;

public class Product {
        private final String ID;       // immutable
        private String name;
        private String description;
        private double cost;

        // Constructor
        public Product(String ID, String name, String description, double cost) {
            this.ID = ID;
            this.name = name;
            this.description = description;
            this.cost = cost;
        }

        // Getters
        public String getID() { return ID; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public double getCost() { return cost; }

        // Setters
        public void setName(String name) { this.name = name; }
        public void setDescription(String description) { this.description = description; }
        public void setCost(double cost) { this.cost = cost; }

        // Additional methods
        public String toCSV() {
            return String.format("%s, %s, %s, %.2f", ID, name, description, cost);
        }

        public String toJSON() {
            return String.format("{\"ID\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"cost\":%.2f}", ID, name, description, cost);
        }

        public String toXML() {
            return String.format("<Product><ID>%s</ID><Name>%s</Name><Description>%s</Description><Cost>%.2f</Cost></Product>",
                    ID, name, description, cost);
        }

        @Override
        public String toString() {
            return name + " (" + ID + "): $" + cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return Double.compare(product.cost, cost) == 0 &&
                    ID.equals(product.ID) &&
                    Objects.equals(name, product.name) &&
                    Objects.equals(description, product.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ID, name, description, cost);
        }
    }
