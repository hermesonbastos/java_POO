import java.util.Scanner;
import java.util.Locale;
import java.util.Arrays;


class Jogo{
    
    private int numJogadores;
    private int [] armadilha;
    private int [] posicao;
    private boolean [] podeJogar;
    private int numArmadilha = 0;
    private int numCasas;
    private int atual;
    private boolean fimDoJogo = false;
    
    public Jogo(int numJogadores, int numCasas){
        this.numJogadores = numJogadores;
        this.armadilha = new int[3];
        this.posicao = new int[numJogadores];
        this.podeJogar = new boolean[numJogadores];
        Arrays.fill(podeJogar, true);
        this.numCasas = numCasas;
        this.atual = 0;
    }
    
    public void addArmadilha(int t){
        this.armadilha[numArmadilha] = t;
        numArmadilha++;
    }
    
    public void addMove(int d1, int d2){
        if(!fimDoJogo){
            
            if(podeJogar[atual]){
                
                posicao[atual] += (d1 + d2);
                System.out.println("O jogador "+ (atual + 1) +" vai para a casa "+ posicao[atual]);
        
                if(posicao[atual] > numCasas){
                    System.out.println("O jogador "+ (atual + 1) +" venceu o jogo");
                    fimDoJogo = true;
                    return;
                }
                
                for(int i = 0; i < numArmadilha; i++){
                    if(posicao[atual] == armadilha[i]){
                        System.out.println("O jogador "+ (atual + 1) +" caiu em um armadilha");
                        podeJogar[atual] = false;
                    }
                }
                
                atual = atual == numJogadores - 1 ? atual = 0 : atual + 1;
                
            } else {
                System.out.println("O jogador "+ (atual + 1) +" passa a vez");
                podeJogar[atual] = true;
                atual = atual == numJogadores - 1 ? atual = 0 : atual + 1;
                
                addMove(d1 , d2);
            }
            
        } else {
            System.out.println("O jogo acabou");
        }
    }
    
    public String toString(){
        String str = "";
        for(int i = 0; i < numJogadores; i++){
            str += "PosJogador["+ (i + 1) +"] = " + posicao[i] + "\n";
        }
        return str;
    }
    
}



class Solver{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault ( Locale.US ) ;
        Jogo jogo = new Jogo(0,0);

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);
            if(ui[0].equals("init")) {
                jogo = new Jogo( Integer.parseInt( ui[1]),  Integer.parseInt( ui[2]) );
            } else if(ui[0].equals("end")) {
                break; 
            } else if(ui[0].equals("addArmadilha")) {
                jogo.addArmadilha( Integer.parseInt( ui[1]) ); 
            } else if(ui[0].equals("addMove")) {
                int d1 = Integer.parseInt( ui[1]);
                int d2 = Integer.parseInt( ui[2]);
                jogo.addMove(d1, d2);
            } else if(ui[0].equals("show")) {
                System.out.println(jogo);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}