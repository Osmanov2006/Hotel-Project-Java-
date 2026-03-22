import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataManager{
    private static DataManager instance;
    private List<Room> rooms;
    private List<Employee> employees;
    private List<Reservation> reservations;

    private DataManager(){
        rooms=new ArrayList<>();
        employees=new ArrayList<>();
        reservations=new ArrayList<>();
        loadData();
    }

    public static DataManager getInstance(){
        if(instance==null){
            instance=new DataManager();
        }
        return instance;
    }

    public void loadData(){
        loadRooms();
        loadEmployees();
        loadReservations();
    }

    private void loadRooms(){
        File file=new File("rooms.txt");
        if(!file.exists()){
            initializeDefaultRooms();
            return;
        }

        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==3){
                    int roomNum=Integer.parseInt(parts[0]);
                    String type=parts[1];
                    double price=Double.parseDouble(parts[2]);
                    rooms.add(new Room(roomNum,type,price));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            initializeDefaultRooms();
        }
    }

    private void loadEmployees(){
        File file=new File("employees.txt");
        if(!file.exists()){
            initializeDefaultEmployee();
            return;
        }

        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==6){
                    employees.add(new Employee(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5]));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            initializeDefaultEmployee();
        }
    }

    private void loadReservations(){
        File file=new File("reservations.txt");
        if(!file.exists()) return;

        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==9){
                    int roomNum=Integer.parseInt(parts[0]);
                    Customer customer=new Customer(parts[1],parts[2],parts[3],parts[4],
                            LocalDate.parse(parts[5]),LocalDate.parse(parts[6]));
                    LocalDate start=LocalDate.parse(parts[7]);
                    LocalDate end=LocalDate.parse(parts[8]);
                    reservations.add(new Reservation(roomNum,customer,start,end));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initializeDefaultRooms(){
        rooms.clear();
        for(int i=1;i<=25;i++){
            String type=i<=10?"Single":(i<=20?"Double":"Suite");
            double price=i<=10?100.0:(i<=20?150.0:250.0);
            rooms.add(new Room(i,type,price));
        }
        saveRooms();
    }

    private void initializeDefaultEmployee(){
        employees.clear();
        employees.add(new Employee("Admin User","1234567890","admin@hotel.com","admin","admin","Manager"));
        saveEmployees();
    }

    public void saveRooms(){
        try(PrintWriter pw=new PrintWriter(new FileWriter("rooms.txt"))){
            for(Room r:rooms){
                pw.println(r.getRoomNumber()+","+r.getRoomType()+","+r.getPrice());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveEmployees(){
        try(PrintWriter pw=new PrintWriter(new FileWriter("employees.txt"))){
            for(Employee e:employees){
                pw.println(e.getName()+","+e.getPhone()+","+e.getEmail()+","+
                        e.getUsername()+","+e.getPassword()+","+e.getPosition());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveReservations(){
        try(PrintWriter pw=new PrintWriter(new FileWriter("reservations.txt"))){
            for(Reservation r:reservations){
                Customer c=r.getCustomer();
                pw.println(r.getRoomNumber()+","+c.getName()+","+c.getPhone()+","+c.getEmail()+","+
                        c.getCustomerId()+","+c.getCheckInDate()+","+c.getCheckOutDate()+","+
                        r.getStartDate()+","+r.getEndDate());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Room> getRooms(){
        return rooms;
    }

    public List<Employee> getEmployees(){
        return employees;
    }

    public List<Reservation> getReservations(){
        return reservations;
    }

    public void addRoom(Room room){
        rooms.add(room);
        saveRooms();
    }

    public void addEmployee(Employee emp){
        employees.add(emp);
        saveEmployees();
    }

    public void addReservation(Reservation res){
        reservations.add(res);
        saveReservations();
    }
}