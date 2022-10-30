package mine.ensaj.credit.classes;

public class Product {

    private int id;
    private String name;
    private String description;
    private int category;

    public Product(String name, String description, int category_id) {
        this.name = name;
        this.description = description;
        this.category = category_id;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category_id) {
        this.category = category_id;
    }
}
