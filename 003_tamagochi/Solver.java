import java.util.Scanner;

class Pet{
    private int energyMax, hungryMax, cleanMax;
    private int energy, hungry, shower;
    private int diamonds;
    private int age;
 
    void setEnergy(int value){
        if(value <= 0){
            this.energy = 0;
            System.out.println("fail: pet morreu de fraqueza");
        }
        else if(value > this.energyMax)
            this.energy = this.energyMax;
        else
            this.energy = value;
    }
    
    public void setHungry(int value){
        if(value <= 0){
            this.hungry = 0;
            System.out.println("fail: pet morreu de fome");
        } else if(value > this.hungryMax){
            this.hungry = this.hungryMax;
        } else {
            this.hungry = value;
        }
    }
    
    void setClean(int value){
        if(value <= 0){
            this.shower = 0;
            System.out.println("fail: pet morreu de sujeira");
        } else if(value > this.cleanMax){
            this.shower = this.cleanMax;
        } else {
            this.shower = value;
        }
    }
    
    public Pet(int energy, int hungry, int shower){
        this.energyMax = energy;
        this.hungryMax = hungry;
        this.cleanMax = shower;
        this.energy = energyMax;
        this.hungry = hungryMax;
        this.shower = cleanMax;
        this.diamonds = 0;
        this.age = 0;
    }
    
    public String toString(){
        return String.format("E:" + this.energy + "/" + this.energyMax + ", S:" + this.hungry + "/" + this.hungryMax + ", L:" + this.shower + "/" + this.cleanMax + ", D:" + this.diamonds + ", I:" + this.age);
    }
    
    private boolean testAlive(){
        if(this.energy <= 0 || this.hungry <= 0 || this.shower <= 0){
            return false;
        } else {
            return true;
        }
    }
    
    public void play(){
        if(!testAlive()){
            System.out.println("fail: pet esta morto");
            return;
        } else {
            setEnergy(getEnergy() - 2);
            setHungry(getHungry() - 1);
            setClean(getClean() - 3);
            diamonds += 1;
            age += 1;
        }
    }
    
    public void shower(){
        if(!testAlive()){
            System.out.println("fail: pet esta morto");
            return;
        } else {
            setEnergy(getEnergy() - 3);
            setHungry(getHungry() - 1);
            setClean(this.cleanMax);
            this.age += 2;
        }
    }
    
    public void eat(){
        if(!testAlive()){
            System.out.println("fail: pet esta morto");
            return;
        } else {
            setEnergy(getEnergy() - 1);
            setHungry(getHungry() + 4);
            setClean(getClean() - 2);
            this.age += 1;   
        }
    }
    
    public void sleep(){
        if(!testAlive()) {
            System.out.println("fail: pet esta morto");
            return;
        } else {
            if(this.energy > (this.energyMax - 5)){
            System.out.println("fail: nao esta com sono");
        } else {
            this.age += (this.energyMax - this.energy);
            setEnergy(this.energyMax);
            setHungry(getHungry() - 1);
        }
        }
    }
    
    int getClean(){
        return this.shower;
    }
    int getHungry(){
        return this.hungry;
    }
    int getEnergy(){
        return this.energy;
    }
    int getEnergyMax(){
        return this.energyMax;
    }
    int getCleanMax(){
        return this.cleanMax;
    }
    int getHungryMax(){
        return this.hungryMax;
    }
}
class Solver{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pet pet = new Pet(0, 0, 0);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if(ui[0].equals("end")) {
                break;
            } else if(ui[0].equals("show")) {
                System.out.println(pet.toString());
            } else if(ui[0].equals("init")) {
                int energy = Integer.parseInt(ui[1]);
                int hungry = Integer.parseInt(ui[2]);
                int shower = Integer.parseInt(ui[3]);
                pet = new Pet(energy, hungry, shower);
            } else if(ui[0].equals("play")) {
                pet.play();
            } else if(ui[0].equals("eat")) {
                pet.eat();
            } else if(ui[0].equals("clean")) {
                pet.shower();
            } else if(ui[0].equals("sleep")) {
                pet.sleep();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
