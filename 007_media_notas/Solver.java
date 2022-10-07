import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;


class MediaNotas{

    private ArrayList<Double> notas = new ArrayList<Double>();

    public void addNota(double nota){
        this.notas.add(nota);
    }

    public void removeNota(int index){
        if(index >= 0 && index < notas.size()){
            notas.remove(index);
        } else {
            System.out.println("falha: indice invalido");
        }
    }

    public void mudaNota(int index, double valor){
        if(index >= 0 && index < notas.size()){
            notas.set(index, valor);
        } else {
            System.out.println("falha: indice invalido");
        }
    }

    public double maiorNota(){
        double maior = 0;
        if(notas.size() > 0){
            for(double i : notas){
            if(i > maior){
                maior = i;
            }
            }
            return maior;
        } else {
            System.out.println("falha: quantidade de notas insuficiente");
            return 1;
        }
    }

    public double menorNota(){
        double menor = 10;
        if(notas.size() > 0){
        for(double i : notas){
            if(i <= menor){
            menor = i;
            }
        }
            return menor;
        } else {
            System.out.println("falha: quantidade de notas insuficiente");
            return 0;
        }
    }

    double media(){
        double media = 0;
        for(double i : notas){
            media += i;
        }
        media /= notas.size();
        return media;
    }

    void parametriza(){
        double maior = 0;
        for(double i : notas){
            if(i > maior){
                maior = i;
            }
        }
        
        for(int i = 0; i < notas.size(); i++){
            notas.set(i, (notas.get(i)/maior) * 10);
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder("[");
        for(int i = 0; i < notas.size(); i++){
            if(i < notas.size() - 1){
                str.append(String.format("%.2f", notas.get(i))).append(", ");
            } else {
                str.append(String.format("%.2f", notas.get(i))).append("]");
            }
        }
        return str.toString();
    }
}



class Solver{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MediaNotas m = new MediaNotas();

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);
            if(ui[0].equals("end")) {
                break; 
            } else if(ui[0].equals("addNota")) {
                m.addNota( Double.parseDouble( ui[1]) );
            } else if(ui[0].equals("removeNota")) {
                m.removeNota( Integer.parseInt( ui[1]) ); 
            } else if(ui[0].equals("maiorNota")) {
                System.out.println( m.maiorNota() );
            } else if(ui[0].equals("menorNota")) {
                System.out.println( m.menorNota() );
            } else if(ui[0].equals("media")) {
                System.out.printf( Locale.US, "%.2f\n",  m.media() );
            }else if(ui[0].equals("parametriza")) {
                m.parametriza(); 
            }else if(ui[0].equals("show")) {
                System.out.println( m );
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}