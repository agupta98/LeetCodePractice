import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lift {


    public class Defaults {
        private static final int MIN_FLOOR = 1;
        private static final int MAX_FLOOR = 20;
        private static final int MAX_LOAD = 2000;
    }

    private int minFloor = Defaults.MIN_FLOOR;
    private int maxFloor = Defaults.MAX_FLOOR;
    private int floor = minFloor;
    private int load = 0;
    private int maxLoad = Defaults.MAX_LOAD;
    private State state = State.STAND;
    private boolean isDoorOpen = false;
    private String id = null;
    private Queue<Integer> upHeap = new PriorityQueue<>(maxFloor);
    private Queue<Integer> downHeap = new PriorityQueue<>(maxFloor, Collections.reverseOrder());

    private enum State {
        MAINTENANCE, STAND, UP, DOWN
    }

    private Lift(String id, int maxFloor, int minFloor, int maxLoad) {
        this(id, maxFloor, minFloor);
        maxLoad(maxLoad);
    }

    private Lift(String id, int maxFloor, int minFloor) {
        this(id, maxFloor);
        minFloor(minFloor);
        floor(minFloor);
    }

    private Lift(String id, int maxFloor) {
        id(id);
        maxFloor(maxFloor);
    }

    public static Lift get(String id, int maxFloor) {
        return new Lift(id, maxFloor);
    }

    public static Lift get(String id, int minFloor, int maxFloor) {
        return new Lift(id, maxFloor, minFloor);
    }

    public static Lift getLiftWithMaxLoad(String id, int maxFloor, int maxLoad) {
        return new Lift(id, maxFloor, Defaults.MIN_FLOOR, maxLoad);
    }

    public static Lift get(String id, int minFloor, int maxFloor, int maxLoad) {
        return new Lift(id, maxFloor, minFloor, maxLoad);
    }

    protected boolean isUpHeapEmpty() {
        return upHeap.isEmpty();
    }

    protected void enqueueFloorUpward(int floor) {
        if (upHeap.contains(floor)) {
            return;
        }
        upHeap.add(floor);
    }

    protected void dequeueFloorUpward() {
        upHeap.remove();
    }

    protected boolean isDownHeapEmpty() {
        return downHeap.isEmpty();
    }

    protected void enqueueFloorDownward(int floor) {
        if (downHeap.contains(floor)) {
            return;
        }
        downHeap.add(floor);
    }

    protected void dequeueFloorDownward() {
        downHeap.remove();
    }

    protected int minFloor() {
        return minFloor;
    }

    protected void minFloor(int minFloor) {
        if (minFloor < 1) {

            throw new IllegalArgumentException();
        }
        if (minFloor > maxFloor) {

            throw new IllegalArgumentException();
        }
        this.minFloor = minFloor;
    }

    protected int maxFloor() {
        return maxFloor;
    }

    protected void maxFloor(int maxFloor) {
        if (maxFloor < 1) {

            throw new IllegalArgumentException();
        }
        if (maxFloor < minFloor) {
            throw new IllegalArgumentException();
        }
        this.maxFloor = maxFloor;
    }

    protected int floor() {
        return floor;
    }

    protected void floor(int floor) {
        if (floor < minFloor) {

            throw new IllegalArgumentException();
        }
        if (floor > maxFloor) {

            throw new IllegalArgumentException();
        }
        this.floor = floor;
    }

    protected int load() {
        return load;
    }

    protected void load(int load) {
        if (load < 0) {

            throw new IllegalArgumentException();
        }
        this.load = load;
    }

    protected int maxLoad() {
        return maxLoad;
    }

    protected void maxLoad(int maxLoad) {
        if (maxLoad < 1000) {

            throw new IllegalArgumentException();
        }
        this.maxLoad = maxLoad;
    }

    protected boolean isMaintenanceState() {
        return state.equals(State.MAINTENANCE);
    }

    protected void setMaintenanceState() {
        state = State.MAINTENANCE;
    }

    protected boolean isStandState() {
        return state.equals(State.STAND);
    }

    protected void setStandState() {
        state = State.STAND;
    }

    protected boolean isUpState() {
        return state.equals(State.UP);
    }

    protected void setUpState() {
        state = State.UP;
    }

    protected boolean isDownState() {
        return state.equals(State.DOWN);
    }

    protected void setDownState() {
        state = State.DOWN;
    }

    protected boolean isDoorOpen() {
        return isDoorOpen;
    }

    protected void openDoor() {
        isDoorOpen = true;
    }

    protected void closeDoor() {
        isDoorOpen = false;
    }

    protected String id() {
        return id;
    }

    private void id(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

}