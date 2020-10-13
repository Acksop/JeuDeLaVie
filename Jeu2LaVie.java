public class Jeu2LaVie {
	public static void main( String[] Args){
		int limite,nbIterations;
		int[] tailleTab;
		boolean reponse;
		tailleTab = new int[2];
		tailleTab[0]=10;
		tailleTab[1]=10;
		boolean[][] tabJDV;
		tabJDV = new boolean[tailleTab[0]][tailleTab[1]]; 
		
		do{
			System.out.println("LIMITES DU JEU:\n 1: NSEW \n 2: NS \n 3: EW \n 4: Aucunes");
			limite = ChoixNombreEntre(1,4);
			System.out.println("Nbs d'itérations: (entre 1 et 10000)");
			nbIterations = ChoixNombreEntre(1,10000);
			Iterations(tabJDV,tailleTab,limite,nbIterations);
			reponse = RetourMenu(); 
		}while(reponse);
	}
	public static int ChoixNombreEntre(int a, int b){
		System.out.print("?");
		int var;
		do{
			System.out.print("?");
			var = Keyboard.readInt();
			System.out.print("\n");
		}while(var<a || var>b);
		return var;
	}
	public static boolean RetourMenu(){
		int reponse;
		System.out.println("Voulez-vous recommencez?\n 1: OUI\n 2: NON");
		reponse = ChoixNombreEntre(1,2);
		return(reponse == 1);
	}
	public static void Iterations(boolean[][] tabJDV,int[] tailleTab, int limite,int nbIterations){
		Remplir(tabJDV,tailleTab);
		AfficherJeu(tabJDV,tailleTab);
		do{
			while(nbIterations>0){
				ModifTJDV(tabJDV,tailleTab,limite);
				nbIterations--;
				AfficherJeu(tabJDV,tailleTab);
			}
			nbIterations = Continuer();
		}while(nbIterations>0);
	}
	public static void Remplir(boolean[][] tabJDV,int[] tailleTab){
		for(int i=0;i<tailleTab[0];i++){
			for(int j=0;j<tailleTab[1];j++){
				int random = (int)Math.round(Math.random()*2);
				if(random==1){
					tabJDV[i][j] = true;
				}else{	
					tabJDV[i][j] = false;
				}
			}
		}
	}
	public static void AfficherJeu(boolean[][] tabJDV,int[] tailleTab){
		for(int i=0;i<tailleTab[0];i++){
			for(int j=0;j<tailleTab[1];j++){
				if(tabJDV[i][j]){
					System.out.print("+");
				}else{
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
              for(int i=0;i<22-tailleTab[1];i++){
                System.out.print("\n");
              }
	}
	public static void ModifTJDV (boolean[][] tabJDV,int[] tailleTab, int limite){
		boolean[][] tempTab;
		tempTab = new boolean[tailleTab[0]][tailleTab[1]]; 
		
		for(int i=0;i<tailleTab[0];i++){
			for(int j=0; j<tailleTab[1];j++){
				int compteurNaissance=0;
				for(int k=-1;k<2;k++){
					for(int l=-1;l<2;l++){
						if(TestLimite(i+k,j+l,limite,tailleTab)){
							if(tabJDV[((i+k)+tailleTab[0])%tailleTab[0]][((j+l)+tailleTab[1])%tailleTab[1]])
								compteurNaissance++;
						}
					}
				}
				if(tabJDV[i][j]){compteurNaissance--;}
				tempTab[i][j] = TestNaissance(compteurNaissance,tabJDV[i][j]);
			}
		}
		
		CopieTableaudans(tempTab,tabJDV,tailleTab);
	}
	public static boolean TestLimite(int x,int y,int limite,int[] tailleTab){
		switch(limite){
			case 1:
				return((x>=0&&x<tailleTab[0])||(y>=0&&y<tailleTab[1]));
			case 2:
				return(y>=0&&y<tailleTab[1]);
			case 3:
				return(x>=0&&x<tailleTab[0]);
			default:
				return true;
		}
	}
	public static boolean TestNaissance(int x,boolean individu){
		if(x<2||x>3){
			return(false);
		}else if(x==3){
			return(true);
		}else{
			return(individu);
		}
	}
	public static void CopieTableaudans(boolean[][] tab1,boolean[][] tabJDV,int[] tailleTab){
		for(int i=0;i<tailleTab[0];i++){
			for(int j=0;j<tailleTab[1];j++){
				tabJDV[i][j] = tab1[i][j];
			}
		}
	}
	public static int Continuer(){
	System.out.println("Voulez-vous continuer?\n 1: OUI\n 2:NON");
	int suite = ChoixNombreEntre(1,2);
	if(suite==1){
		System.out.println("Encore combien d'itérations:(entre 1 et 10000)");
		return(ChoixNombreEntre(1,10000));
	}else{
		return(0);
	}
}
}
