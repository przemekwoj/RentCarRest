export class Client {
    private  firstName?:string;
    private  lastName?:string;
    private  email?:string;
    private  phone?:string;
    private  password?:string;

    public setFirstName(firstName:string){
        this.firstName = firstName;
    }

    public setlastName(lastName:string){
        this.lastName = lastName;
    } 
       public setEmail(email:string){
        this.email = email;
    }   
     public setPhone(phone:string){
        this.phone = phone;
    }   
     public setPassword(password:string){
        this.password = password;
    }
}
