public class Deskovka {

    public String name;
    public boolean bought;
    public int popularity;

    public Deskovka(String name, boolean bought, int popularity) {
        this.name = name;
        this.bought = bought;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return
                "název deskovky: " + name
               ;
    }
}