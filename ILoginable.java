import java.time.LocalDate;

public class Customer extends Person implements Comparable<Customer>{
    private String customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Customer(String name,String phone,String email,String customerId,LocalDate checkIn,LocalDate checkOut){
        super(name,phone,email);
        this.customerId=customerId;
        this.checkInDate=checkIn;
        this.checkOutDate=checkOut;
    }

    @Override
    public int compareTo(Customer other){
        int nameCompare=this.getName().compareTo(other.getName());
        if(nameCompare!=0){
            return nameCompare;
        }
        return this.checkInDate.compareTo(other.checkInDate);
    }

    public String getCustomerId(){
        return customerId;
    }

    public void setCustomerId(String customerId){
        this.customerId=customerId;
    }

    public LocalDate getCheckInDate(){
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate){
        this.checkInDate=checkInDate;
    }

    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate){
        this.checkOutDate=checkOutDate;
    }
}