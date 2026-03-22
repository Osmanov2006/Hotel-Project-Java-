public class Employee extends Person implements ILoginable{
    private String username;
    private String password;
    private String position;

    public Employee(String name,String phone,String email,String username,String password,String position){
        super(name,phone,email);
        this.username=username;
        this.password=password;
        this.position=position;
    }

    @Override
    public boolean login(String username,String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void logout(){
        System.out.println(getName()+" logged out");
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position=position;
    }
}
