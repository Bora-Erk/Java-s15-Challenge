public class Librarian extends Person{
    private String password;
    public Librarian(int id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public boolean verify(String inputPassword){
        return this.password.equals(inputPassword);
    }
    public String whoYouAre() {
        return "Librarian";
    }
}
