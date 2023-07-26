package Entity;

public class Category {
    private Integer id;
    private String name;
    private Integer amountOfParagraph;

    public Integer getAmountOfParagraph() {
        return amountOfParagraph;
    }

    public void setAmountOfParagraph(Integer amountOfParagraph) {
        this.amountOfParagraph = amountOfParagraph;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
