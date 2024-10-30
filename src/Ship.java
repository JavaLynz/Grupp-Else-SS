import java.util.ArrayList;
import java.util.List;

public class Ship {

   private String name;
   private int size;
   private boolean isAlive;
   private List<> positions;

    public Ship(String name, int size, List<> positions) {
        this.name = name;
        this.size = size;
        this.isAlive = true;
        this.positions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public List getPositions() {
        return positions;
    }

    public void setPositions(List positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", isAlive=" + isAlive +
                ", positions=" + positions +
                '}';
    }
}
