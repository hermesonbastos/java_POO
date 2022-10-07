import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


class Lead { //todo
    private float thickness; //calibre
    private String hardness; //dureza
    private int size; //tamanho em mm

    public Lead(float thickness, String hardness, int size) { //todo
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public float getThickness() { //todo
        return this.thickness;
    }

    public String getHardness() { //todo
        return this.hardness;
    }

    public int getSize() { //todo
        return this.size;
    }

    public void setSize(int size) { //todo
        this.size = size;
    }

    public int usagePerSheet() {
        if(hardness.equals("HB"))
            return 1;
        else if(hardness.equals("2B"))
            return 2;
        else if(hardness.equals("4B"))
            return 4;
        else
            return 6;
    }

    public String toString() {
        DecimalFormat form = new DecimalFormat("0.0");
        return form.format(thickness) + ":" + hardness + ":" + size;
    }
}


class Pencil { //todo
    private float thickness;
    private Lead tip;

    public Pencil(float thickness) { //todo
        setThickness(thickness);
    }

    public float getThickness() { //todo
        return this.thickness;
    }

    public void setThickness(float value) { //todo
        this.thickness = value;
    }

    public boolean hasGrafite() { //todo
        if(this.tip == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean insert(Lead grafite) { //todo
        if(!hasGrafite()){
            if(grafite.getThickness() == this.thickness){
                this.tip = grafite;
                return true;
            } else {
                System.out.println("fail: calibre incompatível");
                return false;
            }
        } else {
            System.out.println("fail: ja existe grafite");
            return false;
        }
    }

    public Lead remove() { //todo
        this.tip = null;
        return tip;
    }

    public void writePage() { //todo
        if(tip.getSize() <= 10){
            System.out.println("warning: grafite com tamanho insuficiente para escrever");
        }else{

            if(tip.getSize() - tip.usagePerSheet() < 10){
                System.out.println("fail: folha incompleta");
                tip.setSize(10);
            }else{
                tip.setSize(tip.getSize() - tip.usagePerSheet());
            }

        }
    }
    
    public String toString() {
        String saida = "calibre: " + thickness + ", grafite: ";
        if (tip != null)
            saida += "[" + tip + "]";
        else
            saida += "null";
        return saida;
    }
}



public class Solver {

    static Pencil lap = new Pencil(0.5f);
    public static void main(String[] args) {
        chain.put("init",   () -> lap = new Pencil(Float.parseFloat(ui.get(1))));
        chain.put("insert", () -> lap.insert(new Lead(Float.parseFloat(ui.get(1)), ui.get(2), Integer.parseInt(ui.get(3)))));
        chain.put("remove", () -> lap.remove());
        chain.put("write",  () -> lap.writePage());
        chain.put("show",   () -> System.out.println(lap.toString()));

        execute(chain, ui);
    }

    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, Runnable> chain = new HashMap<>();
    static ArrayList<String> ui = new ArrayList<>();

    static void execute(HashMap<String, Runnable> chain , ArrayList<String> ui) {
        while(true) {
            ui.clear();
            String line = scanner.nextLine();
            Collections.addAll(ui, line.split(" "));
            System.out.println("$" + line);
            if(ui.get(0).equals("end")) {
                break;
            } else if (chain.containsKey(ui.get(0))) {
                chain.get(ui.get(0)).run();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
    }
}