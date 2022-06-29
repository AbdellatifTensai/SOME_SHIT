static class Fighter {
    
    public String name;
    public int health, damagePerAttack;

    public Fighter(String name, int health, int damagePerAttack) {
        this.name = name;
        this.health = health;
        this.damagePerAttack = damagePerAttack;
    }
}

class Kata{
    
    public static void main(String[] args) {
        
    }

    private void declareWineer(Fighter f1, Fighter f2, String firstAttacker){
        Fighter fAttacker = f1.name == firstAttacker? f1: f2;
        Fighter sAttacker = f1.name != firstAttacker? f1: f2;
        while(fAttacker.health != 0 || sAttacker.health != 0){
            
        }
        
    }
}