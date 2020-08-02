package core.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;
import core.exceptions.*;


import javax.persistence.*;


@Entity
@Table(name="product")
public class Product {
    public Product() {

    }

    public Product(Integer id, String name, String description, Integer price, Boolean issold, Boolean isaccapted, String imagepath) throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidProductPriceException {
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setIsSold(issold);
        setIsAccapted(isaccapted);
        setImagepath(imagepath);
    }

    public Product(Integer id, String name, String description, Integer price, Boolean issold, Boolean isaccapted, String imagepath,
                   Date created_date, Date sold_date) throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidProductPriceException {
        this(id, name, description, price, issold, isaccapted, imagepath);
        setCreated_date(created_date);
        setSold_date(sold_date);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private Integer price;
    @Column(name="issold")
    private Boolean issold;
    @Column(name="isaccapted")
    private Boolean isaccapted;
    @Column(name = "imagepath")
    private String imagepath;

    public Boolean getIssold() {
        return issold;
    }

    public void setIssold(Boolean issold) {
        this.issold = issold;
    }

    public Boolean getIsaccapted() {
        return isaccapted;
    }

    public void setIsaccapted(Boolean isaccapted) {
        this.isaccapted = isaccapted;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User owner;
    @Column(name = "created_date")
    private Date created_date;
    @Column(name = "sold_date")
    private Date sold_date;

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) throws InvalidImagePathException {
        //String regex = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?";
        //Pattern pattern = Pattern.compile(regex);
        //boolean isMatched = Pattern.matches(regex, imagepath);
        //if (!isMatched) throw new InvalidImagePathException(imagepath);
        this.imagepath = imagepath;
    }

    public Boolean getIsAccapted() {
        return isaccapted;
    }

    public void setIsAccapted(Boolean isaccapted) {
        this.isaccapted = isaccapted;
    }

    public Boolean getIsSold() {
        return issold;
    }

    public void setIsSold(Boolean issold) {
        this.issold = issold;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) throws InvalidProductPriceException {
        if(price < 0) throw new InvalidProductPriceException("The product price cannot be less than 0. Current price: "+Integer.toString(price));
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidProductDescriptionException {
        if ("".equals(description)) throw new InvalidProductDescriptionException("Description cannot be empty!");
        else if (description.length() < 10)
            throw new InvalidProductDescriptionException("Description length has to be more than 10 char. Description is " + description);
        else if (description.length() > 555)
            throw new InvalidProductDescriptionException("Description length cannot be more than 555 char. Description is " + description + "(" + description.length() + ")");
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidProductNameException {
        if ("".equals(name)) throw new InvalidProductNameException("Name cannot be empty!");
        else if (name.length() < 3)
            throw new InvalidProductNameException("Name length has to be more than 2 char. Given name is " + name);
        else if (name.length() > 20)
            throw new InvalidProductNameException("Name length cannot be more than 20 char. Given name is " + name + "(" + name.length() + ")");
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getSold_date() {
        return sold_date;
    }

    public void setSold_date(Date sold_date) {
        this.sold_date = sold_date;
    }
}
