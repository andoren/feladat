package core.model;

import javax.persistence.*;
@Entity
@Table(name="Address")
public class Address {

    public Address(){}
    public Address(String country, String county, int zip, String village, String street, String number) {
        this.country = country;
        this.county = county;
        this.zip = zip;
        this.village = village;
        this.street = street;
        this.number = number;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="country")
    private String country;
    @Column(name="county")
    private String county;
    @Column(name="zip")
    private int zip;
    @Column(name="village")
    private String village;
    @Column(name="street")
    private String street;
    @Column(name="number")
    private String number;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User userid;


}
